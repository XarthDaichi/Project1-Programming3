package project.presentation.employee;

import project.Application;
import project.logic.Branch_Office;
import project.logic.Employee;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

public class View implements Observer {
    private JPanel panel;
    private JLabel id_label;
    private JTextField id_text;
    private JTextField name_text;
    private JLabel name_label;
    private JLabel phone_label;
    private JLabel salary_label;
    private JTextField phone_text;
    private JTextField salary_text;
    private JButton save_button;
    private JButton cancel_button;

    public View() {
        save_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(validate()) {
                    Employee n = take();
                    try {
                        controller.save(n);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(panel, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        cancel_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.hide();
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
    public void update(Observable updated_model, Object parameters) {
        Employee current = model.get_current();
        this.id_text.setEnabled(model.get_mode() == Application.ADD_MODE);
        this.id_text.setText(current.get_id());
        name_text.setText(current.get_name());
        this.panel.validate();
    }

    public Employee take() {
        Employee e = new Employee();
        e.set_id(id_text.getText());
        e.set_name(name_text.getText());
        e.set_phone(phone_text.getText());
        e.set_base_salary(Double.parseDouble(salary_text.getText()));
        e.set_work_place(new Branch_Office());
        return e;
    }

    private boolean validate() {
        boolean valid = true;
        if (id_text.getText().isEmpty()) {
            valid = false;
            id_label.setBorder(Application.BORDER_ERROR);
            id_label.setToolTipText("Id required");
        } else {
            id_label.setBorder(null);
            id_label.setToolTipText(null);
        }

        if (name_text.getText().length() == 0) {
            valid = false;
            name_label.setBorder(Application.BORDER_ERROR);
            name_label.setToolTipText("Name required");
        } else {
            name_label.setBorder(null);
            name_label.setToolTipText(null);
        }

        if (phone_text.getText().isEmpty()) {
            valid = false;
            phone_label.setBorder(Application.BORDER_ERROR);
            phone_label.setToolTipText("Phone required");
        } else {
            phone_label.setBorder(null);
            phone_label.setToolTipText(null);
        }

        if (salary_text.getText().isEmpty()) {
            valid = false;
            salary_label.setBorder(Application.BORDER_ERROR);
            salary_label.setToolTipText("Salary required");
        } else {
            salary_label.setBorder(null);
            salary_label.setToolTipText(null);
        }
        return valid;
    }
}
