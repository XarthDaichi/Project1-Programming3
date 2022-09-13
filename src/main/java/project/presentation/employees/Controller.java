package project.presentation.employees;

import project.Application;
import project.logic.Employee;
import project.logic.Service;

import java.util.ArrayList;

import static java.util.stream.Collectors.toCollection;

public class Controller {
    View view;

    Model model;

    public Controller(View view, Model model) {
        model.set_employees(Service.instance().employees_search(""));
        this.view = view;
        this.model = model;
        view.set_controller(this);
        view.set_model(model);
    }

    public <T> void search(T filter) {
        ArrayList<Employee> rows = Service.instance().employees_search(filter);
        model.set_employees(rows);
        model.commit();
    }

    public void search_by_id(String id) {
        ArrayList<Employee> rows = Service.instance().employees_search(id);
        rows = rows.stream().filter(element->element.get_id().contains(id)).collect(toCollection(ArrayList::new));
        model.set_employees(rows);
        model.commit();
    }

    public void search_by_name(String name) {
        ArrayList<Employee> rows = Service.instance().employees_search(name);
        rows = rows.stream().filter(element->element.get_name().contains(name)).collect(toCollection(ArrayList::new));
        model.set_employees(rows);
        model.commit();
    }

    public void search_by_phone(String phone) {
        ArrayList<Employee> rows = Service.instance().employees_search(phone);
        rows = rows.stream().filter(element->element.get_phone().contains(phone)).collect(toCollection(ArrayList::new));
        model.set_employees(rows);
        model.commit();
    }

    public void search_by_salary(String salary) {
        ArrayList<Employee> rows = Service.instance().employees_search(salary);
        rows = rows.stream().filter(element -> Double.toString(element.get_base_salary()).contains(salary)).collect(toCollection(ArrayList::new));
        model.set_employees(rows);
        model.commit();
    }

    public void add(Employee e) {
        Service.instance().employees_add(e);
        this.update();
    }

    public void update() {
        ArrayList<Employee> rows = Service.instance().get_employees();
        model.set_employees(rows);
        model.commit();
    }

    public void show() {
        Application.window.setContentPane(view.get_panel());
    }
}
