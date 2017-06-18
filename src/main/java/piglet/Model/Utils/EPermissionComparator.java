package piglet.Model.Utils;

import piglet.Model.Entity.EPermission;

import java.util.Comparator;

/**
 * Created by Uprzejmy on 15.06.2017.
 */
class EPermissionComparator implements Comparator<EPermission>
{
    private int order(EPermission o)
    {
        switch (o)
        {
            case R: return 0;
            case RW: return 1;
            case ADMIN: return 2;
            default: return 0;
        }
    }

    public int compare(EPermission o1, EPermission o2)
    {
        return order(o1) - order(o2);
    }
}