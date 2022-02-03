package clientserverswing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author: Dejan Smiljić; e-mail: dej4n.s@gmail.com
 *
 */
public class ServerLogic implements Runnable {

    private Socket clientConnection;
    private double no1;
    private double no2;
    private static String statusClient;
    private String result;
    private int clientNum;

    public ServerLogic(Socket clientConnection, int clientNum) {
        this.clientConnection = clientConnection;
        this.clientNum = clientNum;
    }

    @Override
    public void run() {

        try (BufferedReader buffIn = new BufferedReader(new InputStreamReader(clientConnection.getInputStream()));
                PrintWriter printWriter = new PrintWriter(clientConnection.getOutputStream(), true);) {
            //tijelo try-catch bloka
            
            /*statusServer = String.format("\n  Server startup completed\nServer @ %s [active, listening]\n", clientConnection.getLocalSocketAddress());
            ServerGUI.textArea.append(statusServer);*/
            //System.out.printf("Server startup completed\nServer @ %s [active, listening]\n", connection.getLocalSocketAddress());
            if (clientConnection.isConnected()) {
                statusClient = String.format("\n\nServer: client process [%s][%s] connected. Total connections: %s", clientConnection.getRemoteSocketAddress(), Thread.currentThread().getName(), clientNum/*Thread.currentThread().getThreadGroup().activeCount() - 1*/);
                ServerGUI.textArea.append(statusClient);
            }
 
            String clientNumbers = buffIn.readLine();
            ServerGUI.textArea.append(String.format("\nData received: %s [%s]", clientNumbers, clientNumbers.getClass().getSimpleName()));
            System.out.println(clientNumbers);// numbers are:.....
            try {
                ServerGUI.textArea.append("\nParsing data ...");
                no1 = Double.parseDouble(clientNumbers.split(",")[0]);
                ServerGUI.textArea.append(String.format("\nsuccess: %.1f [%s]", no1, ((Object)no1).getClass().getSimpleName()));
                no2 = Double.parseDouble(clientNumbers.split(",")[1]);
                ServerGUI.textArea.append(String.format("\nsuccess: %.1f [%s]", no2, ((Object)no2).getClass().getSimpleName()));
            } catch (NumberFormatException nfExc) {
                ServerGUI.textArea.append(String.format("failed!\n%s", nfExc));
                System.out.println(nfExc);
            }
            if(no1 % 1 == 0 && no2 %1 == 0)
                result = String.valueOf((int)(no1 + no2));
            else
                result = String.valueOf(no1 + no2);
            ServerGUI.textArea.append(String.format("\nAdittion result: %s",result));
            ServerGUI.textArea.append("\nSending result ... ");
            printWriter.println(result);
            statusClient = buffIn.readLine();
            ServerGUI.textArea.append(String.format("\nClient [%s] %s", clientConnection.getPort(), statusClient));
            System.out.println(result);

        } catch (IOException ioExc) {
            System.out.println(clientConnection.getRemoteSocketAddress() + " | " + ioExc.getMessage());
        }
        //System.out.println(server.isClosed());

    }

}
