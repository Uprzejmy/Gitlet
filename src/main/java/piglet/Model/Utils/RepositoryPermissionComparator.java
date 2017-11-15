package piglet.Model.Utils;

import piglet.Model.Entity.RepositoryPermission;

import java.util.Comparator;

/**
 * Created by Uprzejmy on 15.06.2017.
 */
public class RepositoryPermissionComparator  implements Comparator<RepositoryPermission> {
    public int compare(RepositoryPermission o1, RepositoryPermission o2)
    {
        // 1. the most important is the permission strength
        int strengthOrder = new EPermissionComparator().compare(o1.getPermission(),o2.getPermission());

        if(strengthOrder != 0)
        {
            return strengthOrder;
        }

        // 2. groups are preferred over users
        int targetTypeOrder = o1.getTarget().getImportance() - o2.getTarget().getImportance();

        if(targetTypeOrder != 0)
        {
            return targetTypeOrder;
        }

        // 3. as the last resort sort users or groups by their internal rules
        return o1.getTarget().compareTo(o2.getTarget());
    }
}
