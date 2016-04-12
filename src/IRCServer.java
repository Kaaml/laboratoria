import sun.org.mozilla.javascript.json.JsonParser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
    import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by kaaml on 06.03.16.
 */
public class IRCServer {
    private String serverName = "Kaaml servers";
    private int serverPort = 1337;      //default is 1337
    private String serverPassword = "";
    private boolean isRunning = false;
    HashMap<String, ClientServiceThread> clientsOnChannels = new HashMap<>();
    ArrayList<ClientServiceThread> connectedClients = new ArrayList<ClientServiceThread>();
    HashMap<String, ClientServiceThread> usersOnServer = new HashMap<>();


    public class UserExist extends  Exception{
        public UserExist(String exc)
        {
            super(exc);
        }
        public String getMessage()
        {
            return super.getMessage();
        }
    }
    //czy to tu powinno być?
    public class Channel
    {
        private ArrayList<ClientServiceThread> channelMembers = new ArrayList<>();
        private String name;
        private String topic;

        public void SendToAllMembers( String msg, ClientServiceThread user )
        {
            for( ClientServiceThread i : channelMembers)
                if( user != i )
                    i.send( msg);
        }
        public void MemberJoin( ClientServiceThread user )
        {
            //join msg
            SendToAllMembers( "User: " + user.getName() + " has joined", user  );
            channelMembers.add( user );
        }

    }

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
    public IRCServer( String configFile )
    {
        FileInputStream file = null;
        try {
             file = new FileInputStream(configFile);

        }catch( Exception e )
        {
            System.out.println( e.getMessage() );
        }
       // while( String s = file.toString())

    }

    public void Start() throws Exception
    {
        if( isRunning )
            throw new Exception("server is running" );
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
                ClientServiceThread clientThread = new ClientServiceThread( client, this );
                connectedClients.add( clientThread );
                clientThread.start();
            }catch( Exception e )
            {
                System.out.println( e );
                System.out.println( e.getStackTrace() );
            }


        }
        //clean up after exit
        try{
            server.close();
        }catch( Exception e )
        {
            System.out.println( e.getStackTrace() );
        }
    }
    public void RegisterClientOnServer(String clientName, ClientServiceThread client ) throws UserExist
    {
        //
        ClientServiceThread cl = usersOnServer.get( clientName );
        if( cl == null )
        {
            usersOnServer.put( clientName, client );
        }else
        {
            throw new UserExist( "User exist on channel" );
            //throw UserNameExist
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
    public boolean IsCorrectPassword( String password )
    {
        if( serverPassword.equals( "" ) )
        {
            System.out.println( "server bez hasła" );
            return true;
        }else if( serverPassword.equals(password) )
        {
            return true;
        }
        return false;
    }
}
