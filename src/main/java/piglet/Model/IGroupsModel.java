package piglet.Model;

import piglet.Model.Entity.Group;
import piglet.Model.Entity.User;
import piglet.View.IObserver;

import java.util.Collection;

/**
 * Created by Uprzejmy on 12.06.2017.
 */
public interface IGroupsModel {
    void registerGroupsModelObserver(IObserver observer);
    Collection<Group> getGroups();
    void addGroup(String groupName);
    void addUserToGroup(Group group, User user);
}
