package piglet.Model.Utils;

import piglet.Model.Entity.Repository;

import java.util.Comparator;

/**
 * Created by Uprzejmy on 16.06.2017.
 */
public class RepositoryComparator implements Comparator<Repository> {
    public int compare(Repository o1, Repository o2)
    {
        return o1.getName().compareTo(o2.getName());
    }
}
