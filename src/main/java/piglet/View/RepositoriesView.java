package piglet.View;

import piglet.Model.Entity.Repository;
import piglet.Model.IRepositoriesModel;
import piglet.Model.Model;

import javax.swing.*;

/**
 * Created by Uprzejmy on 15.06.2017.
 */
public class RepositoriesView implements IView, IObserver, ITabbable  {
    private JPanel mainPanel;
    private JList repositoriesList;
    private JScrollPane scrolledRepositoriesPane;
    private JPanel repositoryDetails;
    private JList permissions;
    private JLabel repositoryName;

    private IRepositoriesModel model;
    private Repository selectedRepository;

    public RepositoriesView()
    {
        this.model = Model.getInstance().getRepositoriesModel();
        this.model.registerRepositoriesModelObserver(this);

        repositoriesList.addListSelectionListener(e -> updateRepositoryDetails());
    }

    public JPanel getMainPanel()
    {
        return mainPanel;
    }

    public void update()
    {
        repositoriesList.setListData(model.getRepositories().toArray());

        updateRepositoryDetails();
    }

    public void updateRepositoryDetails()
    {
        try
        {
            selectedRepository = (Repository) repositoriesList.getSelectedValue();

            repositoryName.setText(selectedRepository.getName());
            permissions.setListData(selectedRepository.getRepositoryPermissions().toArray());
        }
        catch(NullPointerException e)
        {
            repositoryName.setText("repository not selected");
        }
    }
}
