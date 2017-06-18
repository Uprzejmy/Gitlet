package piglet.Model.Entity;

import piglet.Model.Utils.GroupComparator;

import java.util.*;

/**
 * Created by Uprzejmy on 11.06.2017.
 */
public class User implements IPermissionTarget {

    private String username;
    private String publicKey;
    private Collection<Group> groups;

    public User(String username, String publicKey)
    {
        this.username = username;
        this.publicKey = publicKey;

        groups = new TreeSet<>(new GroupComparator());
    }

    public String getPublicKey()
    {
        return publicKey;
    }

    public void setPublicKey(String publicKey)
    {
        this.publicKey = publicKey;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public Collection<Group> getGroups()
    {
        return groups;
    }

    public void addGroup(Group group)
    {
        this.groups.add(group);

        if(!group.getUsers().contains(this))
        {
            group.addUser(this);
        }
    }

    @Override
    public String toString()
    {
        return username;
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
        return 1;
    }
}
