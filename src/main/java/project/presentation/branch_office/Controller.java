package project.presentation.branch_office;

import project.Application;
import project.logic.Branch_Office;
import project.logic.Service;

import java.util.ArrayList;

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

    public void add(Branch_Office b) {
        Service.instance().branch_offices_add(b);
        this.update();
    }

    public void update() {
        ArrayList<Branch_Office> rows = Service.instance().branch_offices_search("");
        model.set_branch_offices(rows);
        model.commit();
    }

    public void show() {
        Application.window.setContentPane(view.get_panel());
    }
}
