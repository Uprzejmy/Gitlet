package piglet.Model;

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

        //ExampleLoader.loadExampleData(this);
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

}
