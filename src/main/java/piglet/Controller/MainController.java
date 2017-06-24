package piglet.Controller;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.dircache.DirCache;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import piglet.Model.ConfigurationHandler.ConfigSaver;
import piglet.Model.Model;
import piglet.View.MainView;
import piglet.View.StartView;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * Created by Uprzejmy on 12.06.2017.
 */
public class MainController implements IController {
    private Model model;
    private MainView mainView;
    private StartView startView;

    public MainController()
    {
        model = Model.getInstance();

        startView = new StartView();
        mainView = new MainView();

        startView.setVisible(true);
        mainView.setVisible(false);

        model.initialize();

        mainView.getSaveButton().addActionListener(e -> saveAction());
        mainView.getSaveAndUploadButton().addActionListener(e -> { saveAction(); uploadAction(); });
        startView.getFileChooser().addActionListener(e -> selectWorkingDirectoryActionPerformed(e));
    }

    private void saveAction()
    {
        ConfigSaver.saveDataToFile();
    }

    private void switchViewToMain()
    {
        startView.setVisible(false);
        mainView.setVisible(true);
    }

    private void selectWorkingDirectoryActionPerformed(ActionEvent e)
    {
        if (e.getActionCommand().equals(javax.swing.JFileChooser.APPROVE_SELECTION))
        {
            //save working directory
            switchViewToMain();
        }
        else if (e.getActionCommand().equals(javax.swing.JFileChooser.CANCEL_SELECTION))
        {
            //System.out.println("cancel selection");
            //do nothing..
        }
    }

    private void uploadAction()
    {
        try
        {
            Git git = Git.open(new File("gitolite-admin"));
            git.add().addFilepattern(".").call();
            git.commit().setMessage(new Date().toString()).call();

            //git.push().call();
        }
        catch(IOException eio)
        {
            //todo notification for the user
        }
        catch(GitAPIException egit)
        {
            //todo notification for the user
        }

    }

}
