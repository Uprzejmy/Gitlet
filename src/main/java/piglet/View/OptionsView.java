package piglet.View;

import javax.swing.*;

/**
 * Created by Uprzejmy on 20.06.2017.
 */
public class OptionsView {
    private JLabel title;
    private JTextField serverAddressField;
    private JPanel mainPanel;

    public JPanel getMainPanel()
    {
        return mainPanel;
    }

    public String getServerAddress()
    {
        return serverAddressField.getText();
    }
}
