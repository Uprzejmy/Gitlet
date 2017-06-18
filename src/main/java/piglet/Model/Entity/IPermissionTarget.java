package piglet.Model.Entity;

/**
 * Created by Uprzejmy on 11.06.2017.
 */
public interface IPermissionTarget extends Comparable<IPermissionTarget> {
    int getImportance();
    int compareTo(IPermissionTarget target);
}
