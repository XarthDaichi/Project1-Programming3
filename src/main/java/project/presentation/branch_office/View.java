package project.presentation.branch_office;

import project.logic.Branch_Office;
import project.logic.Direction;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

public class View implements Observer {
    private JPanel input_panel;
    private JPanel results_panel;
    private JLabel code_label;
    private JTextField code_field;
    private JButton search_button;
    private JTextField reference_field;
    private JLabel reference_label;
    private JTextField zonage_percentage_field;
    private JLabel zonage_percentage_label;
    private JPanel map_panel;
    private JLabel direction_label;
    private JTextField direction_field;
    private JTable results_field;
    private JButton add_button;
    private JPanel panel;

    public View() {
        search_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!code_field.getText().isEmpty() && !reference_field.getText().isEmpty() && !zonage_percentage_field.getText().isEmpty())
                    controller.search("Branch_Office{" +
                            "code='" + code_field.getText() + '\'' +
                            ", reference='" + reference_field.getText() + '\'' +
                            ", zonage_percentage=" + zonage_percentage_field.getText());
                else if (!code_field.getText().isEmpty())
                    controller.search_by_code(code_field.getText());
                else if (!reference_field.getText().isEmpty())
                    controller.search_by_reference(reference_field.getText());
                else if (!zonage_percentage_field.getText().isEmpty())
                    controller.search_by_zonage_percentage(zonage_percentage_field.getText());
                else if (!direction_field.getText().isEmpty())
                    controller.search(direction_field.getText());
                else
                    controller.update();
            }
        });
        add_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.add(new Branch_Office(code_field.getText(), "test_reference", 2.0, new Direction()));
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
        int[] columns = {TableModel.CODE, TableModel.REFERENCE, TableModel.ZONAGEPERCENTAGE, TableModel.DIRECTION};
        results_field.setModel(new TableModel(columns, model.get_branch_offices()));
        results_field.setRowHeight(30);
        this.panel.revalidate();
    }
}
