package piglet.Model;

import piglet.Model.Entity.GitRepository;
import piglet.View.IObserver;

import java.util.Collection;

/**
 * Created by Uprzejmy on 15.06.2017.
 */
public interface IRepositoriesModel {
    Collection<GitRepository> getRepositories();
    void registerRepositoriesModelObserver(IObserver observer);
}
