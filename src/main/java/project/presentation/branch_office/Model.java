package project.presentation.branch_office;

import project.logic.Branch_Office;

public class Model extends java.util.Observable{
    Branch_Office current;
    int mode;

    public Model() {

    }

    public int get_mode() {
        return mode;
    }

    public void set_mode(int mode) {
        this.mode = mode;
    }

    public Branch_Office get_current() {
        return current;
    }

    public void set_current(Branch_Office current) {
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