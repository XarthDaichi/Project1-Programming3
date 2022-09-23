// class based on Container made be @author XarthDaichi (me)

package project.data;

import project.logic.Branch_Office;
import project.logic.Employee;

import java.util.ArrayList;

import static java.util.stream.Collectors.toCollection;

public class Data {
    protected ArrayList<Employee> vec_employees;
    protected ArrayList<Branch_Office> vec_branch_offices;

    public Data() {
        vec_employees = new ArrayList<Employee>();
        vec_branch_offices = new ArrayList<Branch_Office>();
    }

    public Data(ArrayList<Employee> vec_e, ArrayList<Branch_Office> vec_b) {
        this.vec_employees = vec_e;
        this.vec_branch_offices = vec_b;
    }

    public boolean empty_employees() {
        return vec_employees.isEmpty();
    }

    public boolean empty_branch_offices() {
        return vec_branch_offices.isEmpty();
    }

    public <T> void push(T element) {
        if ((Employee) element != null) vec_employees.add((Employee) element);
        else if ((Branch_Office) element != null) vec_branch_offices.add((Branch_Office) element);
    }

    public <T> void pop(T element) {
        if ((Employee) element != null) {
            if (vec_employees.isEmpty()) throw new java.lang.IndexOutOfBoundsException("empty container");
            vec_employees.remove(vec_employees.size()-1);

        }
        else if ((Branch_Office) element != null) {
            if (vec_branch_offices.isEmpty()) throw new java.lang.IndexOutOfBoundsException("empty container");
            vec_branch_offices.remove(vec_branch_offices.size()-1);

        }

    }

    public <T> void top(T element) {
        if ((Employee) element != null) {
            if (vec_employees.isEmpty()) {
                throw new java.lang.IndexOutOfBoundsException("empty container");
            }
        }
        else if ((Branch_Office) element != null) {
            if (vec_branch_offices.isEmpty()) {
                throw new java.lang.IndexOutOfBoundsException("empty container");
            }
        }

    }

    public <T> void insert(int position, T element) {
        if ((Employee) element != null) vec_employees.add(position, (Employee) element);
        else if ((Branch_Office) element != null) vec_branch_offices.add(position, (Branch_Office) element);
    }

    public <T> void erase(T element) {
        if ((Employee) element != null) {
            if (vec_employees.isEmpty()) throw new java.lang.IndexOutOfBoundsException("empty container");

        }
        else if ((Branch_Office) element != null) {
            if (vec_branch_offices.isEmpty()) throw new java.lang.IndexOutOfBoundsException("empty container");
        }
    }

    public <V> ArrayList<Employee> certain_objects_employees(V thing) {
        return vec_employees.stream().filter(element->element.toString().contains(thing.toString())).collect(toCollection(ArrayList::new));
    }

    public <V> ArrayList<Branch_Office> certain_objects_branch_offices(V thing) {
        return vec_branch_offices.stream().filter(element->element.toString().contains(thing.toString())).collect(toCollection(ArrayList::new));
    }

    public ArrayList<Employee> all_objects_employees() {
        return vec_employees;
    }

    public ArrayList<Branch_Office> all_objects_branch_offices() {
        return vec_branch_offices;
    }

    @Override
    public String toString() {
        return "Container{" +
                "employees=" + vec_employees +
                "branch offices=" + vec_branch_offices +
                '}';
    }
}
