package piglet.Model;

import piglet.Model.Entity.User;
import piglet.View.IObserver;

import java.util.Collection;

/**
 * Created by Uprzejmy on 12.06.2017.
 */
public interface IUsersModel {
    Collection<User> getUsers();
    void registerUsersModelObserver(IObserver observer);
}
