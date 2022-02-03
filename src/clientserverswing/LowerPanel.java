package clientserverswing;

import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

/**
 *
 * @author: Dejan Smiljić; e-mail: dej4n.s@gmail.com
 *
 */
public class LowerPanel extends JPanel{
    
    private Border border;
    protected static Border borderOff;
    protected static JLabel labelResult;
    
    public LowerPanel(){
        
        this.setLayout(null);
        this.setBounds(5, 92, 222, 46);
        border = BorderFactory.createTitledBorder(border, "Server", TitledBorder.TRAILING, TitledBorder.BOTTOM, new Font("Courier New", Font.ITALIC, 11));
        borderOff = BorderFactory.createTitledBorder(borderOff, "Server unavailable", TitledBorder.TRAILING, TitledBorder.BOTTOM, new Font("Courier New", Font.ITALIC, 11));
        this.setBorder(border);
        
        labelResult = new JLabel("Result : ");
        //labelResult.setFont(new Font("Courier New", Font.ITALIC+Font.BOLD, 13));
        labelResult.setBounds(20, 15, 150, 16);
        this.add(labelResult);
    }

}
