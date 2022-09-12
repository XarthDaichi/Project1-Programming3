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

    public View() {
        search_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.search(id_field.getText());
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
