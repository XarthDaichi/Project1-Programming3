package project.presentation.main;

import java.util.Observable;
import java.util.Observer;

public class Model extends Observable {

    @Override
    public synchronized void addObserver(Observer o) {
        super.addObserver(o);
        commit();
    }

    public Model() {

    }

    public void commit() {
        setChanged();
        notifyObservers(null);
    }
}
