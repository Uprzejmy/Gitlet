package piglet.Model;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Uprzejmy on 11.06.2017.
 */
public class Model {

    private static Model model = null;
    Collection<User> users;

    private Model()
    {
        users = new ArrayList<>();

        users.add(new User("user1","sha example key1"));
        users.add(new User("user2","sha example key2"));
    }

    public static Model getInstance()
    {
        if(model == null)
        {
            model = new Model();
        }

        return model;
    }

    public String example()
    {
        return "model function call";
    }
}
