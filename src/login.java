import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by kaaml on 22.03.16.
 */
public class login {
    private JTextField loginText;
    private JPasswordField passwordText;
    private JRadioButton saveSettingsRadio;
    private JButton loginButton;
    private JPanel root;
    private JTextField serverText;
    private JLabel loginLabel;
    private JLabel passwordLabel;
    private String login;
    private String passw;
    private String serverName;
    private boolean saveSettings;
    private UserConfig userConfig;


    private void addLoginButtonListner() {

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                login = loginText.getText();
                passw = passwordText.getPassword().toString();
                serverName = serverText.getText();
                saveSettings = saveSettingsRadio.isSelected();

                if (login.isEmpty() && serverName.isEmpty()) {
                    System.out.println("login i server name jest puste");
                }
            }
        });
    }
    public void invoke( UserConfig c ){
        userConfig = c;
        JFrame frame = new JFrame("login" );
        frame.setContentPane( new login().root );
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE );
        //frame.setMinimumSize( new Dimension(600, 500 ));
        frame.pack();
        frame.setVisible(true );
        frame.setAlwaysOnTop(true );
        addLoginButtonListner();
    }


}

