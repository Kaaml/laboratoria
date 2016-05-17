import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by kaaml on 28.03.16.
 */
public class Tab {
    private final JTextField textField1;
    private final JButton button1;
    private final JList list2;
    private final JTextArea sdfsafTextArea;
    private JPanel Bla;
    private JPanel dsafas;
    private JList list1;
    private JTextArea textArea1;
    private Sample sampleForm;
    private Tab currentTab;

    Tab(JTabbedPane tabbedPane1, String tabName, Sample sample ){


        currentTab = this;
        sampleForm = sample;
       final JPanel panel2 = new JPanel();
        panel2.setLayout(new BorderLayout(0, 0));
        tabbedPane1.addTab("Untitled", panel2);
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel2.add(panel3, BorderLayout.SOUTH);
        textField1 = new JTextField();
        textField1.setText("");
        textField1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if( e.getKeyCode() == KeyEvent.VK_ENTER ){
                    //sdfsafTextArea.append( textField1.getText() + "\n" )
                    sampleForm.handleMessageFromForm( textField1.getText(), currentTab );
                    textField1.setText("");
                }
            }
        });
        panel3.add(textField1, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        button1 = new JButton();
        button1.setText("Button");
        panel3.add(button1, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel2.add(panel4, BorderLayout.WEST);
        panel4.setBorder(BorderFactory.createTitledBorder("List of users"));
        list2 = new JList();
        final DefaultListModel defaultListModel1 = new DefaultListModel();
        list2.setModel(defaultListModel1);
        panel4.add(list2, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        final JPanel panel5 = new JPanel();
        panel5.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel2.add(panel5, BorderLayout.CENTER);
        sdfsafTextArea = new JTextArea();
        sdfsafTextArea.setText("sdfsaf");
        //lets try it

        initAutosugestion();

        panel5.add(sdfsafTextArea, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));

    }
    public void setSomething(){
        textArea1.setText( "asdfgg" );
        textArea1.append( "hjjkkiij\n");
        textArea1.append( "dodajmy po ***** | ***** :-)");
    }
    private void initAutosugestion(){
        String[] suggestWords = {
                "/quit", "/msg", "/exit", "/join", "/clear"
        };
        //get root frame
        JFrame frame = (JFrame)SwingUtilities.getRoot(sampleForm);
        AutoSuggestor autoSuggestor = new AutoSuggestor(textField1,
                frame, new ArrayList( Arrays.asList( suggestWords) ), Color.WHITE.brighter(), Color.BLACK, Color.GRAY, 0.75f) {
            @Override
            boolean wordTyped(String typedWord) {
                //System.out.println(typedWord);
                return super.wordTyped(typedWord);
            }
        };



    }
}
