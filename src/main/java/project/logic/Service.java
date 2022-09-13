package project.logic;

import project.data.Data;

import java.util.ArrayList;

public class Service {
    private static Service the_instance;

    public static Service instance() {
        if (the_instance == null) {
            the_instance = new Service();
        }
        return the_instance;
    }

    private Data<Employee> data_employees;
    private Data<Branch_Office> data_branch_offices;

    private Service() {
        data_employees = new Data<Employee>();
        data_branch_offices = new Data<Branch_Office>();

        data_employees.push(new Employee("111", "Franklin Chang", "11111", 1.0, new Branch_Office()));
        data_employees.push(new Employee("222", "Sandra Cauffman", "22222", 2.0, new Branch_Office()));
        data_employees.push(new Employee("333", "Ivan Vargas", "33333", 3.0, new Branch_Office()));
    }

    public <T> ArrayList<Employee> employees_search(T something) {
        return data_employees.certain_objects(something);
    }

    public ArrayList<Employee> get_employees() {
        return data_employees.all_objects();
    }
    public void employees_add(Employee e) {
        if (this.employees_search(e).isEmpty())
            data_employees.push(e);
        else
            throw new IllegalArgumentException("Employee is already in the system");
    }

    public <T> ArrayList<Branch_Office> branch_offices_search(T something) {
        return data_branch_offices.certain_objects(something);
    }

    public ArrayList<Branch_Office> get_branch_offices() {
        return data_branch_offices.all_objects();
    }

    public void branch_offices_add(Branch_Office b) {
        if (this.branch_offices_search(b).isEmpty())
            data_branch_offices.push(b);
        else
            throw new IllegalArgumentException("Branch Office is already in the system");
    }
}
