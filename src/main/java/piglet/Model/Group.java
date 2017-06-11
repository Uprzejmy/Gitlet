package piglet.Model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Uprzejmy on 11.06.2017.
 */
public class Group implements IPermissionTarget {

    private String name;
    private List<User> users = new ArrayList<>();

    public Group(String name)
    {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<User> getUsers() {
        return users;
    }

    public void addUser(User user)
    {
        users.add(user);

        if(!user.getGroups().contains(this))
        {
            user.addGroup(this);
        }
    }

    @Override
    public String toString()
    {
        return name + " users: " + users.size();
    }
}
