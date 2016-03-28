import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

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


    public Sample() {
        this.setContentPane(this.rootPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(600, 500));

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        menuBar.add(menu);
        JMenuItem menuItem = new JMenuItem("Login", KeyEvent.VK_T);
        JMenuItem exit = new JMenuItem("Exit", KeyEvent.VK_ESCAPE);

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        });
        menuItem.getAccessibleContext().setAccessibleDescription(
                "Login");

        menu.add(menuItem);
        menu.add(exit);
        this.setJMenuBar(menuBar);
        //delete after test ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        Tab[] abc = new Tab[3];
        abc[0] = CreatePanel();
        abc[1] = CreatePanel();
        //abc[1].setSomething();
        // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        this.pack();
        this.setVisible(true);
    }

    private Tab CreatePanel() {
        Tab nowa = new Tab(tabbedPane1, "jakas_nazwa");
        return nowa;
    }

    //public static void main(String[] args) {
    public void mainFunc() {
        String homeDirectory = System.getProperty("user.home");
        System.out.println("Home directory is: " + homeDirectory + File.separator + "settings.ini");
        File settingsFile = new File(homeDirectory + File.separator + "settings.ini");
        UserConfig userConfig = new UserConfig();

        if (settingsFile.exists() && !settingsFile.isDirectory()) {
            try {
                userConfig.readConfigFromFile(settingsFile);
            } catch (IOException e) {
                System.out.println("Cannot read from config file. Exception handling");
                System.out.println(e.getMessage());
            }
        } else {
            login loginFrame = new login(userConfig);
        }
        File f2 = new File(homeDirectory + "/settings.ini");


        try {
            userConfig.saveConfigurationToFile(f2);
        } catch (IOException e) {
            System.out.println("cos poszlo nie tak");
        }


    }
}


