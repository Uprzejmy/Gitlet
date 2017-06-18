package piglet.View;

import com.sun.org.apache.regexp.internal.RE;
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
    private JList permissions;
    private JLabel repositoryName;
    private JButton addRepositoryButton;
    private JPanel actionPanel;
    private JPanel addRepositoryPanel;
    private JTextField repositoryNameField;
    private JButton createRepositoryButton;
    private JComboBox addRepositoryPermissionTargetField;
    private JComboBox addRepositoryPermissionAccessField;
    private JButton addRepositoryPermissisonButton;

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
        addRepositoryPermissisonButton.addActionListener(e -> addRepositoryPermissionAction());
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
        oldSelectedRepository = null;

        updateRepositoryDetails();
    }

    public void updateRepositoryDetails()
    {
        try
        {
            selectedRepository = (Repository) repositoriesList.getSelectedValue();

            repositoryName.setText(selectedRepository.getName());
            permissions.setListData(selectedRepository.getRepositoryPermissions().toArray());

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
        repositoriesModel.addRepositoryPermission(selectedRepository, (IPermissionTarget) addRepositoryPermissionTargetField.getSelectedItem(), (EPermission) addRepositoryPermissionAccessField.getSelectedItem());
    }

    private void createRepositoryAction()
    {
        repositoriesModel.addRepository(repositoryNameField.getText());

        switchActionPanelToRepositoryDetails();
    }

    // uchhhh :/ this is actually the worst piece of code I've ever written
    private void populatePermissionsTargetComboBox()
    {
        addRepositoryPermissionTargetField.removeAllItems();

        for(Group group : groupsModel.getGroups())
        {
            addRepositoryPermissionTargetField.addItem(group);

            for(RepositoryPermission repositoryPermission : selectedRepository.getRepositoryPermissions())
            {
                if(repositoryPermission.getTarget() == group)
                {
                    addRepositoryPermissionTargetField.removeItem(group);
                }
            }
        }

        for(User user : usersModel.getUsers())
        {
            addRepositoryPermissionTargetField.addItem(user);

            for(RepositoryPermission repositoryPermission : selectedRepository.getRepositoryPermissions())
            {
                if(repositoryPermission.getTarget() == user)
                {
                    addRepositoryPermissionTargetField.removeItem(user);
                }
            }
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
}
