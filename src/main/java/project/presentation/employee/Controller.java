package project.presentation.employee;

import project.Application;
import project.logic.Branch_Office;
import project.logic.Employee;
import project.logic.Service;
import project.presentation.branch_offices.*;

import java.util.List;
import javax.swing.*;
import java.awt.*;

public class Controller {
    View view;

    Model model;
    project.presentation.branch_offices.Model branchOfficesModel;

    public Controller(View view, Model model, project.presentation.branch_offices.Model branchOfficesModel) {
        model.set_current(new Employee());

        this.view = view;
        this.model = model;
        this.branchOfficesModel = branchOfficesModel;
        view.setController(this);
        view.setModel(model);
        this.model.setBranch_offices(branchOfficesModel.get_branch_offices());
    }

    public void pre_add() {
        model.set_mode(Application.ADD_MODE);
        model.set_current(new Employee());
        this.model.setBranch_offices(branchOfficesModel.get_branch_offices());
        model.commit();
        this.show();
    }

    JDialog dialog;

    public void show() {
        dialog  = new JDialog(Application.window, "Employee", true);
        dialog.setSize(600, 400);
        dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        dialog.setContentPane(view.getPanel());
        Point location = Application.window.getLocation();
        dialog.setLocation(location.x+400, location.y+100);
        dialog.setVisible(true);
    }

    public void hide() {
        dialog.dispose();
    }

    public void show1() {
        Application.window.setContentPane(view.getPanel());
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
        this.model.setBranch_offices(branchOfficesModel.get_branch_offices());
        model.commit();
        this.show();
    }
}
