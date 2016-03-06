import com.sun.security.ntlm.Client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by kaaml on 03.03.16.
 */

//package ClientServiceThread;
public class ClientServiceThread extends Thread {
    Thread activeThread = null;
    Socket clientSocket = null;
    ArrayList<ClientServiceThread> cli = null;
    private PrintWriter outWrit = null;

    private enum Command {
        NICK() {
            @Override
            public void run() {
                System.out.println("NICK(1,1)");
            }
        },
        PASS(){
            @Override
            public void run() {
                System.out.println("PASS(1,4)");
            }
        };
        public abstract void run();
    }
    public ClientServiceThread(Socket conn, ArrayList<ClientServiceThread> thr){
        clientSocket = conn;
        cli = thr;
        System.out.println( "THREAD[ " + this.getId() + " ] has started" );
    }

    public void run()
    {
        System.out.println( "CONNECTION: Accepted connection from " + clientSocket.getInetAddress() );
        try(

                BufferedReader in = new BufferedReader( new InputStreamReader( clientSocket.getInputStream() ) );
                ) {
            outWrit = new PrintWriter( clientSocket.getOutputStream() );
            String msg;
            while ((msg = in.readLine()) != null) {
                System.out.println(msg);
                if (msg.equals("exit"))
                    break;
                if( msg.equalsIgnoreCase("send" ) )
                    for( ClientServiceThread i : cli )
                        i.send("gowno");

            }
        }catch( Exception e )
        {
            System.out.println( e.getMessage() );
        }
        finally {
            System.out.println( "THREAD[ " + this.getId() + "] has been closed" );

        }
    }
    public void send( String msg )
    {
        outWrit.write( msg );
        outWrit.flush();
    }
    public void ParseCommand( String line )
    {
        System.out.println( "Parsing line: [" + line + " ] " );

        if( line.startsWith( ":" ) )
        {
            String[] tokens = line.split( " ", 2 );
            Command c = Command.valueOf( tokens[0] );
            c.run();
        }
    }

}
