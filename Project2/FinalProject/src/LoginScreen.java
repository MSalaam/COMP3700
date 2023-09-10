import com.google.gson.Gson;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginScreen {
    public JPanel getMainPanel() {
        return mainPanel;
    }

    private JPanel mainPanel;
    private JTextField usernameTF;
    private JPasswordField passwordTF;
    private JButton loginButton;
    private Client client;


    public LoginScreen(Client client) {
        this.client = client;

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Product product = new Product();

//                product.setProductID(Integer.parseInt(productIDTF.getText()));
//                product.setName(productNameTF.getText());
//                product.setPrice(Double.parseDouble(productPriceTF.getText()));
//                product.setQuantity(Double.parseDouble(productQuantityTF.getText()));
//
//                Gson gson = new Gson();
//
//                String productString = gson.toJson(product);
//
//                Message message = new Message(Message.SAVE_PRODUCT, productString);
//                client.sendMessage(message);

                String username = usernameTF.getText();
                String password = new String(passwordTF.getPassword());
                Account account = new Account(username, password);
                Gson gson = new Gson();
                String accountString = gson.toJson(account);
                Message message = new Message(Message.LOGIN, accountString);
                client.sendMessage(message);
            }
        });
    }

}
