package piglet.Model.Utils;

import piglet.Model.Entity.RepositoryPermission;

import java.util.Comparator;

/**
 * Created by Uprzejmy on 15.06.2017.
 */
public class RepositoryPermissionComparator  implements Comparator<RepositoryPermission> {
    public int compare(RepositoryPermission o1, RepositoryPermission o2)
    {
        return new EPermissionComparator().compare(o1.getPermission(),o2.getPermission());
    }
}
