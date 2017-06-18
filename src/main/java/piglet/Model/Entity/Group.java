package piglet.Model.Entity;

import piglet.Model.Utils.UserComparator;

import java.util.Collection;
import java.util.HashSet;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Created by Uprzejmy on 11.06.2017.
 */
public class Group implements IPermissionTarget, Comparable<IPermissionTarget>{

    private String name;
    private SortedSet<User> users;

    public Group(String name)
    {
        users = new TreeSet<>(new UserComparator());
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

    public void removeUser(User user)
    {
        users.remove(user);

        if(user.getGroups().contains(this))
        {
            user.removeGroup(this);
        }
    }

    @Override
    public String toString()
    {
        return name;
    }

    @Override
    public int compareTo(IPermissionTarget target)
    {
        int targetTypeOrder = this.getImportance() - target.getImportance();

        if(targetTypeOrder != 0)
        {
            return targetTypeOrder;
        }

        return this.equals(target) ? 0 : 1;
    }

    @Override
    public int getImportance()
    {
        return 2;
    }

    @Override
    public boolean contains(User user)
    {
        for(User u : users)
        {
            if(u == user)
            {
                return true;
            }
        }

        return false;
    }
}
