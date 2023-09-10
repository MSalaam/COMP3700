import java.sql.*;

public class SQLiteDataAdapter implements DataAccess {
    Connection conn = null;

    @Override
    public void connect() {
        try {
            // db parameters
            String url = "jdbc:sqlite:store.db";
            // create a connection to the database
            Class.forName("org.sqlite.JDBC");

            conn = DriverManager.getConnection(url);

            if (conn == null)
                System.out.println("Cannot make the connection!!!");
            else
                System.out.println("The connection object is " + conn);

            System.out.println("Connection to SQLite has been established.");

            /* Test data!!!
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Product");

            while (rs.next())
                System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4));
            */

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void saveProduct(ProductModel product) {
        try {
            Statement stmt = conn.createStatement();

            if (loadProduct(product.productID) == null) {           // this is a new product!
                stmt.execute("INSERT INTO Product(productID, name, price, quantity) VALUES ("
                        + product.productID + ","
                        + '\'' + product.name + '\'' + ","
                        + product.price + ","
                        + product.quantity + ")"
                );
            }
            else {
                stmt.executeUpdate("UPDATE Product SET "
                        + "productID = " + product.productID + ","
                        + "name = " + '\'' + product.name + '\'' + ","
                        + "price = " + product.price + ","
                        + "quantity = " + product.quantity +
                        " WHERE productID = " + product.productID
                );

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public ProductModel loadProduct(int productID) {
        ProductModel product = null;
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Product WHERE ProductID = " + productID);
            if (rs.next()) {
                product = new ProductModel();
                product.productID = rs.getInt(1);
                product.name = rs.getString(2);
                product.price = rs.getDouble(3);
                product.quantity = rs.getDouble(4);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return product;
    }

    @Override
    public OrderModel loadOrder(int orderId) {
        OrderModel order = null;
        try {
            Statement stmt = conn.createStatement();
            System.out.println("SELECT * FROM 'Order' WHERE OrderID = " + orderId);
            ResultSet rs = stmt.executeQuery("SELECT * FROM 'Order' WHERE OrderID = " + orderId);
            if (rs.next()) {
                order = new OrderModel();
                order.setOrderDate(rs.getString(2));
                order.setCustomerName(rs.getString(3));
                order.setTotalCost(rs.getDouble(4));
                order.setTotalTax(rs.getDouble(5));
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return order;
    }

    @Override
    public void saveOrder(OrderModel order) {
        try {
            Statement stmt = conn.createStatement();

            if (loadProduct(order.getOrderID()) == null) {           // this is a new product!
                stmt.execute("INSERT INTO 'Order'(OrderID, OrderDate, Customer, TotalCost, TotalTax) VALUES ("
                        + order.getOrderID() + ","
                        + "date('" + order.getOrderDate() + "',"
                        + "'" + order.getCustomerName() + "',"
                        + order.getTotalCost() + ","
                        + order.getTotalTax() + ")"
                );
            }
            else {
                stmt.executeUpdate("UPDATE 'Order' SET "
                                + "OrderID = " +order.getOrderID() + ","
                                + "OrderDate = " + "date('" + order.getOrderDate() + "',"
                                + "Customer = " + "'" + order.getCustomerName() + "',"
                                + "TotalCost =" + order.getTotalCost() + ","
                                + "TotalTax =" + order.getTotalTax() + ")" +
                        " WHERE OrderID = " + order.getOrderID()
                );

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void saveCustomer(CustomerModel customer) {
        try {
            Statement stmt = conn.createStatement();

            if (loadCustomer(customer.getCustomerID()) == null) {           // this is a new customer!
                stmt.execute("INSERT INTO Product(productID, name, price, quantity) VALUES ("
                        + customer.getCustomerID() + ","
                        + '\'' + customer.getCustomerName() + '\'' + ","
                        + customer.getTotalItems() + ")"
                );
            }
            else {
                stmt.executeUpdate("UPDATE Product SET "
                        + "productID = " + customer.getCustomerName() + ","
                        + "name = " + '\'' + customer.getCustomerName() + '\'' + ","
                        + "quantity = " + customer.getCustomerName()
                );

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public CustomerModel loadCustomer(int customerID) {
        CustomerModel customer = null;
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("CustomerID = " + customerID);
            if (rs.next()) {
                customer = new CustomerModel();
                customer.customerID = rs.getInt(1);
                customer.customerName = rs.getString(2);
                customer.totalItems = rs.getInt(4);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return customer;
    }


}
