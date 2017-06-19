package piglet.Controller;

import piglet.Model.ConfigurationHandler.ConfigSaver;
import piglet.Model.Model;
import piglet.View.MainView;

/**
 * Created by Uprzejmy on 12.06.2017.
 */
public class MainController implements IController {
    private Model model;
    private MainView mainView;

    public MainController()
    {
        model = Model.getInstance();
        mainView = new MainView();

        model.initialize();

        mainView.getSaveButton().addActionListener(e -> saveAction());
        mainView.getSaveAndUploadButton().addActionListener(e -> { saveAction(); uploadAction(); });
    }

    private void saveAction()
    {
        ConfigSaver.saveDataToFile();
    }

    private void uploadAction()
    {

    }

}
