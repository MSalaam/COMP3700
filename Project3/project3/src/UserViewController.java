import com.google.gson.Gson;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserViewController {
    private JPanel mainPanel;
    private JButton loadUserButton;
    private JButton saveUserButton;
    private JTextField userIDTF;
    private JTextField usernameTF;
    private JTextField displayNameTF;
    private JPasswordField passwordTF;

    private Client client;

    public UserViewController(Client client){

        this.client = client;

        loadUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userID = userIDTF.getText();
                Message message = new Message(Message.LOAD_CUSTOMER, userID);
                client.sendMessage(message);

            }
        });

        saveUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                User user = new User();

                user.setUserID(Integer.parseInt(userIDTF.getText()));
                user.setUsername(usernameTF.getText());
                user.setPassword(passwordTF.getText());
                user.setDisplayName(displayNameTF.getText());


                Gson gson = new Gson();

                String userString = gson.toJson(user);

                Message message = new Message(Message.SAVE_CUSTOMER, userString);
                client.sendMessage(message);
            }
        });


    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
