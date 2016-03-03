/**
 * Created by kaaml on 23.02.16.
 */
import java.io.*;
import java.net.Socket;

public class server implements Runnable{


    public void run(){
        //runin
        System.out.println( "Thread start!  ");
    }
    public static void main(String[] args) {
        try{
            Socket s=new Socket("localhost",1337);
            DataOutputStream dout =new DataOutputStream(s.getOutputStream());
            BufferedReader dint = new BufferedReader( new InputStreamReader( s.getInputStream()));
            DataInputStream dint2 = new DataInputStream( s.getInputStream() );
            System.out.println( dint.readLine() );
            System.out.println( dint2.readByte() );
            dout.writeUTF("Hello Server");
            dout.flush();
            dout.close();
            s.close();
        }catch(Exception e){System.out.println(e);}
    }
}
