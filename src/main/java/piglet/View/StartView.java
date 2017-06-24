package piglet.View;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Uprzejmy on 24.06.2017.
 */
public class StartView {
    private JLabel welcomeTextLabel;
    private JLabel selectWorkingDirectoryLabel;
    private JButton selectWorkingDirectoryButton;
    private JPanel mainPanel;
    JFileChooser fileChooser;

    private JFrame frame;

    public StartView()
    {
        selectWorkingDirectoryButton.addActionListener(e -> selectWorkingDirectoryAction());

        fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fileChooser.setAcceptAllFileFilterUsed(false);

        frame = new JFrame("admin");
        frame.setContentPane(mainPanel);
        frame.setMinimumSize(new Dimension(550,350));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null); //have to be set after frame.pack(). Make the window appear in the main monitor center
        frame.setVisible(true);
    }

    private void selectWorkingDirectoryAction()
    {
        fileChooser.showOpenDialog(mainPanel);
    }

    public void setVisible(boolean visible)
    {
        frame.setVisible(visible);
    }

    public JFileChooser getFileChooser()
    {
        return fileChooser;
    }
}
