import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

/**
 * Created by kaaml on 22.03.16.
 */
public class login extends JFrame {
    private JTextField loginText;
    private JPasswordField passwordText;
    private JRadioButton saveSettingsRadio;
    private JButton loginButton;
    private JPanel root;
    private JTextField serverText;
    private JLabel loginLabel;
    private JLabel passwordLabel;
    private JPasswordField serverPasswordText;
    private JFrame frame;

    private boolean saveSettings;
    private UserConfig userConfig;


    public login() {

        init2();
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("Login button clicked");
                String login = loginText.getText();
                String passw = passwordText.getPassword().toString();
                String serverPassword = serverPasswordText.getPassword().toString();
                String serverName = serverText.getText();
                saveSettings = saveSettingsRadio.isSelected();

                //co tauj się dzieje?
                userConfig = new UserConfig();
                if (serverName.isEmpty()) {
                    serverText.setBackground(Color.red);
                    serverText.grabFocus();
                }
                if (login.isEmpty()) {
                    loginText.setBackground(Color.red);
                    loginText.grabFocus();
                }
                if (!login.isEmpty() && !serverName.isEmpty()) {
                    userConfig.setServerName(serverName);
                    userConfig.setServerPassword(serverPassword);
                    userConfig.setUserName(login);
                    userConfig.setUserPassword(passw);
                    userConfig.storeSettings(saveSettings);
                    //TODO: zrobić to lepiej
                    setVisible(false);
                    System.out.println("tutaj okno logowania powinno sie zamknac");

                    //frame.dispatchEvent( new WindowEvent( frame, WindowEvent.WINDOW_CLOSING ) );

                }
            }
        });

    }

    private void addLoginButtonListner() {

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.print("Login button clicked");
                String login = loginText.getText();
                String passw = passwordText.getPassword().toString();
                String serverName = serverText.getText();
                saveSettings = saveSettingsRadio.isSelected();

                // TODO: focus on text field when missing
                if (login.isEmpty()) {
                    setVisible(true);
                    loginText.setBackground(Color.red);
                    //loginText.requestFocusInWindow();
                }
                if (serverName.isEmpty()) {

                    serverText.setBackground(Color.red);
                    pack();
                    setVisible(true);
                    //serverText.requestFocus();

                }
            }
        });
    }

    public void init() {
        //erConfig = c;
        frame = new JFrame("login");
        frame.setContentPane(new login().root);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setMinimumSize( new Dimension(600, 500 ));
        frame.pack();
        frame.setVisible(true);
        frame.setAlwaysOnTop(true);

        //addLoginButtonListner();
    }

    public void init2() {
        this.setContentPane(this.root);
        //this.setDefaultCloseOperation();
        this.pack();
        this.setVisible(true);
        this.setAlwaysOnTop(true);
    }

    public UserConfig GetConfig() {
        return userConfig;
    }

    public void setUserConfig(UserConfig us) {
        userConfig = us;
    }

}

