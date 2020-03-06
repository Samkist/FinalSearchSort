package me.Samkist.GUI;

import BreezySwing.GBDialog;
import me.Samkist.Data.ArrayList;
import me.Samkist.Data.Master;
import me.Samkist.Data.Search;
import me.Samkist.Data.Sort;
import me.Samkist.Objects.Employee;
import me.Samkist.Objects.Student;
import me.Samkist.Objects.Widget;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Samkist
 * https://github.com/Samkist
 */
public class SearchGUI extends GBDialog {

    private JFrame frame;

    private JList<String> list = addList(1, 1, 1,6);
    private ButtonGroup bg = new ButtonGroup();
    private JRadioButton employeeButton = addRadioButton("Employee (Salary)",1, 2, 1, 1);
    private JRadioButton studentButton = addRadioButton("Student (Name)",1, 3, 1, 1);
    private JRadioButton widgetButton = addRadioButton("Widget (Sold)",1, 4, 1, 1);

    private ButtonGroup bg2 = new ButtonGroup();
    private JRadioButton linearSearch = addRadioButton("Linear", 2, 2, 1, 1);
    private JRadioButton binarySearch = addRadioButton("Binary", 2, 3, 1, 1);

    private JTextField textField = addTextField("", 3, 2, 3, 1);

    ArrayList<Employee> employees;
    ArrayList<Student> students;
    ArrayList<Widget> widgets;

    @Override
    public void listDoubleClicked(JList<String> jList, String s) {
        if(employeeButton.isSelected()) {
            new RemoveItem(frame, employees.get(jList.getSelectedIndex()));
            search();
            updateList(employees);
        }

        else if(studentButton.isSelected()) {
            new RemoveItem(frame, students.get(jList.getSelectedIndex()));
            search();
            updateList(students);
        }

        else if(widgetButton.isSelected()) {
            new RemoveItem(frame, widgets.get(jList.getSelectedIndex()));
            search();
            updateList(widgets);
        }
    }

    public SearchGUI(JFrame jFrame) {
        super(jFrame);
        this.frame = jFrame;

        bg.add(employeeButton);
        bg.add(studentButton);
        bg.add(widgetButton);

        employeeButton.addChangeListener(cl);
        studentButton.addChangeListener(cl);
        widgetButton.addChangeListener(cl);

        employeeButton.setSelected(true);

        bg2.add(binarySearch);
        bg2.add(linearSearch);

        binarySearch.addChangeListener(cl);
        linearSearch.addChangeListener(cl);

        linearSearch.setSelected(true);

        textField.addKeyListener(kl);

        setTitle("Search");
        setSize(800, 400);
        setVisible(true);
    }

    public void updateList(ArrayList<? extends Comparable<?>> list) {
        DefaultListModel<String> model = (DefaultListModel) this.list.getModel();
        model.clear();
        list.stream().map(Comparable::toString).forEach(model::addElement);
    }

    ChangeListener cl = new ChangeListener() {
        @Override
        public void stateChanged(ChangeEvent changeEvent) {
            search();
        }
    };

    private void search() {
        if(employeeButton.isSelected()) {
            try {
                Double.parseDouble(textField.getText());
            } catch(Exception e) {
                return;
            }
            employees = new ArrayList<>();
            if(linearSearch.isSelected()) {
                Master.getEmployees().stream().filter(e -> e.compareTo(new Employee("",
                        Double.parseDouble(textField.getText()))) == 0)
                        .forEach(employees::add);
            } else {
                new Sort<>(Master.getEmployees(), "selection")
                        .get().forEach(employees::add);
                ArrayList<Employee> emps = new Search<>(employees, new Employee("",
                        Double.parseDouble(textField.getText()))).get();
                employees.clear();
                emps.forEach(employees::add);
            }
            updateList(employees);
        } else if(studentButton.isSelected()) {
            try {
                Double.parseDouble(textField.getText());
            } catch (Exception e) {
                return;
            }
            students = new ArrayList<>();
            if(linearSearch.isSelected()) {
                Master.getStudents().stream().filter(s -> s.compareTo(new Student("", Double.parseDouble(textField.getText()))) == 0)
                        .forEach(students::add);
            } else {
                new Sort<>(Master.getStudents(), "selection")
                        .get().forEach(students::add);
                try {
                    ArrayList<Student> sts = new Search<>(students, new Student("",
                            Double.parseDouble(textField.getText()))).get();
                    students.clear();
                    sts.forEach(students::add);
                } catch(Exception e) {
                    students.clear();
                }
            }
            updateList(students);
        } else if(widgetButton.isSelected()) {
            try {
                Integer.parseInt(textField.getText());
            } catch(Exception e) {
                return;
            }
            widgets = new ArrayList<>();
            if(linearSearch.isSelected()) {
                Master.getWidgets().stream().filter(w ->
                        w.compareTo(new Widget("000", Integer.parseInt(textField.getText()))) == 0)
                        .forEach(widgets::add);
            } else {
                new Sort<>(Master.getWidgets(), "selection")
                        .get().forEach(widgets::add);
                ArrayList<Widget> widgs = new Search<>(widgets, new Widget("000",
                        Integer.parseInt(textField.getText()))).get();
                widgets.clear();
                widgs.forEach(widgets::add);
            }
            updateList(widgets);
        }
    }

    private KeyListener kl = new KeyListener() {
        @Override
        public void keyTyped(KeyEvent keyEvent) {

        }

        @Override
        public void keyPressed(KeyEvent keyEvent) {

        }

        @Override
        public void keyReleased(KeyEvent keyEvent) {
            search();
        }
    };
}
