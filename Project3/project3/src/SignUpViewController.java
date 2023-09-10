import com.google.gson.Gson;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUpViewController {
    private JPanel mainPanel;
    private JButton signUpButton;
    private JTextField userIDTF;
    private JTextField usernameTF;
    private JTextField passwordTF;
    private JTextField displayNameTF;

    private Client client;

    public SignUpViewController(Client client) {
        this.client = client;

        signUpButton.addActionListener(new ActionListener() {
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
