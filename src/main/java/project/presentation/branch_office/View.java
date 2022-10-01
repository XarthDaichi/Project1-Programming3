package project.presentation.branch_office;

import project.Application;
import project.logic.Branch_Office;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.Objects;
import java.util.Observable;
import java.util.Observer;

public class View implements Observer {
    private JPanel panel;
    private JLabel code_label;
    private JTextField code_text;
    private JLabel reference_label;
    private JTextField reference_text;
    private JLabel zonage_percentage_label;
    private JTextField zonage_percentage_text;
    private JButton save_button;
    private JButton cancel_button;
    private JPanel input_panel;
    private JPanel button_panel;
    private JPanel mapPanel;
    private JLabel mapLabel;
    Image map;
    Image branch_office;
    Image branch_office_selected;
    JLabel locate;

    public View() {
        locate = null;
        try {
            mapLabel.setSize(300,300);
            map = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/mapa.png")));
            map = map.getScaledInstance(mapLabel.getWidth(), mapLabel.getHeight(), Image.SCALE_SMOOTH);
            branch_office = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("../../../Sucursal.png")));
            branch_office = branch_office.getScaledInstance(18,18,Image.SCALE_SMOOTH);
            branch_office_selected = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("../../../SucursalSel.png")));
            branch_office_selected = branch_office_selected.getScaledInstance(18,18,Image.SCALE_SMOOTH);

            mapLabel.setIcon(new ImageIcon(map));
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        mapLabel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    //BufferedImage myPicture = ImageIO.read(Objects.requireNonNull(getClass().getResource("/Sucursal.png")));
                    if (locate == null) {
                        locate = new JLabel(new ImageIcon(branch_office_selected));
                        mapLabel.add(locate);
                    }
                    locate.setLocation(e.getX() - 9, e.getY() - 16);
                    locate.setSize(18,18);
                } catch(Exception ex) {
                    System.out.println(ex.getMessage());
                }

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
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
        e.setX(locate.getX());
        e.setY(locate.getY());
        locate = new JLabel(new ImageIcon(branch_office_selected));
        mapLabel.add(locate);
        locate.setLocation(e.getX(), e.getY());
        locate.setSize(18,18);
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
