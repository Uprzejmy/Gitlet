package piglet.Model;

/**
 * Created by Uprzejmy on 11.06.2017.
 */
public class User {

    private String username;
    private String publicKey;

    public User(String username, String publicKey)
    {
        this.username = username;
        this.publicKey = publicKey;
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

}
