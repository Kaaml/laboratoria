/**
 * Created by kaaml on 23.02.16.
 */

import com.sun.corba.se.spi.activation.Server;

import java.io.*;
import java.net.*;

public class main{
    public static void main(String[] args) {
        int portNumber = 1337;

        /*ServerSocket serverSocket = null;
        try{
            serverSocket = new ServerSocket( portNumber );
            Socket s = serverSocket.accept();
           // s.bind( new InetSocketAddress("localhost", 1337 )  );
            DataInputStream dis = new DataInputStream( s.getInputStream() );
            String str = (String)dis.readLine();

            System.out.println("message=" + str );
            s.close();
        }catch( Exception e )
        {
            System.out.println( e );
        }*/

    String msg = "";
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
                ClientServiceThread clientThread = new ClientServiceThread( client );
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


        //System.out.println("Gilberto");

    }
}
