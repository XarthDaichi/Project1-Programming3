package project.presentation.employee;

import project.Application;
import project.logic.Employee;
import project.logic.Service;

import javax.swing.*;
import java.awt.*;

public class Controller {
    View view;

    Model model;

    public Controller(View view, Model model) {
        model.set_current(new Employee());

        this.view = view;
        this.model = model;
        view.set_controller(this);
        view.set_model(model);
    }

    public void pre_add() {
        model.set_mode(Application.ADD_MODE);
        model.set_current(new Employee());
        model.commit();
        this.show();
    }

    JDialog dialog;

    public void show() {
        dialog  = new JDialog(Application.window, "Employee", true);
        dialog.setSize(500, 400);
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

    public void save(Employee e) throws Exception {
        switch (model.get_mode()) {
            case Application.ADD_MODE:
                Service.instance().employee_add(e);
                model.set_current(new Employee());
                break;
            case Application.EDIT_MODE:
                Service.instance().employee_update(e);
                model.set_current(e);
                break;
        }
        Application.employees_controller.update();
        model.commit();
    }

    public void edit(Employee e) {
        model.set_mode(Application.EDIT_MODE);
        model.set_current(e);
        model.commit();
        this.show();
    }
}
