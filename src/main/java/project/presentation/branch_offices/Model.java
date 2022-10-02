package project.presentation.branch_offices;

import project.logic.Branch_Office;

import java.util.ArrayList;
import java.util.Observer;

public class Model extends java.util.Observable {
    ArrayList<Branch_Office> branch_offices;

    public Model() {
        this.set_branch_offices(new ArrayList<Branch_Office>());
    }

    public void set_branch_offices(ArrayList<Branch_Office> branch_offices) {
        this.branch_offices = branch_offices;
    }

    public ArrayList<Branch_Office> get_branch_offices() {
        return branch_offices;
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
