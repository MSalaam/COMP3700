import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainScreen extends JFrame {


    public JButton productButton = new JButton("Product View");
    public JButton customerButton = new JButton("Customer View");
    public JButton orderButton = new JButton("Order View");

    public MainScreen() {
        this.setTitle("Main Screen");
        this.setSize(new Dimension(600, 300));
        this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));    // make this window with box layout


        JPanel buttonPanel = new JPanel();
        buttonPanel.add(productButton);
        buttonPanel.add(customerButton);
        buttonPanel.add(orderButton);

        productButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Product Button Pressed");
                StoreManager.getInstance().getProductView().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                StoreManager.getInstance().getProductView().setVisible(true); // Show the ProductView!
            }
        });

        orderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StoreManager.getInstance().getOrderView().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                StoreManager.getInstance().getOrderView().setVisible(true); // Show the ProductView!
            }
        });

        customerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StoreManager.getInstance().getCustomerView().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                StoreManager.getInstance().getCustomerView().setVisible(true); // Show the ProductView!
            }
        });

        this.getContentPane().add(buttonPanel);

    }
}
