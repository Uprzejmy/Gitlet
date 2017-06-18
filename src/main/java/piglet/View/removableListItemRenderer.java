package piglet.View;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Uprzejmy on 18.06.2017.
 */
public class removableListItemRenderer<User> extends JLabel implements ListCellRenderer<User> {

    @Override
    public Component getListCellRendererComponent(JList<? extends User> list, User user, int index, boolean isSelected, boolean cellHasFocus)
    {
        setText("test");

        return this;
    }

}