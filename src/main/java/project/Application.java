package project;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Application {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.NimbusLookAndFeel");
        } catch (Exception ex) {}

        project.presentation.employees.Model employees_model = new project.presentation.employees.Model();
        project.presentation.employees.View employees_view = new project.presentation.employees.View();
        employees_controller = new project.presentation.employees.Controller(employees_view, employees_model);

        project.presentation.employee.Model employee_model = new project.presentation.employee.Model();
        project.presentation.employee.View employee_view = new project.presentation.employee.View();
        employee_controller = new project.presentation.employee.Controller(employee_view, employee_model);

        project.presentation.branch_offices.Model branch_offices_model = new project.presentation.branch_offices.Model();
        project.presentation.branch_offices.View branch_offices_view = new project.presentation.branch_offices.View();
        branch_offices_controller = new project.presentation.branch_offices.Controller(branch_offices_view, branch_offices_model);

        project.presentation.branch_office.Model branch_office_model = new project.presentation.branch_office.Model();
        project.presentation.branch_office.View branch_office_view = new project.presentation.branch_office.View();
        branch_office_controller = new project.presentation.branch_office.Controller(branch_office_view, branch_office_model);

        project.presentation.main.Model main_model = new project.presentation.main.Model();
        project.presentation.main.View main_view = new project.presentation.main.View();
        main_controller = new project.presentation.main.Controller(main_view, main_model);

        main_view.get_main_pane().add("Employees", employees_view.get_panel());
        main_view.get_main_pane().add("Branch Offices", branch_offices_view.get_panel());
        window = new JFrame();
        window.setSize(400, 300);
        window.setExtendedState(JFrame.MAXIMIZED_BOTH);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setTitle("System...");
        window.setVisible(true);
        main_controller.show();
        //System.out.println("Hello world!");
    }

    public static project.presentation.employees.Controller employees_controller;

    public static project.presentation.employee.Controller employee_controller;

    public static project.presentation.branch_offices.Controller branch_offices_controller;

    public static project.presentation.branch_office.Controller branch_office_controller;
    public static project.presentation.main.Controller main_controller;

    public static JFrame window;

    public static final int ADD_MODE=0;

    public static final int EDIT_MODE=1;

    public static Border BORDER_ERROR = BorderFactory.createMatteBorder(0, 0, 2, 0, Color.RED);
}