package piglet.Model.Entity;

import piglet.Model.Utils.RepositoryPermissionComparator;
import sun.reflect.generics.tree.Tree;

import java.util.*;

/**
 * Created by Uprzejmy on 11.06.2017.
 */
public class Repository {
    private String name;
    //Tree Set is important since we will always want to sort by the strongest permission
    private TreeSet<RepositoryPermission> repositoryPermissions;

    private Repository(){}

    public Repository(String name)
    {
        this.name = name;

        repositoryPermissions = new TreeSet<>(new RepositoryPermissionComparator());
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public TreeSet<RepositoryPermission> getRepositoryPermissions()
    {
        return repositoryPermissions;
    }

    public void addRepositoryPermission(IPermissionTarget permissionTarget, EPermission permission)
    {
        repositoryPermissions.add(new RepositoryPermission(permissionTarget, permission));
    }

    public void removeRepositoryPermission(IPermissionTarget permissionTarget)
    {
        repositoryPermissions.removeIf(repositoryPermission -> repositoryPermission.hasTarget(permissionTarget));
    }

    @Override
    public String toString()
    {
        return name;
    }
}
