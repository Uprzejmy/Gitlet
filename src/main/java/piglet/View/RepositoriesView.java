package piglet.View;

import piglet.Model.IRepositoriesModel;
import piglet.Model.IUsersModel;
import piglet.Model.Model;

import javax.swing.*;

/**
 * Created by Uprzejmy on 15.06.2017.
 */
public class RepositoriesView implements IView, IObserver, ITabbable  {
    private JPanel mainPanel;
    private JList repositoriesList;
    private JLabel title;
    private JScrollPane scrolledRepositoriesPane;

    private IRepositoriesModel model;

    public RepositoriesView()
    {
        this.model = Model.getInstance().getRepositoriesModel();
        this.model.registerRepositoriesModelObserver(this);
    }

    public JPanel getMainPanel()
    {
        return mainPanel;
    }

    public void update()
    {
        repositoriesList.setListData(model.getRepositories().toArray());
    }
}
