package me.Samkist.Data;

import me.Samkist.Objects.Employee;
import me.Samkist.Objects.Student;
import me.Samkist.Objects.Widget;

/**
 * Created by Samkist
 * https://github.com/Samkist
 */
public class Master {
    private static ArrayList<Comparable> comparables = new ArrayList<>();

    public static void addEmployee(Employee e) {
        comparables.add(e);
    }

    public static void removeEmployee(Employee e) {
        comparables.remove(e);
    }

    public static void removeEmployeeByIndex(int index) {
        comparables.remove(getEmployees().remove(index));
    }

    public static void addStudent(Student s) {
        comparables.add(s);
    }

    public static void removeStudent(Student s) {
        comparables.remove(s);
    }

    public static void removeStudentByIndex(int index) {
        comparables.remove(getStudents().remove(index));
    }

    public static void addWidget(Widget w) {
        comparables.add(w);
    }

    public static void removeWidget(Widget w) {
        comparables.remove(w);
    }

    public static void removeWidgetByIndex(int index) {
        comparables.remove(getWidgets().remove(index));
    }

    public static ArrayList<Employee> getEmployees() {
        ArrayList<Employee> employees = new ArrayList<>();
        comparables.forEach(c -> {
            if(c instanceof Employee)employees.add((Employee) c);
        });
        return employees;
    }

    public static ArrayList<Student> getStudents() {
        ArrayList<Student> students = new ArrayList<>();
        comparables.forEach(c -> {
            if(c instanceof Student)students.add((Student) c);
        });
        return students;
    }

    public static ArrayList<Widget> getWidgets() {
        ArrayList<Widget> widgets = new ArrayList<>();
        comparables.forEach(c -> {
            if(c instanceof Widget)widgets.add((Widget) c);
        });
        return widgets;
    }
}
