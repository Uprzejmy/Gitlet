package piglet.Model.Utils;

import piglet.Model.Entity.Group;

import java.util.Comparator;

/**
 * Created by Uprzejmy on 16.06.2017.
 */
public class GroupComparator implements Comparator<Group> {
    public int compare(Group o1, Group o2)
    {
        return o1.getName().compareTo(o2.getName());
    }
}
