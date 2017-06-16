package piglet.Model;

import piglet.Model.Entity.User;
import piglet.Model.Utils.UserComparator;
import piglet.View.IObserver;

import java.util.*;

/**
 * Created by Uprzejmy on 12.06.2017.
 */
public class UsersModel implements IUsersModel {

    private SortedSet<User> users;
    Collection<IObserver> usersModelObservers;

    public UsersModel()
    {
        users = new TreeSet<>(new UserComparator());
        usersModelObservers = new ArrayList<>();
    }

    public void initialize()
    {
        notifyUsersModelObservers();
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
