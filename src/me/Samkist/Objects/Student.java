package me.Samkist.Objects;

/**
 * Created by Samkist
 * https://github.com/Samkist
 */
public class Student implements Comparable<Student> {

    private String name;
    private double gpa;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public Student(String name, double gpa) {
        this.name = name;
        this.gpa = gpa;
    }

    @Override
    public int compareTo(Student student) {
        return name.compareTo(student.getName());
    }

    @Override
    public String toString() {
        return getName() + " - " + getGpa();
    }
}
