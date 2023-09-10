import com.google.gson.Gson;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.atomic.AtomicReference;

public class CustomerViewController {
    private JPanel mainPanel;
    private JTextField customerIDTF;
    private JTextField customerFirstNameTF;
    private JTextField customerLastNameTF;
    private JTextField customerPhoneTF;
    private JButton saveCustomerButton;
    private JButton loadCustomerButton;


    private Client client;

    public CustomerViewController(Client client) {
        this.client = client;

        loadCustomerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String customerID = customerIDTF.getText();
                Message message = new Message(Message.LOAD_CUSTOMER, customerID);
                client.sendMessage(message);
            }
        });

        saveCustomerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Customer customer = new Customer();

                customer.setCustomerID(customerIDTF.getText());
                customer.setFirstName(customerFirstNameTF.getText());
                customer.setLastName(customerFirstNameTF.getText());
                customer.setPhoneNumber(customerPhoneTF.getText());

                Gson gson = new Gson();

                String customerString = gson.toJson(customer);

                Message message = new Message(Message.SAVE_CUSTOMER, customerString);
                client.sendMessage(message);
            }
        });
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void updateCustomerInfo(Customer customer) {
        customerIDTF.setText(customer.getCustomerID());
        customerFirstNameTF.setText(customer.getFirstName());
        customerFirstNameTF.setText(customer.getLastName());
        customerPhoneTF.setText(customer.getPhoneNumber());

    }
}
