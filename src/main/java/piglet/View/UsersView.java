package piglet.View;

import javax.swing.*;

/**
 * Created by Uprzejmy on 11.06.2017.
 */
public class UsersView implements IView {
    private JLabel title;
    private JList usersList;
    private JScrollPane scrolledUsersList;
    private JPanel mainPanel;

    private JFrame frame;

    public UsersView()
    {
        frame = new JFrame("users view");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
