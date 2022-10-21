package project.presentation.employees;

import project.logic.Branch_Office;
import project.logic.Employee;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.Objects;
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
    private JTextField salary_field;
    private JLabel salary_label;
    private JLabel branch_office_label;
    private JTextField branch_office_field;
    private JLabel phone_label;
    private JTextField phone_field;
    private JButton erase_button;
    private JButton pdf_button;
    Controller controller;
    Model model;

    public JPanel getPanel() {
        return panel;
    }

    public View() {
        search_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Employee employee = new Employee();
                employee.set_id(id_field.getText());
                controller.search(employee);
            }
        });
        add_button.addActionListener(e -> controller.pre_add());
        results_field.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int row = results_field.getSelectedRow();
                    controller.edit(row);
                }
            }
        });
        erase_button.addActionListener(e -> {
            int row = results_field.getSelectedRow();
            controller.erase(row);
        });
        add_button.addActionListener(e -> controller.pre_add());
        pdf_button.addActionListener(e -> {
            try {
                controller.print();
                if (Desktop.isDesktopSupported()) {
                    File myFile = new File("employees.pdf");
                    Desktop.getDesktop().open(myFile);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(panel, "No se pudo imprimir el reporte","ERROR",JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    public void setController(Controller controller) { this.controller = controller; }
    public void setModel(Model model) {
        this.model = model;
        model.addObserver(this);
    }

    @Override
    public void update(Observable updatedModel, Object parametros) {
        int[] cols = {TableModel.ID, TableModel.NAME, TableModel.PHONE, TableModel.BASE_SALARY, TableModel.WORKPLACE, TableModel.ZONAGE, TableModel.TOTAL_SALARY};
        results_field.setModel(new TableModel(cols, model.get_employees()));
        results_field.setRowHeight(30);
        this.panel.revalidate();
    }

    private void createUIComponents() {
        ImageIcon imageIcon = new ImageIcon(new ImageIcon("src/main/resources/pdf.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        pdf_button.setIcon(imageIcon);
    }
}
