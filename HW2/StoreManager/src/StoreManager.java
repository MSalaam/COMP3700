public class StoreManager {

    private static StoreManager instance = null;

    private SQLiteDataAdapter dao;

    private ProductView productView = null;

    private MainScreen mainScreen;

    private OrderView orderView;

    public ProductView getProductView() {
        return productView;
    }

    private ProductController productController = null;

    private CustomerView customerView;

    private CustomerController customerController;

    private StoreManager() { }

    public static StoreManager getInstance() {
        if (instance == null)
            instance = new StoreManager("SQLite");
        return instance;
    }

    public SQLiteDataAdapter getDataAccess() {
        return dao;
    }

    private StoreManager(String db) {
        // do some initialization here!!!
        if (db.equals("SQLite"))
            dao = new SQLiteDataAdapter();

        dao.connect();
        productView = new ProductView();
        mainScreen = new MainScreen();
        productController = new ProductController(productView, dao);
        orderView = new OrderView();
        customerView = new CustomerView();
        customerController = new CustomerController(customerView, dao);
    }


    public MainScreen getMainScreen() {
        return mainScreen;
    }

    public OrderView getOrderView() {
        return orderView;
    }

    public CustomerView getCustomerView() { return customerView; }
}
