import com.google.gson.Gson;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginViewController  {
    private JPanel mainPanel;
    private JTextField usernameTF;
    private JButton loginButton;
    private JButton signUpButton;
    private JPasswordField passwordTF;

    private SignUpViewController signUpViewController;


    private final Client client;

    public LoginViewController(Client client) {
        this.client = client;


        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameTF.getText();
                String password = new String(passwordTF.getPassword());

                UserInfo userInfo = new UserInfo(username, password);
                Gson gson = new Gson();
                Message loginMessage = new Message(Message.LOGIN_REQUEST, gson.toJson(userInfo));
                client.sendMessage(loginMessage);
            }
        });

        // TODO: you have to implement the signup button
        // 1. Create a SignUpViewController
        // 2. fill in the information -> send the new user to the server
        // 3. If success -> goback to the login screen
        // 4. if not -> pop up a dialog -> let the user enter infor again




    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

}
