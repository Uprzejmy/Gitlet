package piglet.Model.ConfigurationHandler;

import org.apache.commons.io.FilenameUtils;
import piglet.Model.Entity.*;
import piglet.Model.Model;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * Created by Uprzejmy on 19.06.2017.
 */
public class ConfigLoader {
    private static String usersPath = "keydir";
    private static String configurationFilePath = "conf" + File.separator + "gitolite.conf";

    private static Pattern groupNamePattern = Pattern.compile("^@(.*?) =");
    private static Pattern repositoryNamePattern = Pattern.compile("^repo (.*?)$");
    private static Pattern repositoryPermissionPattern = Pattern.compile("^    (.*?) = (.*?)$");

    public static void loadDataFromFile(File workingDirectory)
    {
        usersPath = workingDirectory.getAbsolutePath() + File.separator + "keydir";
        configurationFilePath = workingDirectory.getAbsolutePath() + File.separator + "conf" + File.separator + "gitolite.conf";

        loadUsersData();
        loadGroupsData();
        loadRepositoriesData();
    }

    private static void loadUsersData()
    {
        // load all users data from users files
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(usersPath)))
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
            fileData = Files.readAllLines(Paths.get(configurationFilePath), UTF_8);
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

            String name = matcher.group(1).replaceAll(" ", "").replaceAll("_"," ");
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

    private static void loadRepositoriesData()
    {
        List<String> fileData;

        try
        {
            fileData = Files.readAllLines(Paths.get(configurationFilePath), UTF_8);
        }
        catch (IOException e)
        {
            e.printStackTrace();
            //just in case initialize list with empty data to not let for to iterate
            fileData = new ArrayList<>();
        }

        Repository lastRepository = null;

        for(String configLine : fileData)
        {
            if(configLine.startsWith("repo"))
            {
                lastRepository = loadRepository(configLine);
            }

            else if(configLine.startsWith("    R"))
            {
                loadRepositoryPermission(lastRepository, configLine);
            }
        }
    }

    private static Repository loadRepository(String configLine)
    {
        try
        {
            Matcher matcher = repositoryNamePattern.matcher(configLine);
            matcher.find();

            String name = matcher.group(1).replaceAll("_"," ");
            Model.getInstance().getRepositoriesModel().addRepository(name);
            return Model.getInstance().getRepositoriesModel().findRepositoryByName(name);

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }

    private static void loadRepositoryPermission(Repository repository, String configLine)
    {
        try
        {
            Matcher matcher = repositoryPermissionPattern.matcher(configLine);
            matcher.find();

            String permission = matcher.group(1).replaceAll(" ","");

            IPermissionTarget target;

            if(matcher.group(2).replaceAll(" ","").startsWith("@"))
            {
                target = Model.getInstance().getGroupsModel().findGroupByGroupName(matcher.group(2).replaceAll(" ","").substring(1).replaceAll("_", " "));
            }
            else
            {
                target = Model.getInstance().getUsersModel().findUserByUsername(matcher.group(2).replaceAll(" ","").replaceAll("_", " "));
            }

            repository.addRepositoryPermission(target, getEPermissionValueFromConfig(permission));
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    private static EPermission getEPermissionValueFromConfig(String value)
    {
        switch (value)
        {
            case "R":
                return EPermission.R;
            case "RW":
                return EPermission.RW;
            case "RW+":
                return EPermission.ADMIN;
            default:
                return EPermission.R;
        }
    }

}
