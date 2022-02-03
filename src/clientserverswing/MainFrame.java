package clientserverswing;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author: Dejan Smiljić; e-mail: dej4n.s@gmail.com
 *
 */
public class MainFrame extends JFrame{
    
    private static JButton additionButton;
    private BufferedReader buffIn = null;
    private PrintWriter printWriter = null;
    private static Socket client = null;
    private final String HOST = "localhost";
    private final int PORT = 1000;
    private String txtField1Data;
    private String txtField2Data;
    private String numericClientInput;
    private boolean connectionRefused;

    public MainFrame() {

        initComponents();
    }

    public void initComponents() {

        this.setSize(238, 350);
        this.setLocationRelativeTo(null);
        this.setTitle("Client");
        /*this.setVisible(true);//setVisible() pozvati u main metodi aplikacije da bi se sadrzaj prozora
        //pravilno prikazao prilikom pokretanja aplikacije*/
        this.setLayout(null);
        this.setType(Type.UTILITY/*NORMAL*/);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        UpperPanel topPanel = new UpperPanel();
        this.add(topPanel);

        LowerPanel lowerPanel = new LowerPanel();
        this.add(lowerPanel);

        additionButton = new JButton("Addition");
        additionButton.setBounds(53, 148, 130, 25);
        additionButton.setMnemonic(KeyEvent.VK_D);
        this.add(additionButton);
        //this.getRootPane().setDefaultButton(additionButton);

        try {
            MainFrame.client = new Socket(HOST, PORT);
            connectionRefused = false;
            this.setTitle("Client "+client.getLocalSocketAddress().toString());
            this.buffIn = new BufferedReader(new InputStreamReader(client.getInputStream()));
            this.printWriter = new PrintWriter(client.getOutputStream(), true);
        } catch (ConnectException cExc) {
            LowerPanel.labelResult.setText("Server unavailable...");
            LowerPanel.labelResult.setFont(new Font("Courier New", Font.ITALIC, 12));
            //lowerPanel.setEnabled(false);
            //LowerPanel.labelResult.setEnabled(false);
            connectionRefused = true;
            System.out.println(cExc);
        } catch (IOException ioExc) {
            System.out.println(ioExc);
        }

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    if (client != null) {
                        client.close();
                        System.out.println("Socket closed: " + client.isClosed());
                    }
                } catch (IOException ioExc) {
                    System.out.println(ioExc);
                }

                try {
                    if (buffIn != null) {
                        buffIn.close();
                        buffIn.ready();// provjera statusa buffered reader-a, izbacuje IOException
                    }
                } catch (IOException ioExc) {
                    System.out.println("Buffered Reader: " + ioExc.getMessage());
                }

                if (printWriter != null) {
                    printWriter.close();
                }
            }// WindowEvent end
        }); // WindowListener end

        additionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if ((UpperPanel.txtField1.getText().isEmpty() || UpperPanel.txtField1.getText().equals(".") || UpperPanel.txtField1.getText().equals(UpperPanel.emptyString)) && !connectionRefused) {
                    UpperPanel.txtField1.requestFocusInWindow();
                    UpperPanel.txtField1.setText(UpperPanel.emptyString);
                    UpperPanel.txtField1.setFont(new Font("Times New Roman", Font.ITALIC, 12));
                    UpperPanel.txtField1.selectAll();
                    LowerPanel.labelResult.setText("Input error!");
                    LowerPanel.labelResult.setFont(new Font("Courier New", Font.ITALIC, 14));
                    return;
                } else if ((UpperPanel.txtField2.getText().isEmpty() || UpperPanel.txtField2.getText().equals(".") || UpperPanel.txtField2.getText().equals(UpperPanel.emptyString)) && !connectionRefused) {
                    UpperPanel.txtField2.requestFocusInWindow();
                    UpperPanel.txtField2.setText(UpperPanel.emptyString);
                    UpperPanel.txtField2.setFont(new Font("Times New Roman", Font.ITALIC, 12));
                    UpperPanel.txtField2.selectAll();
                    LowerPanel.labelResult.setText("Input error!");
                    LowerPanel.labelResult.setFont(new Font("Courier New", Font.ITALIC, 14));
                    return;
                } else if ((UpperPanel.txtField1.getText().isEmpty() || UpperPanel.txtField1.getText().equals(".") || UpperPanel.txtField1.getText().equals(UpperPanel.emptyString)) && connectionRefused) {
                    UpperPanel.txtField1.requestFocusInWindow();
                    UpperPanel.txtField1.setText(UpperPanel.emptyString);
                    UpperPanel.txtField1.setFont(new Font("Times New Roman", Font.ITALIC, 12));
                    UpperPanel.txtField1.selectAll();
                    LowerPanel.labelResult.setText("Input error!");
                    LowerPanel.labelResult.setFont(new Font("Courier New", Font.ITALIC, 14));
                    return;
                } else if ((UpperPanel.txtField2.getText().isEmpty() || UpperPanel.txtField2.getText().equals(".") || UpperPanel.txtField2.getText().equals(UpperPanel.emptyString)) && connectionRefused) {
                    UpperPanel.txtField2.requestFocusInWindow();
                    UpperPanel.txtField2.setText(UpperPanel.emptyString);
                    UpperPanel.txtField2.setFont(new Font("Times New Roman", Font.ITALIC, 12));
                    UpperPanel.txtField2.selectAll();
                    LowerPanel.labelResult.setText("Input error!");
                    LowerPanel.labelResult.setFont(new Font("Courier New", Font.ITALIC, 14));
                    return;
                }

                try {

                    client = new Socket(HOST, PORT);
                    MainFrame.this.setTitle("Client "+client.getLocalSocketAddress().toString());
                    buffIn = new BufferedReader(new InputStreamReader(client.getInputStream()));
                    printWriter = new PrintWriter(client.getOutputStream(), true);

                    txtField1Data = String.valueOf(Double.parseDouble(UpperPanel.txtField1.getText()));
                    txtField2Data = String.valueOf(Double.parseDouble(UpperPanel.txtField2.getText()));
                    numericClientInput = txtField1Data + "," + txtField2Data;
                    System.out.println(numericClientInput);// oznaciti kao komentar

                    printWriter.println(numericClientInput);

                    String serverResult = buffIn.readLine();
                    System.out.println(serverResult);// oznaciti kao komentar
                    LowerPanel.labelResult.setText("Result : ".concat(serverResult));
                    LowerPanel.labelResult.setFont(new Font("Dialog", Font.BOLD, 12));
                    printWriter.println(String.format("received data: %s", serverResult));

                } catch (ConnectException cExc) {
                    LowerPanel.labelResult.setText("Connection refused");
                    lowerPanel.setBorder(LowerPanel.borderOff);
                    LowerPanel.labelResult.setFont(new Font("Courier New", Font.ITALIC, 14));
                    //lowerPanel.setEnabled(false);
                    //LowerPanel.labelResult.setEnabled(false);
                    for (Component component : topPanel.getComponents()) {
                        component.setEnabled(false);
                    }
                    topPanel.setEnabled(false);
                    additionButton.setEnabled(false);
                    LowerPanel.labelResult.requestFocusInWindow();
                    //repaint();
                    System.out.println(cExc);
                } catch (NumberFormatException | IOException multiExc) {

                    /*LowerPanel.labelResult.setText("Input error!");
                    LowerPanel.labelResult.setFont(new Font("Courier New", Font.ITALIC, 12));
                    if (UpperPanel.txtField1.getText().isEmpty() || UpperPanel.txtField1.getText().equals(".") || UpperPanel.txtField1.getText().equals(UpperPanel.emptyString)) {
                    UpperPanel.txtField1.requestFocusInWindow();
                    UpperPanel.txtField1.setText(UpperPanel.emptyString);
                    UpperPanel.txtField1.setFont(new Font("Times New Roman", Font.ITALIC, 12));
                    UpperPanel.txtField1.selectAll();
                    } else if (UpperPanel.txtField2.getText().isEmpty() || UpperPanel.txtField2.getText().equals(".") || UpperPanel.txtField2.getText().equals(UpperPanel.emptyString)) {
                    UpperPanel.txtField2.requestFocusInWindow();
                    UpperPanel.txtField2.setText(UpperPanel.emptyString);
                    UpperPanel.txtField2.setFont(new Font("Times New Roman", Font.ITALIC, 12));
                    UpperPanel.txtField2.selectAll();
                    }*/
                    System.out.println(multiExc);
                }
                /*finally {
                try {
                if (client != null) {
                client.close();
                System.out.println("Socket closed: " + client.isClosed());
                }
                } catch (IOException ioExc) {
                System.out.println(ioExc);
                }
                try {
                if (buffIn != null) {
                buffIn.close();
                buffIn.ready();// provjera statusa buffered reader-a
                }
                } catch (IOException ioExc) {
                System.out.println("Buffered Reader: " + ioExc.getMessage());
                }
                if (printWriter != null) {
                printWriter.close();
                }
                }*/
            }// ActionEvent end
        });// addActionListener end
    }//initComponents end

}
