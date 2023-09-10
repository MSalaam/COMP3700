public interface DataAccess {
    void connect();


    ProductModel loadProduct(int productID);
    void saveProduct(ProductModel product);

     OrderModel loadOrder(int orderID);
     void saveOrder(OrderModel order);

     CustomerModel loadCustomer(int customerID);
     void saveCustomer(CustomerModel customer);

    // void loadUser(int uderId);
    // void saveUser(UserModel user);
}
