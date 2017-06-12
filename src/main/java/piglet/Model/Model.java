package piglet.Model;

import piglet.Model.Entity.Group;
import piglet.Model.Entity.User;

/**
 * Created by Uprzejmy on 11.06.2017.
 */
public class Model {

    private static Model model = null;
    private static UsersModel usersModel = null;
    private static GroupsModel groupsModel = null;


    private Model()
    {
        usersModel = new UsersModel();
        groupsModel = new GroupsModel();

        usersModel.getUsers().add(new User("user1","example public key1"));
        usersModel.getUsers().add(new User("user2","example public key2"));

        Group group = new Group("test group");
        group.addUser(usersModel.getUsers().iterator().next());
        groupsModel.getGroups().add(group);
    }

    public static Model getInstance()
    {
        if(model == null)
        {
            model = new Model();
        }

        return model;
    }

    public UsersModel getUsersModel()
    {
        return usersModel;
    }

    public GroupsModel getGroupsModel()
    {
        return groupsModel;
    }
}
