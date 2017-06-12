package piglet.View;

import piglet.Model.IGroupsModel;

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
    private IGroupsModel model;

    public GroupsView(IGroupsModel model)
    {
        this.model = model;
        this.model.registerGroupsModelObserver(this);

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
