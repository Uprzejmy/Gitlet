package piglet.View;

import javax.swing.*;

/**
 * Created by Uprzejmy on 11.06.2017.
 */
public class StartView implements IView{
    private JPanel mainPanel;
    private JLabel title;
    private JButton usersViewButton;
    private JButton groupsViewButton;

    private JFrame frame;

    public StartView()
    {
        frame = new JFrame("start view");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public JPanel getMainPanel()
    {
        return mainPanel;
    }

    public JButton getUsersViewButton()
    {
        return usersViewButton;
    }
    public JButton getGroupsViewButton()
    {
        return groupsViewButton;
    }
}
