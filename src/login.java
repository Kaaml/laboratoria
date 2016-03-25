import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by kaaml on 22.03.16.
 */
public class login extends Frame {
    private JTextField loginText;
    private JPasswordField passwordText;
    private JRadioButton saveSettingsRadio;
    private JButton loginButton;
    private JPanel root;
    private JTextField serverText;
    private JLabel loginLabel;
    private JLabel passwordLabel;

    private boolean saveSettings;
    private UserConfig userConfig;


    public login() {
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("Login button clicked");
                String login  = loginText.getText();
                String passw = passwordText.getPassword().toString();
                String serverName = serverText.getText();
                saveSettings = saveSettingsRadio.isSelected();

                if( serverName.isEmpty() ){
                    serverText.setBackground( Color.red );
                    serverText.grabFocus();
                }
                if (login.isEmpty() ) {
                    loginText.setBackground(Color.red);
                    loginText.grabFocus();
                }
            }
        });
    }

    private void addLoginButtonListner() {

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.print("Login button clicked");
                String login  = loginText.getText();
                String passw = passwordText.getPassword().toString();
                String serverName = serverText.getText();
                saveSettings = saveSettingsRadio.isSelected();

                // TODO: focus on text field when missing
                if (login.isEmpty() ) {
                    setVisible(true);
                    loginText.setBackground(Color.red);
                    //loginText.requestFocusInWindow();
                }
                if( serverName.isEmpty() ){

                    serverText.setBackground( Color.red );
                    pack();
                    setVisible(true);
                    //serverText.requestFocus();
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
        //addLoginButtonListner();
    }


}

