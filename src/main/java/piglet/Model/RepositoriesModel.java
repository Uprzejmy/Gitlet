package piglet.Model;

import piglet.Model.Entity.GitRepository;
import piglet.View.IObserver;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Uprzejmy on 15.06.2017.
 */
public class RepositoriesModel implements IRepositoriesModel {

    private Collection<GitRepository> repositories;
    Collection<IObserver> repositoriesModelObservers;

    public RepositoriesModel()
    {
        repositories = new ArrayList<>();
        repositoriesModelObservers = new ArrayList<>();
    }

    public void initialize()
    {
        notifyRepositoriesModelObservers();
    }

    public Collection<GitRepository> getRepositories()
    {
        return repositories;
    }

    public void addRepository(String name)
    {
        repositories.add(new GitRepository(name));

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
