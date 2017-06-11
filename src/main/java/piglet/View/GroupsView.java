package piglet.View;

import piglet.Model.Model;

import javax.swing.*;

/**
 * Created by Uprzejmy on 11.06.2017.
 */
public class GroupsView implements IView, IObserver {
    private JLabel title;
    private JList groupsList;
    private JScrollPane scrolledGroupsList;
    private JPanel mainPanel;

    private JFrame frame;
    private Model model;

    public GroupsView(Model model)
    {
        this.model = model;
        this.model.registerUsersModelObserver(this);

        frame = new JFrame("groups view");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public void update()
    {
        groupsList.setListData(model.getGroups().toArray());
    }
}
