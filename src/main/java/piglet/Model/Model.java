package piglet.Model;

import piglet.Model.Entity.Group;
import piglet.Model.Entity.User;
import piglet.View.RepositoriesView;

/**
 * Created by Uprzejmy on 11.06.2017.
 */
public class Model {

    private static Model model = null;
    private static UsersModel usersModel = null;
    private static GroupsModel groupsModel = null;
    private static RepositoriesModel repositoriesModel = null;

    private Model()
    {
        usersModel = new UsersModel();
        groupsModel = new GroupsModel();
        repositoriesModel = new RepositoriesModel();

        usersModel.getUsers().add(new User("user1","example public key1"));
        usersModel.getUsers().add(new User("user2","example public key2"));

        Group group = new Group("test group");
        group.addUser(usersModel.getUsers().iterator().next());
        groupsModel.getGroups().add(group);

        Group group2 = new Group("test group2");
        group2.addUser(usersModel.getUsers().iterator().next());
        groupsModel.getGroups().add(group2);
    }

    public static Model getInstance()
    {
        if(model == null)
        {
            model = new Model();
        }

        return model;
    }

    public void initialize()
    {
        usersModel.initialize();
        groupsModel.initialize();
        repositoriesModel.initialize();
    }

    public UsersModel getUsersModel()
    {
        return usersModel;
    }

    public GroupsModel getGroupsModel()
    {
        return groupsModel;
    }

    public RepositoriesModel getRepositoriesModel()
    {
        return repositoriesModel;
    }
}
