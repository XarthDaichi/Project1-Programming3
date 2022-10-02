package project.presentation.employee;

import project.Application;
import project.logic.Branch_Office;
import project.logic.Employee;
import project.logic.Service;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.Objects;
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
    private JPanel map_field;
    private JLabel map_label;
    private JTextField sucursal_field;
    private JLabel SucursalLabel;
    Controller controller;
    Model model;

    Image map;
    Image branch_office;
    Image branch_office_selected;

    public View(Model model) {
        this.model = model;
        try {
            map_label.setSize(300,300);
            map = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/mapa.png")));
            map = map.getScaledInstance(map_label.getWidth(), map_label.getHeight(), Image.SCALE_SMOOTH);
            branch_office = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("../../../Sucursal.png")));
            branch_office = branch_office.getScaledInstance(18,18,Image.SCALE_SMOOTH);
            branch_office_selected = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("../../../SucursalSel.png")));
            branch_office_selected = branch_office_selected.getScaledInstance(18,18,Image.SCALE_SMOOTH);
            map_label.setIcon(new ImageIcon(map));
            actualizarMapa();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        save_button.addActionListener(e -> {
            clean();
            try {
                if (validate()) {
                    try {
                        controller.save(take());
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(panel, "El codigo debe ser unico","ERROR",JOptionPane.ERROR_MESSAGE);
                    }
                }
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        cancel_button.addActionListener(e -> controller.hide());
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void setModel(Model model) {
        this.model = model;
        model.addObserver(this);
    }

    @Override
    public void update(Observable updatedModel, Object parametros) {

        Employee current = model.get_current();
        this.id_text.setEnabled(model.get_mode() == Application.ADD_MODE);
        this.id_text.setText(current.get_id());
        name_text.setText(current.get_name());
        phone_text.setText(current.get_phone());

        if(String.valueOf(current.get_base_salary()).equals("0.0")){ salary_text.setText(""); }
        else { salary_text.setText(String.valueOf(current.get_base_salary())); }

        if(current.get_work_place() != null){ sucursal_field.setText(current.get_work_place().get_reference()); }
        else { sucursal_field.setText(""); }

        actualizarMapa();

        this.panel.validate();
    }

    public Employee take() {
        Employee e = new Employee();
        e.set_id(id_text.getText());
        e.set_name(name_text.getText());
        e.set_phone(phone_text.getText());
        e.set_base_salary(Double.parseDouble(salary_text.getText()));
        e.setTotal_salary(e.get_base_salary() * (1 + e.get_work_place().get_zonage_percentage() / 100));
        for (int i = 0; i < model.getBranch_offices().size() ; i++) {
            if(model.getBranch_offices().get(i).get_reference().equals(sucursal_field.getText()))
                e.set_work_place(model.getBranch_offices().get(i));
        }
        return e;
    }

    public void clean() {
        id_text.setBorder(null);
        name_text.setBorder(null);
        phone_text.setBorder(null);
        salary_text.setBorder(null);
        SucursalLabel.setBorder(null);
        map_label.setBorder(null);
    }

    private boolean validate() throws Exception {
        boolean valid = true;
        String mensajeError = "";
        int concatenaciones = 0;

        if (id_text.getText().isEmpty()) {
            valid = false;
            id_label.setBorder(Application.BORDER_ERROR);
            mensajeError += "Cedula requerida. "; concatenaciones++;
        } else if(!id_text.getText().matches("[0-9]+")){
            valid = false;
            id_label.setBorder(Application.BORDER_ERROR);
            mensajeError += "Cedula debe ser numerico. ";
        }
        if (name_text.getText().length() == 0) {
            valid = false;
            name_label.setBorder(Application.BORDER_ERROR);
            mensajeError += "Nombre requerido. "; concatenaciones++;
        } else if(!name_text.getText().matches("^[a-z\\sA-Z]+$")){
            valid = false;
            name_label.setBorder(Application.BORDER_ERROR);
            mensajeError += "El nombre no puede ser numerico. ";
        }
        if (phone_text.getText().length() == 0) {
            valid = false;
            phone_label.setBorder(Application.BORDER_ERROR);
            mensajeError += "Telefono requerido. "; concatenaciones++;
        } else if(!phone_text.getText().matches("[0-9]+")) {
            valid = false;
            phone_label.setBorder(Application.BORDER_ERROR);
            mensajeError += "El telefono debe llenarse con numeros enteros. ";
        } else if(phone_text.getText().length() != 8) {
            valid = false;
            phone_label.setBorder(Application.BORDER_ERROR);
            mensajeError += "El telefono debe tener 8 digitos. ";
        }
        if (salary_text.getText().length() == 0) {
            valid = false;
            salary_label.setBorder(Application.BORDER_ERROR);
            mensajeError += "Salario requerido. "; concatenaciones++;
        } else if(!salary_text.getText().matches("^[0-9]+\\.?[0-9]*$")) {
            valid = false;
            salary_label.setBorder(Application.BORDER_ERROR);
            mensajeError += "El salario debe ser numerico. ";
        }
        if (sucursal_field.getText().length() == 0) {
            valid = false;
            SucursalLabel.setBorder(Application.BORDER_ERROR);
            mensajeError += "Sucursal requerida. "; concatenaciones++;
        } else if(Service.instance().get_branch_office_by_name(sucursal_field.getText()) == null) {
            valid = false;
            SucursalLabel.setBorder(Application.BORDER_ERROR);
            mensajeError += "La sucursal no existe. ";
        }
        if(concatenaciones == 5){
            JOptionPane.showMessageDialog(panel, "Todos los campos son requeridos","ERROR",JOptionPane.ERROR_MESSAGE);
        } else if(!mensajeError.equals("")){
            JOptionPane.showMessageDialog(panel, mensajeError,"ERROR",JOptionPane.ERROR_MESSAGE);
        }
        return valid;

    }

    public void actualizarMapa(){
        map_label.removeAll();
        llenarMapa();
        panel.updateUI();
    }

    private void llenarMapa() {
        for (int j = 0; j < model.getBranch_offices().size(); j++) {
            JLabel temp = new JLabel();
            Branch_Office s = model.getBranch_offices().get(j);
            temp.setSize(30, 30);
            temp.setLocation(s.getX() - 15, s.getY() - 31);
            temp.setToolTipText("Code: " + s.get_code() + ", Reference: " + s.get_reference());
            temp.setIcon(new ImageIcon(branch_office));
            if(sucursal_field.getText().equals(s.get_reference())){
                temp.setIcon(new ImageIcon(branch_office_selected));
            }
            temp.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    temp.setIcon(new ImageIcon(branch_office_selected));
                    sucursal_field.setText(s.get_reference());
                    sucursal_field.setForeground(Color.RED);
                    actualizarMapa();
                }
            });
            temp.setVisible(true);
            map_label.add(temp);
        }
    }
}

