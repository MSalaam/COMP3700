import com.google.gson.Gson;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

public class OrderViewController {
    private JPanel mainPanel;
    private JTextField orderIDTF;
    private JTextField customerIDTF;
    private JTextField totalCostTF;
    private JTextField totalTaxTF;
    private JTextField dateTF;
    private JButton saveOrderButton;
    private JButton loadOrderButton;

    private Client client;

    public OrderViewController(Client client) {
        this.client = client;

        loadOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String orderID = orderIDTF.getText();
                Message message = new Message(Message.LOAD_ORDER, orderID);
                client.sendMessage(message);
            }
        });

        saveOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Order order = new Order();

                order.setOrderID(Integer.parseInt(orderIDTF.getText()));
                order.setCustomerID(customerIDTF.getText());
                order.setTotalCost(Double.parseDouble(totalCostTF.getText()));
                order.setTotalTax(Double.parseDouble(totalTaxTF.getText()));
                order.setDate(Date.valueOf(dateTF.getText()));

                Gson gson = new Gson();

                String orderString = gson.toJson(order);

                Message message = new Message(Message.SAVE_ORDER, orderString);
                client.sendMessage(message);
            }
        });
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void updateOrderInfo(Order order) {
        orderIDTF.setText(String.valueOf(Integer.parseInt(orderIDTF.getText())));
        customerIDTF.setText(customerIDTF.getText());
        totalCostTF.setText(String.valueOf(Double.parseDouble(totalCostTF.getText())));
        totalTaxTF.setText(String.valueOf(Double.parseDouble(totalTaxTF.getText())));
        dateTF.setText(dateTF.getText());
    }

}
