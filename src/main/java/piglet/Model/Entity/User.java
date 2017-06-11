package piglet.Model.Entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Uprzejmy on 11.06.2017.
 */
public class User implements IPermissionTarget{

    private String username;
    private String publicKey;
    private List<Group> groups;

    public User(String username, String publicKey)
    {
        this.username = username;
        this.publicKey = publicKey;

        groups = new ArrayList<>();
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

}
