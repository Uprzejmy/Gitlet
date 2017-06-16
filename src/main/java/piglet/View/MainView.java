package piglet.View;

import piglet.Model.Model;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Uprzejmy on 12.06.2017.
 */
public class MainView {

    UsersView usersView;
    GroupsView groupsView;
    RepositoriesView repositoriesView;

    private JPanel mainPanel;
    private JTabbedPane tabbedPane;

    private JFrame frame;

    public MainView()
    {
        usersView = new UsersView();
        groupsView = new GroupsView();
        repositoriesView = new RepositoriesView();

        tabbedPane.addTab("users", usersView.getMainPanel());
        tabbedPane.addTab("groups", groupsView.getMainPanel());
        tabbedPane.addTab("repositories", repositoriesView.getMainPanel());

        frame = new JFrame("admin");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }


    public UsersView getUsersView()
    {
        return usersView;
    }

    public GroupsView getGroupsView()
    {
        return groupsView;
    }

    public RepositoriesView getRepositoriesView()
    {
        return repositoriesView;
    }


}
