import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by kaaml on 12.04.16.
 */
public class DBConnection {
    private Connection connection = null;

    DBConnection(){
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:baza.db");
        }catch( Exception e ){
            System.out.println( e.getMessage() );
        }

    }
    public synchronized void addUser( String name, String password, String moreInfo ) throws Exception{
        Statement smt = null;

        smt = connection.createStatement();
        String sqlQuery = "INSERT INTO users (name, password, info)  VALUES( " + name + ", " + password + ", " + moreInfo + " );";
        smt.execute(sqlQuery);
        smt.close();
        connection.commit();
    }
    public  synchronized boolean checkUser( String name, String password ) throws  Exception{
        Statement smt = null;

        smt = connection.createStatement();
        String sqlQuery = "SELECT name, password FROM users WHERE name='" + name + "' ;";
        ResultSet res  = smt.executeQuery( sqlQuery );
        while( res.next() ){
            String dbname = res.getString( "name" );
            if( dbname == name ){
                String pw = res.getString( "password" );
                res.close();
                smt.close();
                if( pw == password ){
                    return true;
                }else{
                    return false;
                }
            }
        }
        res.close();
        smt.close();
        return false;

    }
    public synchronized void deleteUser( String name ) throws Exception{
        Statement smt = null;
        smt = connection.createStatement();
        String sqlQuery = "DELETE FROM users wher name='" + name+ "' ; ";
        smt.executeUpdate(sqlQuery);
        connection.commit();
    }
}
