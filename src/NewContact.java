import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;

/**
 * Created by kaaml on 20.03.16.
 */
public class NewContact extends JFrame {
    private JPanel newContactRootPanel;
    private JPanel westLabelPanel;
    private JPanel eastTextPanel;
    private JPanel southButtons;
    private JTextField name;
    private JTextField surname;
    private JTextField adress;
    private JButton button1;
    private JButton button2;
    private JTabbedPane tabbedPane1;
    private JLabel text;
    private JButton button3;
    private JPanel card;


    public NewContact() {
        super("Contact panel");
        name.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //przycisk naciśniety

                JPanel panel = new JPanel(false);
                JLabel filler = new JLabel("dupa dupa dupa");
                filler.setHorizontalAlignment(JLabel.CENTER);
                panel.setLayout(new GridLayout(1, 1));
                panel.add(filler);

                tabbedPane1.addTab( "kupa", panel );


            }
        });

        card.addComponentListener(new ComponentAdapter() {
        });
    }
        public static void main( String[] args){
            JFrame frame = new JFrame("NewContact" );
            frame.setContentPane( new NewContact().newContactRootPanel );
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE );
            frame.pack();
            frame.setVisible(true );
        }
}
