package piglet.Model.ConfigurationHandler;

import org.apache.commons.io.FilenameUtils;
import piglet.Model.Entity.Group;
import piglet.Model.Entity.User;
import piglet.Model.Model;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * Created by Uprzejmy on 19.06.2017.
 */
public class ConfigLoader {
    private static Path configurationFilePath = Paths.get("conf" + File.separator + "gitolite.conf");
    private static String path;
    private static Pattern groupNamePattern = Pattern.compile("^@(.*?) =");

    public static void loadDataFromFile()
    {
        loadUsersData();
        loadGroupsData();
    }

    private static void loadUsersData()
    {
        // load all users data from users files
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get("users")))
        {
            //for each file "username.pub"
            for (Path file: stream)
            {
                //create new user
                String username = FilenameUtils.removeExtension(file.getFileName().toString());
                String publicKey = Files.readAllLines(file,UTF_8).get(0);

                //convert all underscores back to spaces
                Model.getInstance().getUsersModel().addUser(username.replaceAll("_"," "), publicKey);
            }
        }
        catch (IOException | DirectoryIteratorException x)
        {
            // IOException can never be thrown by the iteration.
            // In this snippet, it can only be thrown by newDirectoryStream.
            x.printStackTrace();
        }
    }

    private static void loadGroupsData()
    {
        List<String> fileData;

        try
        {
            fileData = Files.readAllLines(configurationFilePath, UTF_8);
        }
        catch (IOException e)
        {
            e.printStackTrace();
            //just in case initialize list with empty data to not let for to iterate
            fileData = new ArrayList<>();
        }

        for(String configLine : fileData)
        {
            if(configLine.startsWith("@"))
            {
                loadGroup(configLine);
            }
        }
    }

    private static void loadGroup(String configLine)
    {
        try
        {
            Matcher matcher = groupNamePattern.matcher(configLine);
            matcher.find();

            String name = matcher.group(1).replaceAll("_"," ");
            Model.getInstance().getGroupsModel().addGroup(name);
            Group group = Model.getInstance().getGroupsModel().findGroupByGroupName(name);

            Pattern allUsernamesPattern = Pattern.compile("= (.*)$");
            matcher = allUsernamesPattern.matcher(configLine);
            matcher.find();

            for(String username : matcher.group(1).split("\\s"))
            {
                group.addUser(Model.getInstance().getUsersModel().findUserByUsername(username.replaceAll("_", " ")));
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

}
