package piglet.Model.Entity;

import piglet.Model.Utils.GroupComparator;

import java.util.Collection;
import java.util.TreeSet;

public class GroupMember
{
    protected String name;
    protected Collection<Group> groups;

    public GroupMember(String name)
    {
        this.name = name;
        groups = new TreeSet<>(new GroupComparator());
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Collection<Group> getGroups()
    {
        return groups;
    }

    public void addToGroup(Group group)
    {
        this.groups.add(group);

        if(!group.getMembers().contains(this))
        {
            group.addMember(this);
        }
    }

    public void removeFromGroup(Group group)
    {
        this.groups.remove(group);

        if(group.getMembers().contains(this))
        {
            group.removeMember(this);
        }
    }

    @Override
    public String toString()
    {
        return name;
    }
}
