public class Message {

    public static final int FAIL = -1;
    public static final int SUCCESS = 0;

    public static final int LOAD_PRODUCT = 1;
    public static final int LOAD_PRODUCT_REPLY = 2;

    public static final int SAVE_PRODUCT = 3;

    public static final int LOAD_CUSTOMER = 4;
    public static final int LOAD_CUSTOMER_REPLY = 5;

    public static final int SAVE_CUSTOMER = 6;

    public static final int SAVE_ORDER = 7;
    public static final int LOAD_ORDER = 8;
    public static final int LOAD_ORDER_REPLY = 9;

    public static final int LOGIN = 1000;
    public static final int LOGIN_REQUEST = 1001;
    public static final int LOGIN_RESPONSE_NO_USERNAME = 1002;
    public static final int LOGIN_RESPONSE_FAIL_PASSWORD = 1003;
    public static final int LOGIN_RESPONSE_SUCCESS = 1004;
    public static final int LOAD_PRODUCT_TO_ADD = 10005;
    public static final int LOAD_PRODUCT_REPLY_TO_ADD=10006;
    public static final int SAVE_ORDER_LINE = 10007;


    private int id; // the type of the message
    private String content; // the content of the message

    public Message(int id, String content) {
        this.id = id;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
