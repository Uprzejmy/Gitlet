package piglet.View;

import piglet.Model.IUsersModel;
import piglet.Model.Model;

import javax.swing.*;

/**
 * Created by Uprzejmy on 11.06.2017.
 */
public class UsersView implements IView, IObserver, ITabbable {
    private JLabel title;
    private JList usersList;
    private JScrollPane scrolledUsersList;
    private JPanel mainPanel;

    private IUsersModel model;

    public UsersView()
    {
        this.model = Model.getInstance().getUsersModel();
        this.model.registerUsersModelObserver(this);
    }

    public JPanel getMainPanel()
    {
        return mainPanel;
    }

    public void update()
    {
        usersList.setListData(model.getUsers().toArray());
    }
}
