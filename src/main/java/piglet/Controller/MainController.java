package piglet.Controller;

import piglet.Model.Model;
import piglet.View.MainView;

/**
 * Created by Uprzejmy on 12.06.2017.
 */
public class MainController implements IController {
    private Model model;
    private MainView mainView;

    public MainController()
    {
        model = Model.getInstance();
        mainView = new MainView();
    }
}
