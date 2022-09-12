package project;

import javax.swing.*;

public class Application {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.NimbusLookAndFeel");
        } catch (Exception ex) {}

        project.presentation.employees.Model employees_model = new project.presentation.employees.Model();
        project.presentation.employees.View employees_view = new project.presentation.employees.View();
        employees_controller = new project.presentation.employees.Controller(employees_view, employees_model);

        project.presentation.branch_office.Model branch_offices_model = new project.presentation.branch_office.Model();
        project.presentation.branch_office.View branch_offices_view = new project.presentation.branch_office.View();
        branch_offices_controller = new project.presentation.branch_office.Controller(branch_offices_view, branch_offices_model);

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
        System.out.println("Hello world!");
    }

    public static project.presentation.employees.Controller employees_controller;

    public static project.presentation.branch_office.Controller branch_offices_controller;
    public static project.presentation.main.Controller main_controller;

    public static JFrame window;
}