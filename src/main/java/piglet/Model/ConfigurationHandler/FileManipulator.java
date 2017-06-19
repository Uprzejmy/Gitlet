package piglet.Model.ConfigurationHandler;

import piglet.Model.Entity.*;
import piglet.Model.Model;
import piglet.Model.UsersModel;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;
import static java.nio.charset.StandardCharsets.UTF_8;
import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE_NEW;
import static java.nio.file.StandardOpenOption.TRUNCATE_EXISTING;

/**
 * Created by Uprzejmy on 19.06.2017.
 */
public class FileManipulator {
    private static Path configurationFilePath = Paths.get("conf" + File.separator + "gitolite.conf");
    private static String path;

    public static void saveDataToFile()
    {
        saveUsersData();
        saveConfigFile();
    }

    public static void loadDataFromFile()
    {

    }

    private static void saveUsersData()
    {
        try
        {
            //delete all files in directory
            try
            {

                Files.walk(Paths.get("users"))
                        .sorted(Comparator.reverseOrder())
                        .map(Path::toFile)
                        .forEach(File::delete);
            }
            catch(NoSuchFileException e)
            {
                //it's ok we will create folder anyways
            }

            //create directory
            Files.createDirectory(Paths.get("users"));

            //for all users in memory
            for(User user : Model.getInstance().getUsersModel().getUsers())
            {
                //create separate file
                Files.write(Paths.get("users" + File.separator + user.getUsername().replaceAll("\\s","_") + ".pub"), Arrays.asList(user.getPublicKey()), UTF_8);
            }
        }
        catch(IOException e)
        {
            // todo proper exception handling
            e.printStackTrace();
        }
    }

    private static void saveConfigFile()
    {
        try
        {
            //we want to override entire file
            Files.write(configurationFilePath, Arrays.asList(""), UTF_8, TRUNCATE_EXISTING);

            saveGroupsDefinitions();
            //add separator between groups definitions and repositories definitions
            Files.write(configurationFilePath, Arrays.asList(""), UTF_8, APPEND);
            saveRepositoriesDefinitions();
        }
        catch(IOException e)
        {
            // todo proper exception handling
            e.printStackTrace();
        }
    }

    private static void saveGroupsDefinitions() throws IOException
    {
        //for all groups in memory
        for(Group group : Model.getInstance().getGroupsModel().getGroups())
        {
            StringBuilder stringBuilder = new StringBuilder();

            //append group definition

            //the "@groupname = " goes first
            stringBuilder.append("@" + group.getName().replaceAll("\\s","_") + " =");

            //then all the usernames space separated
            for(User user : group.getUsers())
            {
                stringBuilder.append(" " + user.getUsername().replaceAll("\\s","_"));
            }

            //finally save the whole group record to the file
            Files.write(configurationFilePath, Arrays.asList(stringBuilder.toString()), UTF_8, APPEND);
        }
    }

    private static void saveRepositoriesDefinitions() throws IOException
    {
        //for all repositories in memory
        for(Repository repository : Model.getInstance().getRepositoriesModel().getRepositories())
        {
            StringBuilder stringBuilder = new StringBuilder();

            //append repository definition

            //the "repo reponame" goes first
            stringBuilder.append("repo " + repository.getName().replaceAll("\\s","_")+ "\n");

            //then all the repository permissions records in new lines
            for(RepositoryPermission repositoryPermission : repository.getRepositoryPermissions())
            {
                stringBuilder.append(
                        "    " +
                        getEPermissionConfigValue(repositoryPermission.getPermission()) +
                        " = " +
                        repositoryPermission.getTarget().getConfigValue().replaceAll("\\s","_") +
                        "\n");
            }

            //finally save the whole repository to the file
            Files.write(configurationFilePath, Arrays.asList(stringBuilder.toString()), UTF_8, APPEND);
        }
    }

    private static String getEPermissionConfigValue(EPermission ePermission)
    {
        switch (ePermission)
        {
            case R: return "R";
            case RW: return "RW";
            case ADMIN: return "RW+";
            default: return "R";
        }
    }
}
