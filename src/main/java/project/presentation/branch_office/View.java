package project.presentation.branch_office;

import project.Application;
import project.logic.Branch_Office;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

public class View implements Observer {
    private JPanel panel;
    private JPanel input_panel;
    private JPanel button_panel;
    private JLabel code_label;
    private JTextField code_text;
    private JLabel reference_label;
    private JTextField reference_text;
    private JLabel zonage_percentage_label;
    private JTextField zonage_percentage_text;
    private JButton save_button;
    private JButton cancel_button;
    private JPanel mapPanel;
    private JLabel mapLabel;

    public View() {
        save_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(validate()) {
                    Branch_Office n = take();
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
        Branch_Office current = model.get_current();
        this.code_text.setEnabled(model.get_mode() == Application.ADD_MODE);
        this.code_text.setText(current.get_code());
        reference_text.setText(current.get_reference());
        this.panel.validate();
    }

    public Branch_Office take() {
        Branch_Office e = new Branch_Office();
        e.set_code(code_text.getText());
        e.set_reference(reference_text.getText());
        e.set_zonage_percentage(Double.parseDouble(zonage_percentage_text.getText()));
        return e;
    }

    private boolean validate() {
        boolean valid = true;
        if (code_text.getText().isEmpty()) {
            valid = false;
            code_label.setBorder(Application.BORDER_ERROR);
            code_label.setToolTipText("Id required");
        } else {
            code_label.setBorder(null);
            code_label.setToolTipText(null);
        }

        if (reference_text.getText().length() == 0) {
            valid = false;
            reference_label.setBorder(Application.BORDER_ERROR);
            reference_label.setToolTipText("Name required");
        } else {
            reference_label.setBorder(null);
            reference_label.setToolTipText(null);
        }

        if (zonage_percentage_text.getText().isEmpty()) {
            valid = false;
            zonage_percentage_label.setBorder(Application.BORDER_ERROR);
            zonage_percentage_label.setToolTipText("Phone required");
        } else {
            zonage_percentage_label.setBorder(null);
            zonage_percentage_label.setToolTipText(null);
        }
        return valid;
    }
}
