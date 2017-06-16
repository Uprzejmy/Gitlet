package piglet.Model.Utils;

import piglet.Model.Entity.User;

import java.util.Comparator;

/**
 * Created by Uprzejmy on 16.06.2017.
 */
public class UserComparator implements Comparator<User> {
    public int compare(User o1, User o2)
    {
        return o1.getUsername().compareTo(o2.getUsername());
    }
}
