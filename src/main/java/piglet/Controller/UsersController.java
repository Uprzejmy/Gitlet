package piglet.Controller;

import piglet.Model.Model;
import piglet.View.UsersView;

/**
 * Created by Uprzejmy on 11.06.2017.
 */
public class UsersController implements IController{
    Model model;
    UsersView view;

    public UsersController()
    {
        model = Model.getInstance();
        //view = new UsersView(model.getUsersModel());

        //view.update();

        // model.addUser("test", "test");
    }
}
