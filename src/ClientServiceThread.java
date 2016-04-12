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
    //Thread activeThread = null;
    Socket clientSocket = null;
    ArrayList<ClientServiceThread> cli = null;
    private PrintWriter outWrit = null;
    String clientName;
    IRCServer mainServer = null;
    private enum Command {
        NICK() {
            @Override
            public void run(String[] msg, IRCServer server, ClientServiceThread own) {
                System.out.println(msg);
                own.RegisterClient( msg[0] );
            }
        },
        PASS(){
            @Override
            public void run(String[] msg, IRCServer server,  ClientServiceThread own)  {
                System.out.println("PASS(1,4)");
                own.PassToServer(msg[1] );
            }
        },
        REG(){
            @Override
            public void run(String[] msg, IRCServer server, ClientServiceThread own ){
                try{
                    server.RegisterClientOnServer(msg[0], own );
                }catch( Exception e)
                {
                    System.out.println( e.getMessage() );
                }
            }
        };
        public abstract void run(String[] msg, IRCServer server, ClientServiceThread own) ;
    }
    public ClientServiceThread(Socket conn, IRCServer server){
        clientSocket = conn;
        mainServer = server;
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
                        i.send("Alle user msg");
                ParseCommand( msg );

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
        // [:]<kto> <polecenie> <parametry>
        // user MSG target message
        // user joing #channel1
        // user register nickname password
        // pass username password
        // user usei real_informations
        // user msg target msg_
        // user quit msg
        // and more sooon ;)s
            String[] tokens = line.split( " ", 2 );
            if( tokens[0].equalsIgnoreCase("pass" ) ){
                String[] argsOfCommand = tokens[2].split(" " );
                Command x = Command.valueOf( tokens[0] );
                if( x == null ){
                    send( "command not found" );
                    return;
                }else {
                    x.run(argsOfCommand, mainServer, this);
                }
            }

    }
    public void RegisterClient(String name)
    {
        clientName = name;
        try {
            mainServer.RegisterClientOnServer(name, this);
            send( "User registred as " + name );
        }catch( IRCServer.UserExist e )
        {
            try{
                mainServer.RegisterClientOnServer( name+"_" , this );
                send( "User registred as " + name + "_" );
            }catch( IRCServer.UserExist e2 )
            {
                System.out.println( "User already exist on server" );
                send( "ERR_NICKNAMEINUSE" );
                //send( "Nick already exist on server" );
            }
        }

    }
    public void PassToServer( String password )
    {
        if( ! mainServer.IsCorrectPassword( password ) )
        {
            send( "invalid password to server. You are disconnected!" );
            try {
                clientSocket.close();
            }catch( Exception e )
            {
                System.out.println( e.getStackTrace() );
            }
            this.stop();
        }
    }

}
