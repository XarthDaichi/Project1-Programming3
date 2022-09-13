package project.presentation.employees;

import project.logic.Branch_Office;
import project.logic.Employee;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

public class View implements Observer {

    private JPanel panel;
    private JTextField name_field;
    private JLabel name_label;
    private JButton search_button;
    private JTable results_field;
    private JButton add_button;
    private JPanel input_panel;
    private JPanel results_panel;
    private JLabel id_label;
    private JTextField id_field;
    private javax.swing.JScrollPane JScrollPane;
    private JPanel map_panel;
    private JTextField salary_field;
    private JLabel salary_label;
    private JLabel branch_office_label;
    private JTextField branch_office_field;
    private JLabel phone_label;
    private JTextField phone_field;

    public View() {
        search_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!id_field.getText().isEmpty() && !name_field.getText().isEmpty() && !phone_field.getText().isEmpty() && !salary_field.getText().isEmpty())
                    controller.search("Employee{" +
                            "id='" + id_field.getText() + '\'' +
                            ", name='" + name_field.getText() + '\'' +
                            ", phone='" + phone_field.getText() + '\'' +
                            ", base_salary=" + salary_field.getText());
                else if (!id_field.getText().isEmpty())
                    controller.search_by_id(id_field.getText());
                else if (!name_field.getText().isEmpty())
                    controller.search_by_name(name_field.getText());
                else if (!phone_field.getText().isEmpty())
                    controller.search_by_phone(phone_field.getText());
                else if (!salary_field.getText().isEmpty())
                    controller.search_by_salary(salary_field.getText());
                else if (!branch_office_field.getText().isEmpty())
                    controller.search(branch_office_field.getText());
                else
                    controller.update();
            }
        });
        add_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.add(new Employee(id_field.getText(), "test_name", "test_Phone", 10.0, new Branch_Office()));
            }
        });
    }

    public JPanel get_panel() {
        return panel;
    }

    Controller controller;

    Model model;

    public void set_controller(Controller controller) {
        this.controller = controller;
    }

    public void set_model(Model model) {
        this.model = model;
        model.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        int[] columns = {TableModel.ID, TableModel.NAME, TableModel.PHONE, TableModel.SALARY, TableModel.WORKPLACE};
        results_field.setModel(new TableModel(columns, model.get_employees()));
        results_field.setRowHeight(30);
        this.panel.revalidate();
    }
}
