package piglet.Model;

import piglet.Model.Entity.User;
import piglet.View.IObserver;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Uprzejmy on 12.06.2017.
 */
public class UserModel {

    private Collection<User> users;
    Collection<IObserver> usersModelObservers;

    public UserModel()
    {
        users = new ArrayList<>();
        usersModelObservers = new ArrayList<>();

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
