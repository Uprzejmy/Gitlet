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
        this.model = Model.getInstance();
        this.view = new UsersView();
    }
}
