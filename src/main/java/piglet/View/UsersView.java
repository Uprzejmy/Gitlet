package piglet.View;

import piglet.Model.Model;

import javax.swing.*;

/**
 * Created by Uprzejmy on 11.06.2017.
 */
public class UsersView implements IView, IObserver {
    private JLabel title;
    private JList usersList;
    private JScrollPane scrolledUsersList;
    private JPanel mainPanel;

    private JFrame frame;
    private Model model;

    public UsersView(Model model)
    {
        this.model = model;
        this.model.registerUsersModelObserver(this);

        frame = new JFrame("users view");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public void update()
    {
        usersList.setListData(model.getUsers().toArray());
    }
}
