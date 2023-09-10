import com.google.gson.Gson;

import javax.crypto.SecretKey;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Base64;

public class Client {
    private JTextArea messageTextArea;
    private JButton manageCheckoutButton;
    private JButton manageProductButton;
    private JButton manageCustomerButton;
    private JPanel mainPanel;


    private SecretKey secretKey;

    private byte[] initializationVector;


    private Socket socket;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;

    private Gson gson;

    private Worker worker;

    private ProductViewController productViewController;

    public Client() {
        try {
            socket = new Socket(InetAddress.getByName("127.0.0.1"), 12002);
            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());

            // send the secret key
            secretKey = KeyService.createAESKey();

            String keyString = KeyService.convertSecretKeyToString(secretKey);

            dataOutputStream.writeUTF(keyString);

            // send the initialization vector

            initializationVector = KeyService.createInitializationVector();

            String vectorString = Base64.getEncoder().encodeToString(initializationVector);

            dataOutputStream.writeUTF(vectorString);
        } catch (Exception ex) {
            ex.printStackTrace();
        }





        gson = new Gson();

        worker = new Worker();
        Thread workerThread = new Thread(worker);
        workerThread.start();

        this.productViewController = new ProductViewController(this);

        manageProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Manage Product");
                frame.setContentPane(productViewController.getMainPanel());
                frame.setMinimumSize(new Dimension(800, 400));
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });
    }

    public void sendMessage(Message message) {

        String str = gson.toJson(message);
        try {

            // Encrypting the message
            // using the symmetric key
            byte[] cipherText
                    = KeyService.do_AESEncryption(
                    str,
                    secretKey,
                    initializationVector);

            String cipherTextString = Base64.getEncoder().encodeToString(cipherText);

            dataOutputStream.writeUTF(cipherTextString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    private class Worker implements Runnable {

        @Override
        public void run() {
            while (true) {
                String replyString = null;
                try {
                    replyString = dataInputStream.readUTF();

                    byte[] decode = Base64.getDecoder().decode(replyString);

                    replyString
                            = KeyService.do_AESDecryption(
                            decode,
                            secretKey,
                            initializationVector);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                Message message = gson.fromJson(replyString, Message.class);

                processMessage(message);

            }
        }
    }

    private void processMessage(Message message) {
        messageTextArea.append(gson.toJson(message) + "\n");
        switch (message.getId()) {
            case Message.LOAD_PRODUCT_REPLY: {
                Product product = gson.fromJson(message.getContent(), Product.class);
                productViewController.updateProductInfo(product);
                break;
            }

            default:
                return;
        }

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Client");
        frame.setContentPane(new Client().mainPanel);
        frame.setMinimumSize(new Dimension(800, 400));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
