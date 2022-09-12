package project.presentation.employees;

import project.Application;
import project.logic.Employee;
import project.logic.Service;

import java.util.ArrayList;

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

    public void add(Employee e) {
        Service.instance().employees_add(e);
        this.update();
    }

    public void update() {
        ArrayList<Employee> rows = Service.instance().employees_search("");
        model.set_employees(rows);
        model.commit();
    }

    public void show() {
        Application.window.setContentPane(view.get_panel());
    }
}
