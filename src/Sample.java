import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

/**
 * Created by kaaml on 21.03.16.
 *
 *  lowerCamelCaseConvention for private members and funcions
 *  UpperCamelCaseConvention for class names
 *  CONSTANTS by UPERCASE SEPARATED BY UNDERSCORE ____
 *
 */
public class Sample extends JFrame{
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


    public Sample() {
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JPanel panel = new JPanel( false );
                panel.setLayout( new BorderLayout());

                JPanel south = new JPanel();
                JPanel east = new JPanel();
                JPanel west = new JPanel();
                JLabel lab = new JLabel("dpa kuaapaaadf" );
                JLabel lab2 = new JLabel( "kurwa jego mac!" );
                //south.add( lab, BorderLayout.SOUTH );
                //east.add(lab2, BorderLayout.BEFORE_LINE_BEGINS );
                //botom ->south
                JTextField msg = new JTextField();
                msg.setPreferredSize( new Dimension(450, 30 ));
                JButton send = new JButton( "send" );
                south.add( msg, BorderLayout.BEFORE_LINE_BEGINS );
                south.add( send, BorderLayout.LINE_START );
                //LEFT PANEL
                JList list = new JList();
                list.setBorder( new TitledBorder("Users on chanel"));
                list.setPreferredSize( new Dimension( 250, 500 ) );
                west.add( list, BorderLayout.CENTER );
                //right panel
                JTextArea area = new JTextArea();
                area.setPreferredSize( new Dimension(300, 500 ));
                east.add( area, BorderLayout.CENTER );

                panel.add( south, BorderLayout.SOUTH );
                panel.add(  west, BorderLayout.WEST );
                panel.add( east, BorderLayout.EAST );

                tabbedPane1.addTab( "kupa", panel );
                tabbedPane1.setMnemonicAt( 2, KeyEvent.VK_1 );



            }
        });
    }
    public void Createmy()
    {
        JPanel panel2 = new JPanel(false );

        String[] dupa = { "adasf",  "adfafas", "adfasfd" };
        JList<String> lista = new JList(dupa);
        Border b = new TitledBorder( "Lis of users" );
        // lista.setBorder( b );
        lista.setPreferredSize( new Dimension(150, 50 ) );

        JPanel panel = new JPanel( false );
        panel.setLayout( new GridLayout( 1, 2 ) );


        //lista.setMinimumSize();
        panel2.add( lista );
        panel.add( panel2 );

        JPanel panel3 = new JPanel( false );
        JTextArea chat = new JTextArea();
        panel3.add( chat );
        panel.add( panel3 );
        pack();
        repaint();
    }
    public static void main(String[] args){

        String homeDirectory = System.getProperty( "user.home" );
            System.out.println( "Home directory is: " + homeDirectory + File.separator + "settings.ini" );
        File settingsFile = new File(  homeDirectory + File.separator + "settings.ini" );
        UserConfig userConfig = new UserConfig();
        if( settingsFile.exists() && !settingsFile.isDirectory()  ){
            try {
                userConfig.readConfigFromFile(settingsFile);
            }catch( IOException e ){
                System.out.println( "Cannot read from config file. Exception handling" );
                System.out.println( e.getMessage() );
            }
        }else {
            login loginFrame = new login();
            loginFrame.invoke( userConfig );
        }
        File f2 = new File( homeDirectory + "/set.ini" );


        try {
            userConfig.saveConfigurationToFile(f2);
        }catch( IOException e ){
            System.out.println("cos poszlo nie tak" );
        }

        JFrame frame = new JFrame("Sample" );
        frame.setContentPane( new Sample().rootPanel );
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE );
        frame.setMinimumSize( new Dimension(600, 500 ));

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu" );
        menuBar.add( menu );

        JMenuItem menuItem = new JMenuItem("Login", KeyEvent.VK_T);
        JMenuItem exit = new JMenuItem( "Exit", KeyEvent.VK_ESCAPE );

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        } );

        menuItem.getAccessibleContext().setAccessibleDescription(
                "Login");

        menu.add( menuItem );
        menu.add(exit);
        frame.setJMenuBar( menuBar );
        frame.pack();
        frame.setVisible(true );
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

}
