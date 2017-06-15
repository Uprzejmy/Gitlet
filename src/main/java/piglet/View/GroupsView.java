package piglet.View;

import piglet.Model.Entity.Group;
import piglet.Model.IGroupsModel;
import piglet.Model.Model;

import javax.swing.*;

/**
 * Created by Uprzejmy on 11.06.2017.
 */
public class GroupsView implements IView, IObserver, ITabbable {
    private JList groupsList;
    private JScrollPane scrolledGroupsList;
    private JPanel mainPanel;
    private JPanel groupDetails;
    private JLabel groupname;
    private JList usersList;

    private IGroupsModel model;
    private Group selectedGroup;

    public GroupsView()
    {
        this.model = Model.getInstance().getGroupsModel();
        this.model.registerGroupsModelObserver(this);

        groupsList.addListSelectionListener(e -> updateGroupDetails());
    }

    public JPanel getMainPanel()
    {
        return mainPanel;
    }

    public void update()
    {
        groupsList.setListData(model.getGroups().toArray());

        updateGroupDetails();
    }

    public void updateGroupDetails()
    {
        try
        {
            selectedGroup = (Group) groupsList.getSelectedValue();

            groupname.setText(selectedGroup.getName());
            usersList.setListData(selectedGroup.getUsers().toArray());
        }
        catch(NullPointerException e)
        {
            groupname.setText("group not selected");
        }
    }
}
