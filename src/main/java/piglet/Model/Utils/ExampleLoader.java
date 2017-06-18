package piglet.Model.Utils;

import piglet.Model.Entity.EPermission;
import piglet.Model.Entity.Group;
import piglet.Model.Entity.Repository;
import piglet.Model.Entity.User;
import piglet.Model.Model;

/**
 * Created by Uprzejmy on 16.06.2017.
 */
public class ExampleLoader {
    public static void loadExampleData(Model model)
    {
        for(int i=0;i<30;i++)
        {
            model.getUsersModel().getUsers().add(new User("user" + i,"example public key" + i));
        }

        for(int i=0;i<10;i++)
        {
            Group group = new Group("test group" + i);

            for(User user : model.getUsersModel().getUsers())
            {
                if(Math.random() < 0.2)
                {
                    group.addUser(user);
                }
            }

            model.getGroupsModel().getGroups().add(group);
        }

        for(int i=0;i<3;i++)
        {
            Repository repository = new Repository("test repo" + i);

            for(Group group : model.getGroupsModel().getGroups())
            {
                double result = Math.random();

                if(Math.random() < 0.1)
                {
                    repository.addRepositoryPermission(group, EPermission.R);
                }
                else if(Math.random() < 0.3)
                {
                    repository.addRepositoryPermission(group, EPermission.RW);
                }
            }

            for(User user : model.getUsersModel().getUsers())
            {
                if(Math.random() < 0.01)
                {
                    repository.addRepositoryPermission(user, EPermission.ADMIN);
                }
            }

            //adding super user to the repo
            int superUserIndex = (int) (Math.random() * model.getUsersModel().getUsers().size());
            repository.addRepositoryPermission((User)model.getUsersModel().getUsers().toArray()[superUserIndex],EPermission.ADMIN);

            model.getRepositoriesModel().getRepositories().add(repository);
        }
    }

}
