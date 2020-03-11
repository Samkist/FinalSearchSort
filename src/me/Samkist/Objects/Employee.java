package me.Samkist.Objects;

import java.text.DecimalFormat;

/**
 * Created by Samkist
 * https://github.com/Samkist
 */
public class Employee implements Comparable<Employee> {

    private String name;
    private double salary;

    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    @Override
    public int compareTo(Employee employee) {
        return (int) (salary - employee.getSalary());
    }

    @Override
    public String toString() {
        DecimalFormat formatter = new DecimalFormat("$0.00");
        return getName() + " - " + formatter.format(getSalary());
    }
}
