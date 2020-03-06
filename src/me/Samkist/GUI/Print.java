package me.Samkist.GUI;

import BreezySwing.GBDialog;
import me.Samkist.Data.ArrayList;
import me.Samkist.Data.Master;
import me.Samkist.Data.Sort;
import me.Samkist.Objects.Employee;
import me.Samkist.Objects.Student;
import me.Samkist.Objects.Widget;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Created by Samkist
 * https://github.com/Samkist
 */
public class Print extends GBDialog {

    private static JFrame frame;

    private JList<String> list = addList(1, 1, 1,6);

    private ButtonGroup bg = new ButtonGroup();
    private JRadioButton employeeButton = addRadioButton("Employee",1, 2, 1, 1);
    private JRadioButton studentButton = addRadioButton("Student",1, 3, 1, 1);
    private JRadioButton widgetButton = addRadioButton("Widget",1, 4, 1, 1);

    private ButtonGroup bg2 = new ButtonGroup();
    private JRadioButton insertionButton = addRadioButton("Insertion Sort", 2, 2, 1, 1);
    private JRadioButton selectionButton = addRadioButton("Selection Sort", 2, 3, 1,1);

    private ArrayList<Comparable> data;

    public Print(JFrame jFrame) {
        super(jFrame);
        this.frame = jFrame;

        bg.add(employeeButton);
        bg.add(studentButton);
        bg.add(widgetButton);

        employeeButton.addChangeListener(cl);
        studentButton.addChangeListener(cl);
        widgetButton.addChangeListener(cl);

        employeeButton.setSelected(true);

        bg2.add(insertionButton);
        bg2.add(selectionButton);

        insertionButton.addChangeListener(cl);
        selectionButton.addChangeListener(cl);

        insertionButton.setSelected(true);

        setTitle("Print");
        setSize(600, 400);
        setVisible(true);
    }

    @Override
    public void listDoubleClicked(JList<String> jList, String s) {
        int index = jList.getSelectedIndex();
        if(data.get(index) instanceof Employee) {
            new RemoveItem(frame, data.get(index));
        }
        updateList();
    }

    private void updateList() {
        data = new ArrayList<>();
        if(employeeButton.isSelected()) {
            if(insertionButton.isSelected()) {
                new Sort<Employee>(Master.getEmployees(), "insertion")
                .get().forEach(data::add);
            } else {
                new Sort<Employee>(Master.getEmployees(), "selection")
                .get().forEach(data::add);
            }
        }

        else if(studentButton.isSelected()) {
            if(insertionButton.isSelected()) {
                new Sort<Student>(Master.getStudents(), "insertion")
                        .get().forEach(data::add);
            } else {
                new Sort<Student>(Master.getStudents(), "selection")
                        .get().forEach(data::add);
            }
        }

        else if(widgetButton.isSelected()) {
            if(insertionButton.isSelected()) {
                new Sort<Widget>(Master.getWidgets(), "insertion")
                        .get().forEach(data::add);
            } else {
                new Sort<Widget>(Master.getWidgets(), "selection")
                        .get().forEach(data::add);
            }
        }

        DefaultListModel<String> model = (DefaultListModel<String>) list.getModel();
        model.clear();
        data.stream().map(Comparable::toString).forEach(model::addElement);
    }

    private ChangeListener cl = new ChangeListener() {
        @Override
        public void stateChanged(ChangeEvent changeEvent) {
            updateList();
        }
    };
}
