package piglet.Model.Entity;

/**
 * Created by Uprzejmy on 11.06.2017.
 */
public class RepositoryPermission {

    private EPermission permission;
    private IPermissionTarget target;

    private RepositoryPermission(){}

    public RepositoryPermission(IPermissionTarget permissionTarget, EPermission permission)
    {
        this.permission = permission;
        this.target = permissionTarget;
    }

    public EPermission getPermission()
    {
        return permission;
    }

    public void setPermission(EPermission permission)
    {
        this.permission = permission;
    }

    public IPermissionTarget getTarget()
    {
        return target;
    }

    public void setTarget(IPermissionTarget target)
    {
        this.target = target;
    }

    public boolean hasTarget(IPermissionTarget target)
    {
        return this.target.equals(target);
    }

    @Override
    public String toString()
    {
        return "" +
                "target: " + target + " " +
                "permission: " + permission
                ;
    }

}
