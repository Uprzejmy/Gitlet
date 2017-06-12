package piglet.Controller;

import piglet.Model.Model;
import piglet.View.GroupsView;

/**
 * Created by Uprzejmy on 11.06.2017.
 */
public class GroupsController implements IController {
    Model model;
    GroupsView view;

    public GroupsController()
    {
        model = Model.getInstance();
        view = new GroupsView(model.getGroupsModel());

        view.update();
    }
}
