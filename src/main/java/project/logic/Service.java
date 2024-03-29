package project.logic;

import project.data.Branch_Office_Dao;
import project.data.Data;
import project.data.Employee_Dao;
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

    private Branch_Office_Dao branch_office_dao;

    private Employee_Dao employee_dao;

    private Data data;

    private Service() {
        //data = new Data();
        branch_office_dao = new Branch_Office_Dao();
        employee_dao = new Employee_Dao();
        try {
//            data=XmlPersister.instance().load();
        } catch(Exception e) {
            data = new Data();
            //data.push(new Employee("111", "Franklin Chang", "11111", 1.0, new Branch_Office()));
            //data.push(new Employee("222", "Sandra Cauffman", "22222", 2.0, new Branch_Office()));
            //data.push(new Employee("333", "Ivan Vargas", "33333", 3.0, new Branch_Office()));
        }
    }

//    public <T> ArrayList<Employee> employees_search(T something) {
//        return data.certain_objects_employees(something);
//    }
    public ArrayList<Employee> employees_search(Employee filter) throws Exception {
        return employee_dao.find_by_name(filter.get_name());
    }

    public ArrayList<Employee> employees_search_id(Employee filter) throws Exception{
        return employee_dao.find_by_id(filter.get_id());
    }

//    public Employee get_employee(String id) throws Exception {
//        Employee result = data.all_objects_employees().stream().filter(e->e.get_id().equals(id)).findFirst().orElse(null);
//        if (result!=null) return result;
//        else throw new Exception("Employee does not exist");
//    }
    public Employee get_employee(String id) throws Exception {
        return employee_dao.read(id);
    }

    public ArrayList<Employee> get_employees() {
        try {
            return employees_search(new Employee());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

//    public void employee_add(Employee e) throws Exception {
//        if (this.employees_search(e).size() == 0) data.push(e);
//        else throw new Exception("Employee is already in the system");
//    }

    public void employee_add(Employee e) throws Exception {
        employee_dao.create(e);
    }

//    public void employee_delete(Employee employee) throws Exception {
//        Employee result = data.all_objects_employees().stream().filter(e->e.get_id().equals(employee.get_id())).findFirst().orElse(null);
//        if (result != null) data.erase(employee);
//        else throw new Exception("Employee does not exits");
//    }

    public void employee_delete(Employee employee) throws Exception {
        employee_dao.delete(employee);
    }

//    public void employee_update(Employee employee) throws Exception{
//        Employee result;
//        try {
//            result = this.get_employee(employee.get_id());
//            data.erase(result);
//            data.push(employee);
//        } catch (Exception e) {
//            throw new Exception("Employee does not exist");
//        }
//    }

    public void employee_update(Employee e) throws Exception {
        employee_dao.update(e);
    }

    public void store() {
        try {
            XmlPersister.instance().store(data);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

//    public <T> ArrayList<Branch_Office> branch_offices_search(T something) {
//        return data.certain_objects_branch_offices(something);
//    }
    public ArrayList<Branch_Office> branch_offices_search(Branch_Office filter) throws Exception {
        return branch_office_dao.find_by_reference(filter.get_reference());
    }

    public ArrayList<Branch_Office> get_branch_offices() {
        try {
            return branch_offices_search(new Branch_Office());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

//    public Branch_Office get_branch_office(String code) throws Exception {
//        Branch_Office result = data.all_objects_branch_offices().stream().filter(e->e.get_code().equals(code)).findFirst().orElse(null);
//        if (result!=null) return result;
//        else throw new Exception("Branch Office does not exist");
//    }
    public Branch_Office get_branch_office(String code) throws Exception {
        return branch_office_dao.read(code);
    }

    public Branch_Office get_branch_office_by_name(String name) throws Exception {
//        Branch_Office result = data.all_objects_branch_offices().stream().filter(e->e.get_reference().equals(name)).findFirst().orElse(null);
        ArrayList<Branch_Office> result = branch_office_dao.find_by_reference(name);
        if (result!=null) {
            return result.get(0);
        }
        else throw new Exception("Branch Office does not exist");
    }

//    public void branch_office_add(Branch_Office b) {
//        if (this.branch_offices_search(b).size() == 0) data.push(b);
//        else throw new IllegalArgumentException("Branch Office is already in the system");
//    }
    public void branch_office_add(Branch_Office b) throws Exception {
        branch_office_dao.create(b);
    }

//    public void branch_office_delete(Branch_Office branch_office) throws Exception {
//        Branch_Office result = data.all_objects_branch_offices().stream().filter(e->e.get_code().equals(branch_office.get_code())).findFirst().orElse(null);
//        if (result != null) data.erase(branch_office);
//        else throw new Exception("Branch Office does not exits");
    public void branch_office_delete(Branch_Office branch_office) throws Exception {
        branch_office_dao.delete(branch_office);
    }

//    public void branch_office_update(Branch_Office branch_office) throws Exception{
//        Branch_Office result;
//        try {
//            result = this.get_branch_office(branch_office.get_code());
//            data.erase(result);
//            data.push(branch_office);
//        } catch (Exception e) {
//            throw new Exception("Branch Office does not exist");
//        }
//    }
    public void branch_office_update(Branch_Office b) throws Exception {
        branch_office_dao.update(b);
    }


}
