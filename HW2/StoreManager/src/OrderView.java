import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OrderView extends JFrame {

    public JTextField txtOrderID = new JTextField(30);
    public JTextField txtOrderDate = new JTextField(30);
    public JTextField txtCustomerName = new JTextField(30);
    public JTextField txtTotalCost = new JTextField(30);
    public JTextField txtTotalTax = new JTextField(30);

    public JButton btnLoad = new JButton("Load");
    public JButton btnSave = new JButton("Save");

    public OrderView() {

        this.setTitle("Product View");
        this.setSize(new Dimension(600, 300));
        this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));    // make this window with box layout

        JPanel line1 = new JPanel();
        line1.add(new JLabel("Order ID"));
        line1.add(txtOrderID);
        this.getContentPane().add(line1);

        JPanel line5 = new JPanel();
        line5.add(new JLabel("Order Date"));
        line5.add(txtOrderDate);
        this.getContentPane().add(line5);

        JPanel line2 = new JPanel();
        line2.add(new JLabel("Customer Name"));
        line2.add(txtCustomerName);
        this.getContentPane().add(line2);

        JPanel line3 = new JPanel();
        line3.add(new JLabel("TotalCost"));
        line3.add(txtTotalCost);
        this.getContentPane().add(line3);

        JPanel line4 = new JPanel();
        line4.add(new JLabel("TotalTax"));
        line4.add(txtTotalTax);
        this.getContentPane().add(line4);


        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnLoad);
        buttonPanel.add(btnSave);

        btnLoad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(txtOrderID.getText());

                OrderModel order = StoreManager.getInstance().getDataAccess().loadOrder(id);

                txtCustomerName.setText(order.getCustomerName());
                txtOrderDate.setText(order.getOrderDate());
                txtTotalCost.setText(String.valueOf(order.getTotalCost()));
                txtTotalTax.setText(String.valueOf(order.getTotalTax()));
            }
        });

        this.getContentPane().add(buttonPanel);
    }
}
