package piglet.Controller;

/**
 * Created by Uprzejmy on 11.06.2017.
 */
public class ControllerFactory {

    private ControllerFactory(){}

    public static IController createController(EController context)
    {
        switch (context)
        {
            case START: return new StartController();
            case USERS: return new UsersController();
            default: return new StartController();
        }
    }

}
