// class based on Container made be @author XarthDaichi (me)

package project.data;

import java.util.ArrayList;

import static java.util.stream.Collectors.toCollection;

public class Data <T> {
    protected ArrayList<T> vec;

    public Data() {
        vec = new ArrayList<T>();
    }

    public Data(ArrayList<T> vec) {
        this.vec = vec;
    }

    public boolean empty() {
        return vec.isEmpty();
    }

    public void push(T element) {
        vec.add(element);
    }

    public void pop(T element) {
        if (vec.isEmpty()) throw new java.lang.IndexOutOfBoundsException("empty container");
        vec.remove(vec.size()-1);
    }

    public void top() {
        if (vec.isEmpty()) throw new java.lang.IndexOutOfBoundsException("empty container");
    }

    public void insert(int position, T element) {
        vec.add(position, element);
    }

    public void erase(T element) {
        if (vec.isEmpty()) throw new java.lang.IndexOutOfBoundsException("empty container");
    }

    public <V> ArrayList<T> certain_objects(V thing) {
        return vec.stream().filter(element->element.toString().contains(thing.toString())).collect(toCollection(ArrayList::new));
    }

    public ArrayList<T> all_objects() {
        return vec;
    }

    @Override
    public String toString() {
        return "Container{" +
                "vec=" + vec +
                '}';
    }
}
