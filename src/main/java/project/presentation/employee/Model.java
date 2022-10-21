package project.presentation.employee;

import project.logic.Branch_Office;
import project.logic.Employee;
import java.util.ArrayList;
import java.util.List;

public class Model extends java.util.Observable{
    Employee current;
    int mode;
    ArrayList<Branch_Office> branch_offices;

    public Model() {
        this.setBranch_offices(new ArrayList<>());
    }

    public ArrayList<Branch_Office> getBranch_offices() {
        return branch_offices;
    }

    public void setBranch_offices(ArrayList<Branch_Office> branch_offices) {
        this.branch_offices = branch_offices;
    }

    public int get_mode() {
        return mode;
    }

    public void set_mode(int mode) {
        this.mode = mode;
    }

    public Employee get_current() {
        return current;
    }

    public void set_current(Employee current) {
        this.current = current;
    }

    @Override
    public void addObserver(java.util.Observer o) {
        super.addObserver(o);
        this.commit();
    }

    public void commit() {
        setChanged();
        notifyObservers(null);
    }
}
