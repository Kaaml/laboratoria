import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by kaaml on 03.03.16.
 */

//package ClientServiceThread;
public class ClientServiceThread extends Thread {
    Thread activeThread = null;
    Socket clientSocket = null;

    public ClientServiceThread(Socket conn ){
        clientSocket = conn;
        System.out.println( "THREAD[ " + this.getId() + " ] has started" );
    }

    public void run()
    {
        System.out.println( "CONNECTION: Accepted connection from " + clientSocket.getInetAddress() );
        try(
                PrintWriter out = new PrintWriter( clientSocket.getOutputStream() );
                BufferedReader in = new BufferedReader( new InputStreamReader( clientSocket.getInputStream() ) );
                ) {
            String msg;
            while ((msg = in.readLine()) != null) {
                System.out.println(msg);
                if (msg.equals("exit"))
                    break;
            }
        }catch( Exception e )
        {
            System.out.println( e.getMessage() );
        }
        finally {
            System.out.println( "THREAD[ " + this.getId() + "] has been closed" );

        }
    }

}
