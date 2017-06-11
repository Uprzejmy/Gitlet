package piglet.Model;

import piglet.Model.Entity.Group;
import piglet.Model.Entity.User;
import piglet.View.IObserver;

import java.util.Collection;

/**
 * Created by Uprzejmy on 11.06.2017.
 */
public class Model implements IFacadeModel {

    private static Model model = null;
    private static UserModel userModel = null;
    private static GroupModel groupModel = null;


    private Model()
    {
        userModel = new UserModel();
        groupModel = new GroupModel();

        userModel.getUsers().add(new User("user1","example public key1"));
        userModel.getUsers().add(new User("user2","example public key2"));

        Group group = new Group("test group");
        group.addUser(userModel.getUsers().iterator().next());
        groupModel.getGroups().add(group);
    }

    public static Model getInstance()
    {
        if(model == null)
        {
            model = new Model();
        }

        return model;
    }

    public void registerUsersModelObserver(IObserver observer)
    {
        userModel.registerUsersModelObserver(observer);
    }

    public void registerGroupsModelObserver(IObserver observer)
    {
        groupModel.registerGroupsModelObserver(observer);
    }

    public Collection<User> getUsers()
    {
        return userModel.getUsers();
    }

    public Collection<Group> getGroups()
    {
        return groupModel.getGroups();
    }



}
