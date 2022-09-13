package project.presentation.employees;

import project.logic.Employee;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class Model extends java.util.Observable {
    ArrayList<Employee> employees;

    public Model() {
        this.set_employees(new ArrayList<Employee>());
    }

    public void set_employees(ArrayList<Employee> employees) {
        this.employees = employees;
    }

    public ArrayList<Employee> get_employees() {
        return employees;
    }

    @Override
    public void addObserver(Observer o) {
        super.addObserver(o);
        commit();
    }

    public void commit() {
        setChanged();
        notifyObservers(null);
    }
}
