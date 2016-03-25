import java.io.File;
import java.io.IOException;
import java.io.SyncFailedException;
import java.lang.reflect.Array;
import java.util.*;

import org.ini4j.Config;
import org.ini4j.Ini;
import org.ini4j.MultiMap;
import org.ini4j.Profile.Section;
/**
 * Created by kaaml on 25.03.16.
 */
@SuppressWarnings("ALL")
public class UserConfig {
    private String userName;
    private String password;
    private String serverAdress;
    private String passwordToServer;
    private boolean storeSettings = true;
    private ArrayList<String> chanelNames = new ArrayList<>();


    public UserConfig(){

    }
    public UserConfig( String name, String serverPath, String serverPassword ){
        userName = name;
        serverAdress = serverPath;
        passwordToServer = serverPassword;
    }

    public void readConfigFromFile(File f ) throws IOException {
        //TODO: dokonczyc konfiguracje o kanaly
        Ini ini = new Ini();
        Config conf = new Config();
        conf.setMultiOption(true );
        conf.setMultiSection(true );
        ini.setConfig( conf );
        ini.load(f);
        userName = ini.get("user", "user" );
        password = ini.get("user", "userPassword");
        serverAdress = ini.get("user", "server");
        passwordToServer = ini.get("user", "serverPassword");
        String ch = ini.get("user", "channels" );
        chanelNames = new ArrayList( Arrays.asList( ch.split( "\\s" ) ) );
        for( String chs :chanelNames )
            chs.replaceAll("\\s", "" );
    }
    public void saveConfigurationToFile( File f ) throws IOException{

        f.createNewFile();
        Ini ini = new Ini( f );
        ini.put( "user", "user", userName );
        ini.put( "user", "userPassword", password );
        ini.put( "user", "server", serverAdress );

        String ch = new String();
        Iterator it = chanelNames.iterator();
        while( it.hasNext() ){
           ch += it.next() + " ";
       }
        ini.put( "user", "channels", ch );

        ini.store();
        System.out.println("Stored ini file with configuration" );
    }

    public void SetUserName( String name ){
        userName = name;
    }
    public void SetUserPassword( String pass ){
        password = pass;
    }
    public void SetServerName( String server ) {
        serverAdress = server;
    }
    public void SetServerPassword( String servPass ){
        passwordToServer = servPass;
    }
    public void StoreSettings( boolean val ){
        storeSettings = val;
    }
    public boolean StoreSettings(){
        return storeSettings;
    }
    private void addChanelName( String chName ){
        chanelNames.add( chName );
    }

}
