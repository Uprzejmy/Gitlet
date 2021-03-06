package piglet.Controller;

import com.jcraft.jsch.Session;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.errors.RepositoryNotFoundException;
import org.eclipse.jgit.transport.JschConfigSessionFactory;
import org.eclipse.jgit.transport.OpenSshConfig;
import org.eclipse.jgit.transport.SshSessionFactory;
import piglet.Model.ConfigurationHandler.ConfigLoader;
import piglet.Model.ConfigurationHandler.ConfigSaver;
import piglet.Model.Model;
import piglet.View.MainView;
import piglet.View.StartView;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Uprzejmy on 12.06.2017.
 */
public class MainController implements IController {
    private Model model;
    private MainView mainView;
    private StartView startView;

    private File workingDirectory;
    Git git;

    public MainController()
    {
        model = Model.getInstance();

        startView = new StartView();
        mainView = new MainView();

        startView.setVisible(true);
        mainView.setVisible(false);

        model.initialize();

        SshSessionFactory sshSessionFactory = new JschConfigSessionFactory()
        {
            @Override
            protected void configure(OpenSshConfig.Host host, Session session )
            {
                session.setConfig("StrictHostKeyChecking", "no");
            }
        };

        SshSessionFactory.setInstance(sshSessionFactory);

        mainView.getSaveButton().addActionListener(e -> saveAction());
        mainView.getSaveAndUploadButton().addActionListener(e -> { saveAction(); uploadAction(); });
        startView.getExistingConfigurationFileChooser().addActionListener(e -> selectWorkingDirectoryActionPerformed(e));
        startView.getDownloadConfigurationActionButton().addActionListener(e -> downloadNewConfigurationActionPerformed(e));
    }

    private void saveAction()
    {
        ConfigSaver.saveDataToFile(workingDirectory);
    }

    private void switchViewToMain()
    {
        startView.setVisible(false);
        mainView.setVisible(true);
    }

    private void downloadNewConfigurationActionPerformed(ActionEvent e)
    {
        try
        {
            git = Git.cloneRepository()
                    .setURI("git@" + startView.getServerAddress() + ":gitolite-admin")
                    .setDirectory(startView.getNewConfigurationFileChooser().getSelectedFile())
                    .call();

            workingDirectory = startView.getNewConfigurationFileChooser().getSelectedFile();
            ConfigLoader.loadDataFromFile(workingDirectory);
            switchViewToMain();

            return;
        }
        catch(GitAPIException exception)
        {
            System.out.println("Exception during pull");
            System.out.println(exception.toString());
        }

        startView.getServerConnectionErrorLabel().setText("error downloading configuration");
    }

    private void selectWorkingDirectoryActionPerformed(ActionEvent e)
    {
        if (e.getActionCommand().equals(javax.swing.JFileChooser.APPROVE_SELECTION))
        {
            if(validateConfigurationDirectory(startView.getExistingConfigurationFileChooser().getSelectedFile()))
            {
                try
                {
                    workingDirectory = startView.getExistingConfigurationFileChooser().getSelectedFile();
                    git = Git.open(workingDirectory);

                    ConfigLoader.loadDataFromFile(workingDirectory);

                    //save working directory
                    switchViewToMain();
                    return;
                }
                catch(RepositoryNotFoundException rnfe)
                {
                    System.out.println("not a git repository");
                }
                catch(IOException eio)
                {
                    //todo notification for the user
                    System.out.println("io exception");
                }
            }
        }
        else if (e.getActionCommand().equals(javax.swing.JFileChooser.CANCEL_SELECTION))
        {
            //System.out.println("cancel selection");
            //do nothing..
        }

        startView.getInvalidRepositorySelectionLabel().setText("invalid settings directory selected");
    }

    private boolean validateConfigurationDirectory(File configurationDirectory)
    {
        List<String> files = new ArrayList<>();

        for(File file : configurationDirectory.listFiles())
        {
            if(file.isDirectory())
            {
                if(file.getName().equals(".git"))
                    files.add("repository");
                if(file.getName().equals("conf"))
                    files.add("configuration");
                if(file.getName().equals("keydir"))
                    files.add("users");
            }
        }

        return files.size() == 3;
    }

    private void uploadAction()
    {
        try
        {
            git.add().addFilepattern(".").call();
            git.commit().setMessage(new Date().toString()).call();

            git.push().call();
        }
        catch(GitAPIException egit)
        {
            //todo notification for the user
            System.out.println("error during upload action");
            System.out.println(egit.toString());
        }

    }

}
