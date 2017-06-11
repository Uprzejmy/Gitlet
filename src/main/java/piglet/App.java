package piglet;

import piglet.Controller.Controller;
import piglet.Model.Model;
import piglet.View.View;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Model model = new Model();
        View view = new View();
        Controller controller = new Controller(model, view);
        controller.run();
    }
}
