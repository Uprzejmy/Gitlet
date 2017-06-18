package piglet.Model;

import piglet.Model.Entity.EPermission;
import piglet.Model.Entity.IPermissionTarget;
import piglet.Model.Entity.Repository;
import piglet.View.IObserver;

import java.util.Collection;

/**
 * Created by Uprzejmy on 15.06.2017.
 */
public interface IRepositoriesModel {
    Collection<Repository> getRepositories();
    void registerRepositoriesModelObserver(IObserver observer);
    void addRepository(String repositoryName);
    void addRepositoryPermission(Repository repository, IPermissionTarget target, EPermission permission);
}
