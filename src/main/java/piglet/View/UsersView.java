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
    private JPanel userDetails;
    private JLabel username;
    private JList groupsList;

    private IUsersModel model;
    private User selectedUser;

    public UsersView()
    {
        this.model = Model.getInstance().getUsersModel();
        this.model.registerUsersModelObserver(this);

        usersList.addListSelectionListener(e -> updateUserDetails());
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
    }


}
