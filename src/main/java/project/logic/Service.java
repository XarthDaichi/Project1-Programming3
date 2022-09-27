package project.logic;

import project.data.Data;
import project.data.XmlPersister;

import java.util.ArrayList;

public class Service {
    private static Service the_instance;

    public static Service instance() {
        if (the_instance == null) {
            the_instance = new Service();
        }
        return the_instance;
    }

    private Data data;

    private Service() {
        data = new Data();

        data.push(new Employee("111", "Franklin Chang", "11111", 1.0, new Branch_Office()));
        data.push(new Employee("222", "Sandra Cauffman", "22222", 2.0, new Branch_Office()));
        data.push(new Employee("333", "Ivan Vargas", "33333", 3.0, new Branch_Office()));
    }

    public <T> ArrayList<Employee> employees_search(T something) {
        return data.certain_objects_employees(something);
    }

    public Employee get_employee(String id) throws Exception {
        Employee result = data.all_objects_employees().stream().filter(e->e.get_id().equals(id)).findFirst().orElse(null);
        if (result!=null) return result;
        else throw new Exception("Employee does not exist");
    }
    public ArrayList<Employee> get_employees() {
        return data.all_objects_employees();
    }
    public void employee_add(Employee e) throws Exception {
        if (this.employees_search(e).size() == 0) data.push(e);
        else throw new Exception("Employee is already in the system");
    }

    public void employee_delete(Employee employee) throws Exception {
        Employee result = data.all_objects_employees().stream().filter(e->e.get_id().equals(employee.get_id())).findFirst().orElse(null);
        if (result != null) data.erase(employee);
        else throw new Exception("Employee does not exits");
    }

    public void employee_update(Employee employee) throws Exception{
        Employee result;
        try {
            result = this.get_employee(employee.get_id());
            data.erase(result);
            data.push(employee);
        } catch (Exception e) {
            throw new Exception("Employee does not exist");
        }
    }

    public void store() {
        try {
            XmlPersister.instance().store(data);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public <T> ArrayList<Branch_Office> branch_offices_search(T something) {
        return data.certain_objects_branch_offices(something);
    }

    public ArrayList<Branch_Office> get_branch_offices() {
        return data.all_objects_branch_offices();
    }

    public Branch_Office get_branch_office(String code) throws Exception {
        Branch_Office result = data.all_objects_branch_offices().stream().filter(e->e.get_code().equals(code)).findFirst().orElse(null);
        if (result!=null) return result;
        else throw new Exception("Branch Office does not exist");
    }

    public void branch_office_add(Branch_Office b) {
        if (this.branch_offices_search(b).size() == 0) data.push(b);
        else throw new IllegalArgumentException("Branch Office is already in the system");
    }

    public void branch_office_delete(Branch_Office branch_office) throws Exception {
        Branch_Office result = data.all_objects_branch_offices().stream().filter(e->e.get_code().equals(branch_office.get_code())).findFirst().orElse(null);
        if (result != null) data.erase(branch_office);
        else throw new Exception("Branch Office does not exits");
    }

    public void branch_office_update(Branch_Office branch_office) throws Exception{
        Branch_Office result;
        try {
            result = this.get_branch_office(branch_office.get_code());
            data.erase(result);
            data.push(branch_office);
        } catch (Exception e) {
            throw new Exception("Branch Office does not exist");
        }
    }
}
