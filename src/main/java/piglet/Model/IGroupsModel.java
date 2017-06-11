package piglet.Model;

import piglet.Model.Entity.Group;
import piglet.View.IObserver;

import java.util.Collection;

/**
 * Created by Uprzejmy on 12.06.2017.
 */
public interface IGroupsModel {
    void registerGroupsModelObserver(IObserver observer);
    Collection<Group> getGroups();
}
