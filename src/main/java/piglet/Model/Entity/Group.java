package piglet.Model.Entity;

import piglet.Model.Utils.GroupMemberComparator;

import java.util.Collection;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Created by Uprzejmy on 11.06.2017.
 */
public class Group extends GroupMember implements IPermissionTarget, Comparable<IPermissionTarget>{

    private SortedSet<GroupMember> members;

    public Group(String name)
    {
        super(name);

        members = new TreeSet<>(new GroupMemberComparator());
    }

    public Collection<GroupMember> getMembers()
    {
        return members;
    }

    public void addMember(GroupMember member)
    {
        members.add(member);

        if(!member.getGroups().contains(this))
        {
            member.addToGroup(this);
        }
    }

    public void removeMember(GroupMember member)
    {
        members.remove(member);

        if(member.getGroups().contains(this))
        {
            member.removeFromGroup(this);
        }
    }

    @Override
    public int compareTo(IPermissionTarget target)
    {
        int targetTypeOrder = this.getImportance() - target.getImportance();

        if(targetTypeOrder != 0)
        {
            return targetTypeOrder;
        }

        return this.equals(target) ? 0 : 1;
    }

    @Override
    public int getImportance()
    {
        return 2;
    }

    @Override
    public boolean contains(User member)
    {
        for(GroupMember m : members)
        {
            if(m == member)
            {
                return true;
            }
        }

        return false;
    }

    @Override
    public String getConfigValue()
    {
        return "@" + name;
    }
}
