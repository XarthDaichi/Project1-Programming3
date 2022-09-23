package project.presentation.employee;

import project.logic.Employee;

public class Model extends java.util.Observable{
    Employee current;
    int mode;

    public Model() {

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
