package me.Samkist.GUI;

import BreezySwing.GBDialog;
import me.Samkist.Data.Master;
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
public class Create extends GBDialog {

    private static JFrame frame;

    private JList<String> list = addList(1, 1, 1,6);
    private ButtonGroup bg = new ButtonGroup();
    private JRadioButton employeeButton = addRadioButton("Employee",1, 2, 1, 1);
    private JRadioButton studentButton = addRadioButton("Student",1, 3, 1, 1);
    private JRadioButton widgetButton = addRadioButton("Widget",1, 4, 1, 1);
    private JLabel label1 = addLabel("Name", 2, 2, 3, 1);
    private JTextField field1 = addTextField("", 3, 2, 3, 1);
    private JLabel label2 = addLabel("Salary", 4, 2, 3, 1);
    private JTextField field2 = addTextField("", 5, 2, 3, 1);
    private JButton create = addButton("Create", 6, 2, 3, 1);

    @Override
    public void listDoubleClicked(JList<String> jList, String s) {
        int index = jList.getSelectedIndex();
        if(!s.contains(" - ")) return;
        //Widget's Zero
        int widgetStart = 3 + Master.getEmployees().size() + Master.getStudents().size();
        if(index >= widgetStart) {
            Widget w = Master.getWidgets().get(index - widgetStart);
            new RemoveItem(frame, w);
            updateList();
            return;
        }
        //Student's Zero
        int studentStart = 2 + Master.getEmployees().size();
        if(index >= studentStart) {
            Student st = Master.getStudents().get(index - studentStart);
            new RemoveItem(frame, st);
            updateList();
            return;
        }
        if(index > 0 && index <= Master.getEmployees().size()) {
            Employee e = Master.getEmployees().get(index - 1);
            new RemoveItem(frame, e);
            updateList();
        }
    }



    public Create(JFrame jFrame) {
        super(jFrame);
        this.frame = jFrame;

        bg.add(employeeButton);
        employeeButton.addChangeListener(changeListener);
        bg.add(studentButton);
        studentButton.addChangeListener(changeListener);
        bg.add(widgetButton);
        widgetButton.addChangeListener(changeListener);
        employeeButton.setSelected(true);
        updateList();

        setSize(600, 400);
        setTitle("Create");
        setVisible(true);
    }

    @Override
    public void buttonClicked(JButton jButton) {
        if(jButton.equals(create))
            create();
    }

    private void updateList() {
        DefaultListModel<String> model = (DefaultListModel) list.getModel();
        model.clear();
        model.addElement("Employees");
        Master.getEmployees().stream().map(Employee::toString).forEach(model::addElement);
        model.addElement("Students");
        Master.getStudents().stream().map(Student::toString).forEach(model::addElement);;
        model.addElement("Widgets");
        Master.getWidgets().stream().map(Widget::toString).forEach(model::addElement);;
    }

    private void clearFields() {
        field1.setText("");
        field2.setText("");
    }

    private void create() {
        try {
            if(widgetButton.isSelected()) {
                Integer.parseInt(field1.getText());
                if (field1.getText().length() != 3) throw new Exception();
            }
        } catch(Exception e) {
            messageBox("Invalid ID (000)");
            clearFields();
            return;
        }
        if(field1.getText().length() == 0) {
            messageBox("Invalid " + label1.getText());
            clearFields();
            return;
        } else if(field2.getText().length() == 0) {
            messageBox("Invalid " + label2.getText());
            clearFields();
            return;
        }
        try {
            if (employeeButton.isSelected()) {
                Master.addEmployee(new Employee(field1.getText(), Double.parseDouble(field2.getText())));
            } else if (studentButton.isSelected()) {
                Double gpa = Double.parseDouble(field2.getText());
                if(gpa < 0 || gpa > 5)  { messageBox("Invalid GPA (0 < GPA < 5)"); clearFields(); return; }
                Master.addStudent(new Student(field1.getText(), gpa));
            } else if (widgetButton.isSelected()) {
                Master.addWidget(new Widget(field1.getText(), Integer.parseInt(field2.getText())));
            }
        } catch(Exception e) {
            clearFields();
            messageBox("Invalid " + label2.getText());
        }
        updateList();
    }

    ChangeListener changeListener = new ChangeListener() {
        @Override
        public void stateChanged(ChangeEvent changeEvent) {
            if(changeEvent.getSource() instanceof JRadioButton) {
                if(employeeButton.isSelected()) {
                    label1.setText("Name");
                    label2.setText("Salary");
                } else if(studentButton.isSelected()) {
                    label1.setText("Name");
                    label2.setText("GPA (0-5)");
                } else if(widgetButton.isSelected()) {
                    label1.setText("Code (000)");
                    label2.setText("# Sold");
                }
            }
        }
    };
}
