import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by kaaml on 16.05.16.
 */
public class FileTransfer extends Thread {
    private File fileToSend;
    private FileInputStream fis;
    private BufferedInputStream bis;

    public FileTransfer(File f ){
        fileToSend = f;
    }
    public void startU(String parameters) {
        String par[] = parameters.split( " " );
        int bytesRead;
        int current = 0;
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        Socket sock = null;
        try {
            sock = new Socket( par[0],  Integer.parseInt( par[1] ));
            // send file
            byte [] mybytearray  = new byte [(int)fileToSend.length()];
            fis = new FileInputStream(fileToSend);
            bis = new BufferedInputStream(fis);
            bis.read(mybytearray,0,mybytearray.length);
            OutputStream os = sock.getOutputStream();
            os.write(mybytearray,0,mybytearray.length);
            os.flush();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) fos.close();
                if (bos != null) bos.close();
                if (sock != null) sock.close();
            }catch (IOException e ){
                System.out.println( e.getMessage() );
            }
        }
    }
    public void startD( String parameters ) {
        String par[] = parameters.split( " " );
        int bytesRead;
        int current = 0;
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        Socket sock = null;
        try {
            sock = new Socket(par[0], Integer.parseInt( par[1]));

            // receive file
            byte [] mybytearray  = new byte [ Integer.parseInt( par[3]) ];
            InputStream is = sock.getInputStream();
            File f = new File( par[4] );
            fos = new FileOutputStream( f );
            bos = new BufferedOutputStream(fos);
            bytesRead = is.read(mybytearray,0,mybytearray.length);
            current = bytesRead;

            do {
                bytesRead =
                        is.read(mybytearray, current, (mybytearray.length-current));
                if(bytesRead >= 0) current += bytesRead;
            } while(bytesRead > -1);

            bos.write(mybytearray, 0 , current);
            bos.flush();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) fos.close();
                if (bos != null) bos.close();
                if (sock != null) sock.close();
            }catch ( IOException e ){
                System.out.println( e.getMessage() );
            }
        }
    }




}
