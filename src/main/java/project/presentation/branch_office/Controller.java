package project.presentation.branch_office;

import project.Application;
import project.logic.Branch_Office;
import project.logic.Employee;
import project.logic.Service;

import java.util.ArrayList;

import static java.util.stream.Collectors.toCollection;

public class Controller {
    View view;
    Model model;

    public Controller(View view, Model model) {
        model.set_branch_offices(Service.instance().branch_offices_search(""));
        this.view = view;
        this.model = model;
        view.set_controller(this);
        view.set_model(model);
    }

    public <T> void search(T filter) {
        ArrayList<Branch_Office> rows = Service.instance().branch_offices_search(filter);
        model.set_branch_offices(rows);
        model.commit();
    }

    public void search_by_code(String code) {
        ArrayList<Branch_Office> rows = Service.instance().branch_offices_search(code);
        rows = rows.stream().filter(element->element.get_code().contains(code)).collect(toCollection(ArrayList::new));
        model.set_branch_offices(rows);
        model.commit();
    }

    public void search_by_reference(String reference) {
        ArrayList<Branch_Office> rows = Service.instance().branch_offices_search(reference);
        rows = rows.stream().filter(element->element.get_reference().contains(reference)).collect(toCollection(ArrayList::new));
        model.set_branch_offices(rows);
        model.commit();
    }

    public void search_by_zonage_percentage(String zonage_percentage) {
        ArrayList<Branch_Office> rows = Service.instance().branch_offices_search(zonage_percentage);
        rows = rows.stream().filter(element -> Double.toString(element.get_zonage_percentage()).contains(zonage_percentage)).collect(toCollection(ArrayList::new));
        model.set_branch_offices(rows);
        model.commit();
    }

    public void add(Branch_Office b) {
        Service.instance().branch_offices_add(b);
        this.update();
    }

    public void update() {
        ArrayList<Branch_Office> rows = Service.instance().get_branch_offices();
        model.set_branch_offices(rows);
        model.commit();
    }

    public void show() {
        Application.window.setContentPane(view.get_panel());
    }
}
