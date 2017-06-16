package piglet.Model;

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
}
