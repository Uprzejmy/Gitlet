package piglet.Model.Utils;

import piglet.Model.Entity.GroupMember;

import java.util.Comparator;

public class GroupMemberComparator implements Comparator<GroupMember>
{
        public int compare(GroupMember m1, GroupMember m2)
        {
                return m1.getName().compareTo(m2.getName());
        }
}
