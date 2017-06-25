package piglet.View;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Uprzejmy on 24.06.2017.
 */
public class StartView {
    private JLabel welcomeTextLabel;
    private JButton selectWorkingDirectoryButton;
    private JPanel mainPanel;
    private JPanel downloadNewConfigurationPanel;
    private JPanel selectWorkingDirectoryPanel;
    private JLabel selectWorkingDirectoryLabel;
    private JLabel invalidWorkingDirectoryLabel;
    private JLabel downloadConfigurationLabel;
    private JButton downloadConfigurationSelectLocationButton;
    private JTextField downloadConfigurationServerAddressField;
    private JLabel separatorLabel;
    private JPanel serverAddressPanel;
    private JLabel serverConnectionErrorLabel;
    JFileChooser existingConfigurationFileChooser;
    JFileChooser newConfigurationFileChooser;

    private JFrame frame;

    public StartView()
    {
        selectWorkingDirectoryButton.addActionListener(e -> selectWorkingDirectoryAction());
        downloadConfigurationSelectLocationButton.addActionListener(e -> selectNewConfigurationLocationAction());

        existingConfigurationFileChooser = new JFileChooser();
        existingConfigurationFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        existingConfigurationFileChooser.setAcceptAllFileFilterUsed(false);

        newConfigurationFileChooser = new JFileChooser();
        newConfigurationFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        newConfigurationFileChooser.setAcceptAllFileFilterUsed(false);

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
        existingConfigurationFileChooser.showOpenDialog(mainPanel);
    }

    private void selectNewConfigurationLocationAction()
    {
        newConfigurationFileChooser.showOpenDialog(mainPanel);
    }

    public void setVisible(boolean visible)
    {
        frame.setVisible(visible);
    }

    public JFileChooser getExistingConfigurationFileChooser()
    {
        return existingConfigurationFileChooser;
    }

    public JFileChooser getNewConfigurationFileChooser()
    {
        return newConfigurationFileChooser;
    }

    public JLabel getInvalidRepositorySelectionLabel() { return invalidWorkingDirectoryLabel; }

    public JLabel getServerConnectionErrorLabel() { return serverConnectionErrorLabel; }
}
