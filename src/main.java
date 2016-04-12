/**
 * Created by kaaml on 23.02.16.
 */


public class main{
    public static void main(String[] args) {
       IRCServer ircServer = new IRCServer("kaaml server's on localhost", 1337 );
        try {
            ircServer.Start();
        }catch( Exception e )
        {
            System.out.println( e.getMessage() );
        }
        ircServer.CleanUp();

    }

}
