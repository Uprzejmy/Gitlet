package piglet.Controller;

import piglet.Model.Model;
import piglet.View.View;

/**
 * Created by Uprzejmy on 11.06.2017.
 */
public class Controller {

    Model model;
    View view;

    public Controller(Model model, View view)
    {
        this.model = model;
        this.view = view;
    }

    public void run()
    {
        System.out.println( "Hello World MVC!" );
    }
}
