import com.sun.corba.se.spi.activation.Server;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by kaaml on 06.03.16.
 */
public class IRCServer {
    private String serverName = "Kaaml servers";
    private int serverPort = 1337;      //default is 1337

    HashMap<String, ClientServiceThread> clientsOnChannels = new HashMap<>();
    ArrayList<ClientServiceThread> connectedClients = new ArrayList<ClientServiceThread>();

    public IRCServer( String ServerName, int Port ){
        serverName = ServerName;
        serverPort = Port;
        try {
            InetAddress adr = InetAddress.getLocalHost();
            System.out.println( "Server [ " + serverName + "@" + adr.getHostAddress() + " ] has started" );
        }catch ( Exception e )
        {

        }
    }


    public void Start()
    {

        ServerSocket server = null;
        try {
            server = new ServerSocket(1337);
        } catch (Exception e) {
            System.out.println(e);
        }

        while( true )
        {
            Socket client = null;
            try {
                client = server.accept();
                if( client.getInetAddress().equals("1.1.1.1.1" ) )
                    break;
                ClientServiceThread clientThread = new ClientServiceThread( client, connectedClients );
                connectedClients.add( clientThread );
                clientThread.start();
            }catch( Exception e )
            {

            }


        }
        //clean up after exit
        try{
            server.close();
        }catch( Exception e )
        {

        }
    }
    public void RegisterClientOnChannel( String chanelName, ClientServiceThread client )
    {
        clientsOnChannels.put( chanelName, client );
    }
    public void CleanUp()
    {


        System.out.println( "Server [ " + serverName + " ] has turned off");
    }
}
