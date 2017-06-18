package piglet.View;

import piglet.Model.Entity.Group;
import piglet.Model.Entity.Repository;
import piglet.Model.Entity.RepositoryPermission;
import piglet.Model.IGroupsModel;
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
    private JScrollPane scrolledRepositoriesPane;
    private JPanel repositoryDetailsPanel;
    private JList permissions;
    private JLabel repositoryName;
    private JButton addRepositoryButton;
    private JPanel actionPanel;
    private JPanel addRepositoryPanel;
    private JTextField repositoryNameField;
    private JButton createRepositoryButton;
    private JComboBox addRepositoryPermissionField;

    private IRepositoriesModel repositoriesModel;
    private IUsersModel usersModel;
    private IGroupsModel groupsModel;
    private Repository selectedRepository;

    public RepositoriesView()
    {
        this.repositoriesModel = Model.getInstance().getRepositoriesModel();
        this.repositoriesModel.registerRepositoriesModelObserver(this);

        this.usersModel = Model.getInstance().getUsersModel();
        this.groupsModel = Model.getInstance().getGroupsModel();

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
        repositoriesList.setListData(repositoriesModel.getRepositories().toArray());

        updateRepositoryDetails();
    }

    public void updateRepositoryDetails()
    {
        try
        {
            selectedRepository = (Repository) repositoriesList.getSelectedValue();

            repositoryName.setText(selectedRepository.getName());
            permissions.setListData(selectedRepository.getRepositoryPermissions().toArray());

            populateGroupsComboBox();
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
        repositoriesModel.addRepository(repositoryNameField.getText());

        switchActionPanelToRepositoryDetails();
    }

    // uchhhh :/
    private void populateGroupsComboBox()
    {
        addRepositoryPermissionField.removeAllItems();

        for(Group group : groupsModel.getGroups())
        {
            addRepositoryPermissionField.addItem(group);

            for(RepositoryPermission repositoryPermission : selectedRepository.getRepositoryPermissions())
            {
                if(repositoryPermission.getTarget() == group)
                {
                    addRepositoryPermissionField.removeItem(group);
                }
            }
        }
    }
}
