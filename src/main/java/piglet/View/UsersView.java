package piglet.View;

import piglet.Model.Entity.User;
import piglet.Model.IUsersModel;
import piglet.Model.Model;

import javax.swing.*;

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

    private IUsersModel model;
    private User selectedUser;

    public UsersView()
    {
        model = Model.getInstance().getUsersModel();
        model.registerUsersModelObserver(this);

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
        usersList.setListData(model.getUsers().toArray());

        updateUserDetails();
    }

    public void updateUserDetails()
    {
        try
        {
            selectedUser = (User) usersList.getSelectedValue();

            username.setText(selectedUser.getUsername());
            groupsList.setListData(selectedUser.getGroups().toArray());
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
        model.addUser(usernameField.getText(),publicKeyField.getText());

        switchActionPanelToUserDetails();
    }

}
