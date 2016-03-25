import java.io.File;
import java.io.IOException;
import java.io.SyncFailedException;

import org.ini4j.*;
/**
 * Created by kaaml on 25.03.16.
 */
public class UserConfig {
    private String userName;
    private String password;
    private String serverAdress;
    private String passwordToServer;
    //TODO: zapamietac kanaly i je wczytywac odrazu

    public UserConfig(){

    }
    public UserConfig( String name, String serverPath, String serverPassword ){
        userName = name;
        serverAdress = serverPath;
        passwordToServer = serverPassword;
    }

    public void readConfigFromFile(File f ) throws IOException {
        //TODO: dokonczyc konfiguracje o kanaly
            Wini ini = new Wini(f);
            userName = ini.get("user", String.class);
            password = ini.get("userPassword", String.class);
            serverAdress = ini.get("server", String.class);
            passwordToServer = ini.get("serverPassword", String.class);
    }
}
