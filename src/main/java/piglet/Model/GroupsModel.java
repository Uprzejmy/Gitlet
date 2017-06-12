package piglet.Model;


import piglet.Model.Entity.Group;
import piglet.View.IObserver;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Uprzejmy on 12.06.2017.
 */
public class GroupsModel implements IGroupsModel{

    private Collection<Group> groups;
    Collection<IObserver> groupsModelObservers;

    public GroupsModel()
    {
        groups = new ArrayList<>();
        groupsModelObservers = new ArrayList<>();

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
