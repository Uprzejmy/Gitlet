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
    private JPanel groupDetailsPanel;
    private JLabel groupname;
    private JList usersList;
    private JPanel actionPanel;
    private JPanel addGroupPanel;
    private JTextField groupNameField;
    private JButton createGroupButton;
    private JButton addGroupButton;

    private IGroupsModel model;
    private Group selectedGroup;

    public GroupsView()
    {
        this.model = Model.getInstance().getGroupsModel();
        this.model.registerGroupsModelObserver(this);

        addGroupPanel.setVisible(false);
        groupDetailsPanel.setVisible(true);

        groupsList.addListSelectionListener(e -> updateGroupDetails());
        addGroupButton.addActionListener(e -> switchActionPanelToGroupCreate());
        createGroupButton.addActionListener(e -> createGroupAction());
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

        switchActionPanelToGroupDetails();
    }

    private void switchActionPanelToGroupCreate()
    {
        groupDetailsPanel.setVisible(false);
        addGroupPanel.setVisible(true);
    }

    private void switchActionPanelToGroupDetails()
    {
        groupDetailsPanel.setVisible(true);
        addGroupPanel.setVisible(false);
    }

    private void createGroupAction()
    {
        model.addGroup(groupNameField.getText());

        switchActionPanelToGroupDetails();
    }
}
