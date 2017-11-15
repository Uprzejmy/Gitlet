package piglet.Model;

import piglet.Model.Entity.*;
import piglet.Model.Utils.RepositoryComparator;
import piglet.View.IObserver;

import java.util.*;

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

    public Map<Repository,RepositoryPermission> getRepositories(User user)
    {
        Map<Repository,RepositoryPermission> accessibleRepositories = new HashMap<>();

        for(Repository repository : repositories)
        {
            for(RepositoryPermission repositoryPermission : repository.getRepositoryPermissions())
            {
                if(repositoryPermission.getTarget().contains(user))
                {
                    accessibleRepositories.put(repository, repositoryPermission);
                }
            }
        }

        return accessibleRepositories;
    }

    public Repository findRepositoryByName(String name)
    {
        for(Repository repository : repositories)
        {
            if(repository.getName().equals(name))
            {
                return repository;
            }
        }

        return null;
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
