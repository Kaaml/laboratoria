import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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


    private boolean saveSettings;
    private UserConfig userConfig;


    public login( UserConfig userCfg) {

        userConfig = userCfg;
        init();
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("Login button clicked");
                String login = loginText.getText();
                String passw = passwordText.getPassword().toString();
                String serverPassword = serverPasswordText.getPassword().toString();
                String serverName = serverText.getText();
                saveSettings = saveSettingsRadio.isSelected();

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
                    dispose();
                    System.out.println("tutaj okno logowania powinno sie zamknac");
                }
            }
        });

    }

    public void init() {
        this.setContentPane(this.root);
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

