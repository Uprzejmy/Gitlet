package piglet.Model.Entity;

/**
 * Created by Uprzejmy on 11.06.2017.
 */
public class User extends GroupMember implements IPermissionTarget {

    private String publicKey;
    private Group group;

    public User(String username, String publicKey)
    {
        super(username);

        this.publicKey = publicKey;
        this.group = new Group(username);
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
        return name;
    }

    public void setUsername(String username)
    {
        this.name = username;
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
        return 1;
    }

    @Override
    public boolean contains(User user)
    {
        return this == user;
    }

    @Override
    public String getConfigValue()
    {
        return name;
    }
}
