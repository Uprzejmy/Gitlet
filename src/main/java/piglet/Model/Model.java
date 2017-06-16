package piglet.Model;

import piglet.Model.Entity.*;

/**
 * Created by Uprzejmy on 11.06.2017.
 */
public class Model {

    private static Model model = null;
    private static UsersModel usersModel = null;
    private static GroupsModel groupsModel = null;
    private static RepositoriesModel repositoriesModel = null;

    private Model()
    {
        usersModel = new UsersModel();
        groupsModel = new GroupsModel();
        repositoriesModel = new RepositoriesModel();

        loadExampleData();
    }

    public static Model getInstance()
    {
        if(model == null)
        {
            model = new Model();
        }

        return model;
    }

    public void initialize()
    {
        usersModel.initialize();
        groupsModel.initialize();
        repositoriesModel.initialize();
    }

    public UsersModel getUsersModel()
    {
        return usersModel;
    }

    public GroupsModel getGroupsModel()
    {
        return groupsModel;
    }

    public RepositoriesModel getRepositoriesModel()
    {
        return repositoriesModel;
    }

    private void loadExampleData()
    {
        for(int i=0;i<30;i++)
        {
            usersModel.getUsers().add(new User("user" + i,"example public key" + i));
        }

        for(int i=0;i<10;i++)
        {
            Group group = new Group("test group" + i);

            for(User user : usersModel.getUsers())
            {
                if(Math.random() < 0.2)
                {
                    group.addUser(user);
                }
            }

            groupsModel.getGroups().add(group);
        }

        for(int i=0;i<3;i++)
        {
            Repository repository = new Repository("test repo" + i);

            for(Group group : groupsModel.getGroups())
            {
                double result = Math.random();

                if(Math.random() < 0.1)
                {
                    repository.addRepositoryPermission(group, EPermission.READWRITE);
                }
                else if(Math.random() < 0.3)
                {
                    repository.addRepositoryPermission(group, EPermission.READ);
                }
            }

            for(User user : usersModel.getUsers())
            {
                if(Math.random() < 0.01)
                {
                    repository.addRepositoryPermission(user, EPermission.READWRITE);
                }
            }

            //adding super user to the repo
            int superUserIndex = (int) (Math.random() * usersModel.getUsers().size());
            repository.addRepositoryPermission((User)usersModel.getUsers().toArray()[superUserIndex],EPermission.READWRITEADMIN);

            repositoriesModel.getRepositories().add(repository);
        }
    }
}
