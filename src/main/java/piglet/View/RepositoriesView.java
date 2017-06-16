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
    private JPanel repositoryDetailsPanel;
    private JList permissions;
    private JLabel repositoryName;
    private JButton addRepositoryButton;
    private JPanel actionPanel;
    private JPanel addRepositoryPanel;
    private JTextField repositoryNameField;
    private JButton createRepositoryButton;

    private IRepositoriesModel model;
    private Repository selectedRepository;

    public RepositoriesView()
    {
        this.model = Model.getInstance().getRepositoriesModel();
        this.model.registerRepositoriesModelObserver(this);

        addRepositoryPanel.setVisible(false);
        repositoryDetailsPanel.setVisible(true);

        repositoriesList.addListSelectionListener(e -> updateRepositoryDetails());
        addRepositoryButton.addActionListener(e -> switchActionPanelToRepositoryCreate());
        createRepositoryButton.addActionListener(e -> createRepositoryAction());
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

        switchActionPanelToRepositoryDetails();
    }

    private void switchActionPanelToRepositoryCreate()
    {
        repositoryDetailsPanel.setVisible(false);
        addRepositoryPanel.setVisible(true);
    }

    private void switchActionPanelToRepositoryDetails()
    {
        repositoryDetailsPanel.setVisible(true);
        addRepositoryPanel.setVisible(false);
    }

    private void createRepositoryAction()
    {
        model.addRepository(repositoryNameField.getText());

        switchActionPanelToRepositoryDetails();
    }
}
