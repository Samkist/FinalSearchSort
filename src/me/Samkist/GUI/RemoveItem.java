package me.Samkist.GUI;

import BreezySwing.GBDialog;
import me.Samkist.Data.Master;
import me.Samkist.Objects.Employee;
import me.Samkist.Objects.Student;
import me.Samkist.Objects.Widget;

import javax.swing.*;

/**
 * Created by Samkist
 * https://github.com/Samkist
 */
public class RemoveItem extends GBDialog {

    private Object o;

    private JLabel label = addLabel("Are you sure you want to remove ", 1, 1, 2,1);
    private JButton yes = addButton("Yes", 2, 1, 1, 1);
    private JButton no = addButton("No", 2, 2, 1 ,1);

    public RemoveItem(JFrame jFrame, Object o) {
        super(jFrame);
        this.o = o;
        label.setText(label.getText() + o.toString());
        setTitle("Remove " + o.toString());
        setSize(400, 400);
        setVisible(true);
    }

    private void remove() {
        if(o instanceof Employee)
            Master.removeEmployee((Employee) o);
        if(o instanceof Student)
            Master.removeStudent((Student) o);
        if(o instanceof Widget)
            Master.removeWidget((Widget) o);
    }

    @Override
    public void buttonClicked(JButton jButton) {
        if(jButton.equals(yes))
            remove();
        setVisible(false);
    }
}
