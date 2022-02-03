package clientserverswing;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author: Dejan Smiljić; e-mail: dej4n.s@gmail.com
 *
 */
public class ClientApp {
    
    public static void main(String[] args) {
        
        try {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        UIManager.getDefaults().put("Button.showMnemonics", Boolean.TRUE);// ukoliko je Windows nativni OS
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException multiExc) {
        System.out.println(multiExc);
        }
        MainFrame mainWindow = new MainFrame();
        mainWindow.setAlwaysOnTop(true);

        mainWindow.setVisible(true);
        
    }

}
