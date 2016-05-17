import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by kaaml on 21.03.16.
 * <p/>
 * lowerCamelCaseConvention for private members and funcions
 * UpperCamelCaseConvention for class names
 * CONSTANTS by UPERCASE SEPARATED BY UNDERSCORE ____
 */
public class Sample extends JFrame {
    private JPanel rootPanel;
    private JTabbedPane tabbedPane1;
    private JButton button1;
    private JTextArea textArea1;
    private JList list1;
    private JPanel Bla;
    private JPanel dsafas;
    private JTextField textField1;
    private JList list2;
    private JTextArea sdfsafTextArea;
    private JScrollBar scrollBar1;
    private Conection con;
    private File settingsFile;
    private HashMap<String, Tab > activeTabs = new HashMap<>();
    FileTransfer fileToSend = null;

    public Sample() {
        this.setContentPane(this.rootPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(600, 500));

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        menuBar.add(menu);
        JMenuItem menuItem = new JMenuItem("Login", KeyEvent.VK_T);
        JMenuItem exit = new JMenuItem("Exit", KeyEvent.VK_ESCAPE);

        exit.addActionListener(actionEvent -> System.exit(0));
        menuItem.getAccessibleContext().setAccessibleDescription(
                "Login");

        menu.add(menuItem);
        menu.add(exit);
        //dodanie wysyłania pliku w menu
        JMenuItem sendFile = new JMenuItem( "Upload File" );
        sendFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooseFile = new JFileChooser();
                int retVal = chooseFile.showOpenDialog( Sample.this );
                if (retVal == JFileChooser.APPROVE_OPTION) {
                    File file = chooseFile.getSelectedFile();
                    fileToSend =  new FileTransfer( file ) ;
                }
            }
        });
        menu.add( sendFile );
        menu.add( menu );
        this.setJMenuBar(menuBar);

        //delete after test ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        //Tab[] abc = new Tab[3];
        activeTabs.put( "#default", createTab("defa") );
        //abc[0] = createTab();
       // abc[1] = CreatePanel();
        //abc[1].setSomething();
        // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        this.pack();
        this.setVisible(true);
    }

    private Tab createTab(String name) {
        Tab nowa = new Tab(tabbedPane1, name, this);
        activeTabs.put(name, nowa );
        return nowa;
    }

    //public static void main(String[] args) {
    public void mainFunc() {
        String homeDirectory = System.getProperty("user.home");
        System.out.println("Home directory is: " + homeDirectory + File.separator + "settings.ini");
        settingsFile = new File(homeDirectory + File.separator + "settings.ini");
        UserConfig userConfig = new UserConfig();

        if (settingsFile.exists() && !settingsFile.isDirectory()) {
            try {
                userConfig.readConfigFromFile(settingsFile);
                exe( userConfig );
            } catch (IOException e) {
                System.out.println("Cannot read from config file. Exception handling");
                System.out.println(e.getMessage());
            }
        } else {
            login loginFrame = new login(userConfig, this);
        }

    }
    public void exe( UserConfig userConfig){
        String[] servName = userConfig.getServerName().split( ":",2 );
        String host = servName[0];
        int port = 3733;        //default port
        if( servName.length == 2 )
            port = Integer.parseInt( servName[1]);
        System.out.println( "Connection parametrs" );
        System.out.println( "HOST: " + host );
        System.out.println( "PORT: " + port );
        System.out.println( "USER: " + userConfig.getUserName() );
        con = new Conection( host, userConfig, port);
        Runnable r1 = () -> {
            try {
                con.listen( this );
            }catch( Exception e ){
                System.out.println( e.getMessage() );
            }
        };



        try {
            userConfig.saveConfigurationToFile(settingsFile);
        } catch (IOException e) {
            System.out.println("cos poszlo nie tak");
        }

    }
    public void handleMessageFromServer( String msg ){
        //tutaj cała logika
        String[] tags = msg.split(" ",2 );
        switch (tags[0] ){
            case "ERROR":
                System.out.println("Last operation ERROR" );
                //TODO inform user about it status bar or etc
                break;
            case "JOINED_TO":
                createTab( tags[1] );
                break;
            case "MSG":
                String[] tokens = tags[1].split(" ", 2 );
                Tab tab = activeTabs.get( tokens[0] );
                if( tab == null ){
                    tab = activeTabs.get( "#default" );
                }
                //tab.appendMessage( tokens[1] );
                break;
            case "FILEU":
                fileToSend.startU( tags[1]);
                break;
            case "FILED":
                fileToSend.startD( tags[1] );
                break;
            default:
                System.out.println( "Undefined response from server'" );
        }
    }
    public void handleMessageFromForm( String msg, Tab tab ){
        msg = msg.trim();
        if( msg.charAt(0 )  != '/' ){
            con.sendMessage( "default", msg );

        }else {
            String[] tags = msg.split(" ", 2);   //ie: /join #chanelname
            switch (tags[0].toLowerCase()) {
                case "/join":
                    //send msg to server and create new tab
                    con.joinToChannel(tags[1]);
                    break;
                case "/msg":
                    //jak wyżej
                    String[] tokens = tags[1].split(" ", 2);
                    con.sendMessage(tokens[0], tokens[1]);
                    break;
                case "/quit":
                    this.setVisible(false);
                    this.dispose();
                    System.exit(0);
                    break;
                default:
                    System.out.println("bledy w wiadomosci z taba");
            }
        }
    }

}


