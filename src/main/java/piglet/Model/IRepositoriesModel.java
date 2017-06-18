package piglet.Model;

import piglet.Model.Entity.*;
import piglet.View.IObserver;

import java.util.Collection;
import java.util.Map;

/**
 * Created by Uprzejmy on 15.06.2017.
 */
public interface IRepositoriesModel {
    Collection<Repository> getRepositories();
    void registerRepositoriesModelObserver(IObserver observer);
    void addRepository(String repositoryName);
    void addRepositoryPermission(Repository repository, IPermissionTarget target, EPermission permission);
    void removeRepositoryPermission(Repository repository, RepositoryPermission repositoryPermission);
    Map<Repository,RepositoryPermission> getRepositories(User user);
}
