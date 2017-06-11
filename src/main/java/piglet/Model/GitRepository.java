package piglet.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Uprzejmy on 11.06.2017.
 */
public class GitRepository {
    private String name;
    private List<RepositoryPermission> repositoryPermissions;

    private GitRepository(){}

    public GitRepository(String name)
    {
        this.name = name;

        repositoryPermissions = new ArrayList<>();
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public List<RepositoryPermission> getRepositoryPermissions()
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
}
