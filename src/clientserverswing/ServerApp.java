package clientserverswing;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author: Dejan Smiljić; e-mail: dej4n.s@gmail.com
 *
 */
public class ServerApp {

    private static ServerSocket serverSocket = null;
    private static Socket clientConnection = null;
    private static String statusServer = null;
    private static int clientCount = 0;

    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            UIManager.getDefaults().put("Button.showMnemonics", Boolean.TRUE);// ukoliko je Windows nativni OS
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException multiExc) {
            System.out.println(multiExc);
        }

        ServerGUI mainWindowServer = new ServerGUI();
        mainWindowServer.setAlwaysOnTop(true);
        try {
            serverSocket = new ServerSocket(1000);
            statusServer = String.format("\n Server startup completed\n Server @ %s [Active, listening]", serverSocket.getLocalSocketAddress());
            ServerGUI.textArea.append(statusServer);
            System.out.println("Server startup completed");
        } catch (IOException ioExc) {
            System.out.println(ioExc);
        }
        mainWindowServer.setVisible(true);

        while (true) {
            try {
                clientConnection = serverSocket.accept();
                clientCount++;
                ServerLogic server = new ServerLogic(clientConnection, clientCount);
                Thread thread = new Thread(server);
                thread.start();
            } catch (IOException ioExc) {
                System.out.println(ioExc);
            }

        }
    }

}
