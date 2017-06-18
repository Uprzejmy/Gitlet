package piglet.Model;

import piglet.Model.Entity.EPermission;
import piglet.Model.Entity.IPermissionTarget;
import piglet.Model.Entity.Repository;
import piglet.Model.Entity.RepositoryPermission;
import piglet.Model.Utils.RepositoryComparator;
import piglet.View.IObserver;

import java.util.ArrayList;
import java.util.Collection;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Created by Uprzejmy on 15.06.2017.
 */
public class RepositoriesModel implements IRepositoriesModel {

    private SortedSet<Repository> repositories;
    Collection<IObserver> repositoriesModelObservers;

    public RepositoriesModel()
    {
        repositories = new TreeSet<>(new RepositoryComparator());
        repositoriesModelObservers = new ArrayList<>();
    }

    public void initialize()
    {
        notifyRepositoriesModelObservers();
    }

    public Collection<Repository> getRepositories()
    {
        return repositories;
    }

    public void addRepository(String name)
    {
        repositories.add(new Repository(name));

        notifyRepositoriesModelObservers();
    }

    public void addRepositoryPermission(Repository repository, IPermissionTarget target, EPermission permission)
    {
        repository.addRepositoryPermission(target,permission);

        notifyRepositoriesModelObservers();
    }

    public void removeRepositoryPermission(Repository repository, RepositoryPermission repositoryPermission)
    {
        repository.removeRepositoryPermission(repositoryPermission.getTarget());

        notifyRepositoriesModelObservers();
    }

    public void registerRepositoriesModelObserver(IObserver observer)
    {
        repositoriesModelObservers.add(observer);
    }

    private void notifyRepositoriesModelObservers()
    {
        for(IObserver observer : repositoriesModelObservers)
        {
            observer.update();
        }
    }
}
