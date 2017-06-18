package piglet.View;

import piglet.Model.Entity.*;
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
    private JScrollPane scrolledPermissionsList;
    private JList permissionsList;
    private JLabel repositoryName;
    private JButton addRepositoryButton;
    private JPanel actionPanel;
    private JPanel addRepositoryPanel;
    private JTextField repositoryNameField;
    private JButton createRepositoryButton;
    private JComboBox addRepositoryPermissionTargetField;
    private JComboBox addRepositoryPermissionAccessField;
    private JButton addRepositoryPermissionButton;
    private JButton removeSelectedPermissionButton;

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
        addRepositoryPermissionButton.addActionListener(e -> addRepositoryPermissionAction());
        removeSelectedPermissionButton.addActionListener(e -> removeSelectedPermissionAction());
    }


    public JPanel getMainPanel()
    {
        return mainPanel;
    }

    public void update()
    {
        Repository oldSelectedRepository = selectedRepository;

        repositoriesList.setListData(repositoriesModel.getRepositories().toArray());

        repositoriesList.setSelectedValue(oldSelectedRepository, true);

        updateRepositoryDetails();
    }

    public void updateRepositoryDetails()
    {
        try
        {
            selectedRepository = (Repository) repositoriesList.getSelectedValue();

            repositoryName.setText(selectedRepository.getName());
            permissionsList.setListData(selectedRepository.getRepositoryPermissions().toArray());

            populatePermissionsAccessComboBox();
            populatePermissionsTargetComboBox();
        }
        catch(NullPointerException e)
        {
            addRepositoryPermissionAccessField.setSelectedIndex(-1); //reset selection
            repositoryName.setText("repository not selected");
        }

        switchActionPanelToRepositoryDetails();
    }

    private void switchActionPanelToRepositoryCreate()
    {
        repositoryNameField.setText("");

        repositoryDetailsPanel.setVisible(false);
        addRepositoryPanel.setVisible(true);
    }

    private void switchActionPanelToRepositoryDetails()
    {
        repositoryDetailsPanel.setVisible(true);
        addRepositoryPanel.setVisible(false);
    }

    private void addRepositoryPermissionAction()
    {
        try
        {
            repositoriesModel.addRepositoryPermission(selectedRepository, (IPermissionTarget) addRepositoryPermissionTargetField.getSelectedItem(), (EPermission) addRepositoryPermissionAccessField.getSelectedItem());
        }
        catch(NullPointerException e)
        {
            //todo implement some notification for the user
        }
    }

    private void createRepositoryAction()
    {
        repositoriesModel.addRepository(repositoryNameField.getText());

        switchActionPanelToRepositoryDetails();
    }

    private void populatePermissionsTargetComboBox()
    {
        addRepositoryPermissionTargetField.removeAllItems();

        for(Group group : groupsModel.getGroups())
        {
            addRepositoryPermissionTargetField.addItem(group);
        }

        for(User user : usersModel.getUsers())
        {
            addRepositoryPermissionTargetField.addItem(user);
        }

        for(RepositoryPermission repositoryPermission : selectedRepository.getRepositoryPermissions())
        {
            addRepositoryPermissionTargetField.removeItem(repositoryPermission.getTarget());
        }
    }

    private void populatePermissionsAccessComboBox()
    {
        addRepositoryPermissionAccessField.removeAllItems();

        for(EPermission ep : EPermission.values())
        {
            addRepositoryPermissionAccessField.addItem(ep);
        }
    }

    private void removeSelectedPermissionAction()
    {
        try
        {
            repositoriesModel.removeRepositoryPermission(selectedRepository, (RepositoryPermission) permissionsList.getSelectedValue());
        }
        catch(NullPointerException e)
        {
            //todo implement some notification for the user
        }
    }
}
