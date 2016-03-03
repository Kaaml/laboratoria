import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by kaaml on 24.02.16.
 */
public class syl extends JFrame {
    private JPanel rootPanel;
    private JButton sendButton;
    private JTextField textField1;
    private JTextArea textArea1;
    private JComboBox comboBox1;
    private JLabel lab;

    public syl() {
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //lab.setText("kuwrwa mac dziala");
                lab.setText( textArea1.getText() );
            }
        });
        textArea1.addComponentListener(new ComponentAdapter() {
        });
        textArea1.addContainerListener(new ContainerAdapter() {
        });
        textArea1.addKeyListener(new KeyAdapter() {
        });
    }

    public static void main( String[] args )
    {
        JFrame frame = new JFrame("syl");
        frame.setMinimumSize(  new Dimension( 200, 200 ) );
        frame.setContentPane( new syl().rootPanel );
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        frame.pack();
        frame.setVisible(true );
    }
}
