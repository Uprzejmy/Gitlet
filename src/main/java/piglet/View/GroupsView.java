package piglet.View;

import piglet.Model.Entity.Group;
import piglet.Model.Entity.GroupMember;
import piglet.Model.Entity.User;
import piglet.Model.IGroupsModel;
import piglet.Model.IUsersModel;
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
    private JComboBox addUserField;
    private JButton addUserButton;
    private JScrollPane scrolledUsersList;
    private JButton removeSelectedUserButton;

    private IGroupsModel groupsModel;
    private IUsersModel usersModel;

    private Group selectedGroup;

    public GroupsView()
    {
        this.groupsModel = Model.getInstance().getGroupsModel();
        this.usersModel = Model.getInstance().getUsersModel();

        this.groupsModel.registerGroupsModelObserver(this);

        addGroupPanel.setVisible(false);
        groupDetailsPanel.setVisible(true);

        groupsList.addListSelectionListener(e -> updateGroupDetails());
        addGroupButton.addActionListener(e -> switchActionPanelToGroupCreate());
        createGroupButton.addActionListener(e -> createGroupAction());
        addUserButton.addActionListener(e -> addMemberAction());
        removeSelectedUserButton.addActionListener(e -> removeSelectedUserAction());
    }

    public JPanel getMainPanel()
    {
        return mainPanel;
    }

    public void update()
    {
        Group oldSelectedGroup = selectedGroup;

        groupsList.setListData(groupsModel.getGroups().toArray());

        groupsList.setSelectedValue(oldSelectedGroup,true);

        updateGroupDetails();
    }

    public void updateGroupDetails()
    {
        try
        {
            selectedGroup = (Group) groupsList.getSelectedValue();

            groupname.setText(selectedGroup.getName());
            usersList.setListData(selectedGroup.getMembers().toArray());

            populateAddUserComboBox();
        }
        catch(NullPointerException e)
        {
            groupname.setText("group not selected");
        }

        switchActionPanelToGroupDetails();
    }

    private void switchActionPanelToGroupCreate()
    {
        groupNameField.setText("");

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
        groupsModel.addGroup(groupNameField.getText());

        switchActionPanelToGroupDetails();
    }

    private void populateAddUserComboBox()
    {
        addUserField.removeAllItems();

        for(User user : usersModel.getUsers())
        {
            addUserField.addItem(user);
        }

        for(GroupMember member : selectedGroup.getMembers())
        {
            addUserField.removeItem(member);
        }
    }

    private void addMemberAction()
    {
        try
        {
            groupsModel.addMemberToGroup(selectedGroup, (User) addUserField.getSelectedItem());
        }
        catch(NullPointerException e)
        {
            //todo implement some notification for the user
        }
    }

    private void removeSelectedUserAction()
    {
        try
        {
            groupsModel.removeMemberFromGroup(selectedGroup, (User) usersList.getSelectedValue());
        }
        catch(NullPointerException e)
        {
            //todo implement some notification for the user
        }
    }
}
