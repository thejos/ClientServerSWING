package clientserverswing;

import java.awt.Color;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

/**
 *
 * @author: Dejan Smiljić; e-mail: dej4n.s@gmail.com
 *
 */
public class ServerGUI extends JFrame{
    
    protected static JTextArea textArea;
    private JScrollPane scrollPane;
    protected JButton clearButton;
    
    public ServerGUI() {
        
        initComponents();
    }
    
    public void initComponents() {

        this.setSize(400, 320);
        this.setLocation(810, 90);
        //this.setLocationRelativeTo(null);
        //this.setLocationByPlatform(true);
        this.setTitle("Server");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setType(Window.Type.UTILITY/*NORMAL*/);
        this.setResizable(false);
        //this.getContentPane().setBackground(Color.getHSBColor(2, 236, 623));
        
        textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);
        textArea.setBackground(Color.BLACK);
        textArea.setFont(new Font("Courier New"/*"Sans Serif"*/, Font.BOLD+Font.ITALIC, 12));
        textArea.setForeground(Color.WHITE/*getHSBColor(12, 157, 571)*/);
        
        scrollPane = new JScrollPane(textArea);
        //scrollPane.setPreferredSize(new Dimension(350, 220));
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        //scrollPane.setLayout(null);
        scrollPane.setBounds(8, 8, 380, 230);
        
        this.add(scrollPane);
        
        clearButton = new JButton("Clear screen");
        clearButton.setBounds(142, 250, 120, 25);
        clearButton.setMnemonic(KeyEvent.VK_C);
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ServerGUI.textArea.setText("");
            }
        });
        this.getRootPane().setDefaultButton(clearButton);
        this.add(clearButton);
    }
    
    

}