package project.presentation.branch_office;

import project.Application;
import project.logic.Branch_Office;
import project.logic.Employee;
import project.logic.Service;

import javax.swing.*;
import java.awt.*;

public class Controller {
    View view;

    Model model;

    public Model getModel() {return model; }
    public Controller(View view, Model model) {
        model.set_current(new Branch_Office());

        view.set_controller(this);
        view.set_model(model);
        this.view = view;
        this.model = model;
    }

    public void pre_add() {
        model.set_mode(Application.ADD_MODE);
        model.set_current(new Branch_Office());
        model.commit();
        this.show();
    }

    JDialog dialog;

    public void show() {
        dialog  = new JDialog(Application.window, "Branch Office", true);
        dialog.setSize(600, 400);
        dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        dialog.setContentPane(view.get_panel());
        Point location = Application.window.getLocation();
        dialog.setLocation(location.x+400, location.y+100);
        dialog.setVisible(true);
    }

    public void hide() {
        dialog.dispose();
    }

    public void show1() {
        Application.window.setContentPane(view.get_panel());
        Application.window.revalidate();
    }

    public void hide1() {
        Application.main_controller.show();
    }

    public void save(Branch_Office b) throws Exception {
        switch (model.get_mode()) {
            case Application.ADD_MODE:
                Service.instance().branch_office_add(b);
                model.set_current(new Branch_Office());
                break;
            case Application.EDIT_MODE:
                Service.instance().branch_office_update(b);
                model.set_current(b);
                break;
        }
        Application.branch_offices_controller.update();
        model.commit();
    }

    public void edit(Branch_Office b) {
        model.set_mode(Application.EDIT_MODE);
        model.set_current(b);
        model.commit();
        this.show();
    }
}
