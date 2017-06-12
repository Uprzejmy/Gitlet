package piglet.View;

import piglet.Model.IGroupsModel;
import piglet.Model.Model;

import javax.swing.*;

/**
 * Created by Uprzejmy on 11.06.2017.
 */
public class GroupsView implements IView, IObserver, ITabbable {
    private JLabel title;
    private JList groupsList;
    private JScrollPane scrolledGroupsList;
    private JPanel mainPanel;

    private IGroupsModel model;

    public GroupsView()
    {
        this.model = Model.getInstance().getGroupsModel();
        this.model.registerGroupsModelObserver(this);
    }

    public JPanel getMainPanel()
    {
        return mainPanel;
    }

    public void update()
    {
        groupsList.setListData(model.getGroups().toArray());
    }
}
