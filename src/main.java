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

        try (
                ServerSocket s = new ServerSocket(1337);
                Socket client = s.accept();
                PrintWriter out = new PrintWriter(client.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        ) {
            //jesli wszystko ok mozna dzialac na obiektach
            //String msg;
            while ((msg = in.readLine()) != null) {
                System.out.println(msg);
                if (msg.equals("bye"))
                    break;
            }


        } catch (Exception e) {
            System.out.println(e);
        }


        System.out.println("Gilberto");

    }
}
