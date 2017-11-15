package piglet.View;

import piglet.Model.Entity.Repository;
import piglet.Model.Entity.RepositoryPermission;
import piglet.Model.Entity.User;
import piglet.Model.IRepositoriesModel;
import piglet.Model.IUsersModel;
import piglet.Model.Model;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Map;

/**
 * Created by Uprzejmy on 11.06.2017.
 */
public class UsersView implements IView, IObserver, ITabbable {
    private JList usersList;
    private JScrollPane scrolledUsersList;
    private JPanel mainPanel;
    private JPanel userDetailsPanel;
    private JLabel username;
    private JList groupsList;
    private JButton addUserButton;
    private JPanel actionPanel;
    private JPanel addUserPanel;
    private JTextField usernameField;
    private JTextArea publicKeyField;
    private JScrollPane scrolledPublicKeyField;
    private JButton createUserButton;
    private JScrollPane scrolledGroupsList;
    private JLabel accessibleRepositoriesLabel;
    private JTable accessibleRepositoriesTable;
    private JScrollPane scrolledAccessibleRepositoriesTable;

    private IUsersModel usersModel;
    private IRepositoriesModel repositoriesModel;

    private User selectedUser;

    public UsersView()
    {
        usersModel = Model.getInstance().getUsersModel();
        repositoriesModel = Model.getInstance().getRepositoriesModel();

        usersModel.registerUsersModelObserver(this);

        addUserPanel.setVisible(false);
        userDetailsPanel.setVisible(true);

        usersList.addListSelectionListener(e -> updateUserDetails());
        addUserButton.addActionListener(e -> switchActionPanelToUserCreate());
        createUserButton.addActionListener(e -> createUserAction());
    }

    public JPanel getMainPanel()
    {
        return mainPanel;
    }

    public void update()
    {
        usersList.setListData(usersModel.getUsers().toArray());

        updateUserDetails();
    }

    public void updateUserDetails()
    {
        try
        {
            selectedUser = (User) usersList.getSelectedValue();

            username.setText(selectedUser.getUsername());
            groupsList.setListData(selectedUser.getGroups().toArray());

            populateAccessibleRepositoriesTable();
        }
        catch(NullPointerException e)
        {
            username.setText("user not selected");
        }

        switchActionPanelToUserDetails();
    }

    private void switchActionPanelToUserCreate()
    {
        usernameField.setText("");
        publicKeyField.setText("");

        userDetailsPanel.setVisible(false);
        addUserPanel.setVisible(true);
    }

    private void switchActionPanelToUserDetails()
    {
        userDetailsPanel.setVisible(true);
        addUserPanel.setVisible(false);
    }

    private void createUserAction()
    {
        usersModel.addUser(usernameField.getText(),publicKeyField.getText());

        switchActionPanelToUserDetails();
    }

    private void populateAccessibleRepositoriesTable()
    {
        Map<Repository,RepositoryPermission> permissionMap = repositoriesModel.getRepositories(selectedUser);

        String[] labels = {"repository", "permission", "source"};
        DefaultTableModel tableModel = new DefaultTableModel(labels,0);

        permissionMap.forEach((repository,repositoryPermission) -> tableModel.addRow(new Object[] { repository.getName(), repositoryPermission.getPermission(), repositoryPermission.getTarget()}));

        accessibleRepositoriesTable.setModel(tableModel);
    }

}
