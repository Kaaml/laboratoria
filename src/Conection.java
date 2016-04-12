import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


/**
 * Created by kaaml on 01.04.16.
 */
public class Conection {
    private Socket ircSocket;
    private BufferedReader socketIn;
    private PrintWriter socketOut;
    static private int defaultPort = 3733;
    //private HashMap<String, Tab > activeChanels;

    public Conection( String host, UserConfig cfg, int port ) {

        try {
            ircSocket = new Socket(host, port);
            socketIn = new BufferedReader(new InputStreamReader(ircSocket.getInputStream()));
            socketOut = new PrintWriter(ircSocket.getOutputStream(), true);
        } catch (Exception e) {
            System.out.println("**Exception handled on Connection");
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }
        login( cfg.getUserName(), cfg.getServerPassword() );
        socketOut.flush();
    }
    public Conection(String host, UserConfig cfg ) {
        this(host, cfg, defaultPort );
    }
    public void listen( Sample sample) throws IOException {

        String responseFromServer = null;
        while ((responseFromServer = socketIn.readLine()) != null) {

            switch (responseFromServer) {
                case "dupa":
                    System.out.println("no to dupa");
                    break;
                default:
                    System.out.println("Nie znana komenda servera");
                    System.out.println("Response from server = '" + responseFromServer + "' ");
            }
        }
    }
    public synchronized void  login( String user, String pw ) {
        String msg = "PASS " + user + " " + pw + " ";
        socketOut.println( msg );
    }
    //target can be user or chanel
    public synchronized void sendMessage( String target, String message ) {
        String msg = "MSG " + target + " " + message;
        socketOut.println( msg  );
    }
    public synchronized void joinToChannel( String chanelName ) {
        String msg = "JOIN " + chanelName;
        socketOut.println(msg);
    }
    public synchronized void quitMessage() {
        String msg = "QUIT";
        socketOut.println(msg);
    }
    public synchronized void SendRequest( String msg ){
        socketOut.println( msg );
        socketOut.flush();
    }
}
