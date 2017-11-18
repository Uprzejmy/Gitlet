package piglet.Model;


import piglet.Model.Entity.Group;
import piglet.Model.Entity.GroupMember;
import piglet.Model.Utils.GroupComparator;
import piglet.View.IObserver;

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

    public void addMemberToGroup(Group group, GroupMember member)
    {
        group.addMember(member);

        notifyGroupsModelObservers();
    }

    public void removeMemberFromGroup(Group group, GroupMember member)
    {
        group.removeMember(member);

        notifyGroupsModelObservers();
    }

    public Group findGroupByGroupName(String name)
    {
        for(Group group : groups)
        {
            if(group.getName().equalsIgnoreCase(name))
            {
                return group;
            }
        }

        return null;
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
