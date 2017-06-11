package piglet.Controller;

import piglet.ControllerManager;
import piglet.Model.Model;
import piglet.View.StartView;

/**
 * Created by Uprzejmy on 11.06.2017.
 */
public class StartController implements IController{
    private Model model;
    private StartView view;

    public StartController()
    {
        this.model = Model.getInstance();
        this.view = new StartView();

        configureListeners();
    }

    private void switchViewToUsers()
    {
        //todo proper view dispose
        this.view = null;
        ControllerManager.getInstance().changeController(EController.USERS);
    }

    private void configureListeners()
    {
        view.getUsersViewButton().addActionListener(e -> switchViewToUsers());
    }

}
