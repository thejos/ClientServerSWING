package clientserverswing;

import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

/**
 *
 * @author: Dejan Smiljić; e-mail: dej4n.s@gmail.com
 *
 */
public class UpperPanel extends JPanel {

    private Border border;
    private final JLabel label1;
    private final JLabel label2;
    protected static JTextField txtField1;
    protected static JTextField txtField2;
    protected static String emptyString;

    public UpperPanel() {

        this.setLayout(null);
        this.setBounds(5, 2, 222, 85);

        border = BorderFactory.createTitledBorder(border, "Client", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("courier new", Font.ITALIC, 11));
        this.setBorder(border);

        label1 = new JLabel("1st number  :");
        label1.setBounds(22, 20, 100, 20);
        this.add(label1);

        txtField1 = new JTextField();
        txtField1.setBounds(105, 20, 100, 20);
        this.add(txtField1);

        emptyString = "Value goes here...";

        txtField1.addKeyListener(new KeyAdapter() {

            @Override
            public void keyTyped(KeyEvent e) {

                if (txtField1.getText().equals(emptyString)) {
                    txtField1.setText("");
                    txtField1.setFont(new Font("Dialog", Font.PLAIN, 12));
                }

                char d = e.getKeyChar();
                if ((d == 46 && txtField1.getText().contains(e.getKeyChar() + "")) || (d < 48 || d > 57) && d != 46 /*&& d!=8*/) {
                    e.consume();
                }
            }
        });

        label2 = new JLabel("2nd number  :");
        label2.setBounds(19, 50, 100, 20);
        this.add(label2);

        txtField2 = new JTextField();
        txtField2.setBounds(105, 50, 100, 20);
        this.add(txtField2);

        txtField2.addKeyListener(new KeyAdapter() {

            @Override
            public void keyTyped(KeyEvent e) {

                if (txtField2.getText().equals(emptyString)) {
                    txtField2.setText("");
                    txtField2.setFont(new Font("Dialog", Font.PLAIN, 12));
                }

                char d = e.getKeyChar();
                if ((d == 46 && txtField2.getText().contains(e.getKeyChar() + "")) || (d < 48 || d > 57) && d != 46 /*&& d!=8*/) {
                    e.consume();
                }
            }
        });

    }

}
