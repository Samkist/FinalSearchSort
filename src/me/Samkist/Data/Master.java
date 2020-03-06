package me.Samkist.Data;

import me.Samkist.Objects.Employee;
import me.Samkist.Objects.Student;
import me.Samkist.Objects.Widget;

/**
 * Created by Samkist
 * https://github.com/Samkist
 */
public class Master {
    private static ArrayList<Employee> employees = new ArrayList<>();
    private static ArrayList<Student> students = new ArrayList<>();
    private static ArrayList<Widget> widgets = new ArrayList<>();

    public static void addEmployee(Employee e) {
        employees.add(e);
    }

    public static void removeEmployee(Employee e) {
        employees.remove(e);
    }

    public static void removeEmployeeByIndex(int index) {
        employees.remove(index);
    }

    public static void addStudent(Student s) {
        students.add(s);
    }

    public static void removeStudent(Student s) {
        students.remove(s);
    }

    public static void removeStudentByIndex(int index) {
        students.remove(index);
    }

    public static void addWidget(Widget w) {
        widgets.add(w);
    }

    public static void removeWidget(Widget w) {
        widgets.remove(w);
    }

    public static void removeWidgetByIndex(int index) {
        widgets.remove(index);
    }

    public static ArrayList<Employee> getEmployees() {
        return employees;
    }

    public static ArrayList<Student> getStudents() {
        return students;
    }

    public static ArrayList<Widget> getWidgets() {
        return widgets;
    }
}
