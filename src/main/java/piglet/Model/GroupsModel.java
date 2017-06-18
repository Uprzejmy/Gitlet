package piglet.Model;


import piglet.Model.Entity.Group;
import piglet.Model.Entity.User;
import piglet.Model.Utils.GroupComparator;
import piglet.View.IObserver;
import sun.reflect.generics.tree.Tree;

import java.util.ArrayList;
import java.util.Collection;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Created by Uprzejmy on 12.06.2017.
 */
public class GroupsModel implements IGroupsModel{

    private SortedSet<Group> groups;
    Collection<IObserver> groupsModelObservers;

    public GroupsModel()
    {
        groups = new TreeSet<>(new GroupComparator());
        groupsModelObservers = new ArrayList<>();

    }

    public void initialize()
    {
        notifyGroupsModelObservers();
    }

    public Collection<Group> getGroups()
    {
        return groups;
    }

    public void addGroup(String name)
    {
        groups.add(new Group(name));

        notifyGroupsModelObservers();
    }

    public void addUserToGroup(Group group, User user)
    {
        group.addUser(user);

        notifyGroupsModelObservers();
    }

    public void removeUserFromGroup(Group group, User user)
    {
        group.removeUser(user);

        notifyGroupsModelObservers();
    }

    public void registerGroupsModelObserver(IObserver observer)
    {
        groupsModelObservers.add(observer);
    }

    private void notifyGroupsModelObservers()
    {
        for(IObserver observer : groupsModelObservers)
        {
            observer.update();
        }
    }
}
