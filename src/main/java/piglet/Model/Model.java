package piglet.Model;

import piglet.View.IObserver;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Uprzejmy on 11.06.2017.
 */
public class Model {

    private static Model model = null;
    private Collection<User> users;
    Collection<IObserver> usersModelObservers;

    private Model()
    {
        users = new ArrayList<>();
        usersModelObservers = new ArrayList<>();

        users.add(new User("user1","example public key1"));
        users.add(new User("user2","example public key2"));
    }

    public static Model getInstance()
    {
        if(model == null)
        {
            model = new Model();
        }

        return model;
    }

    public Collection<User> getUsers()
    {
        return users;
    }

    public void addUser(String username, String publicKey)
    {
        users.add(new User(username, publicKey));

        notifyUsersModelObservers();
    }

    public void registerUsersModelObserver(IObserver observer)
    {
        usersModelObservers.add(observer);
    }

    private void notifyUsersModelObservers()
    {
        for(IObserver observer : usersModelObservers)
        {
            observer.update();
        }
    }
}
