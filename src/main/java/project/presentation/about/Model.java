package project.presentation.about;

import java.util.Observer;

public class Model extends java.util.Observable {
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
