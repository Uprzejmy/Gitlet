package piglet;

import piglet.Controller.*;

/**
 * Created by Uprzejmy on 11.06.2017.
 */
public class ControllerManager {
    private static ControllerManager controllerManager;

    private IController currentController;

    private ControllerManager(){}

    public static ControllerManager getInstance()
    {
        if(controllerManager == null)
        {
            controllerManager = new ControllerManager();
        }

        return controllerManager;
    }

    public void changeController(EController eController)
    {
        //todo implement dispose methods
        currentController = null;

        currentController = ControllerFactory.createController(eController);
    }
}
