package project.presentation.branch_offices;

import project.logic.Branch_Office;

import javax.imageio.ImageIO;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.*;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.Objects;
import java.util.Observable;
import java.util.Observer;

public class View implements Observer {
    Vector<JLabel> locations;
    private JPanel panel;
    private JPanel input_panel;
    private JLabel code_label;
    private JTextField code_field;
    private JButton search_button;
    private JTextField reference_field;
    private JLabel reference_label;
    private JTextField zonage_percentage_field;
    private JLabel zonage_percentage_label;
    private JLabel direction_label;
    private JTextField direction_field;
    private JTable results_field;

    private JPanel buttons_panel;
    private JButton add_button;
    private JButton erase_button;
    private JButton pdf_button;
    private JPanel map_panel;
    private JLabel mapPanel;

    Image map;
    Image branch_office;
    Image branch_office_selected;
    private String temp_code;
    Controller controller;
    Model model;

    public View(Model model) {
        set_model(model);
        locations = new Vector<>();
        try {
            mapPanel.setSize(600,600);
            map = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/mapa.png")));
            map = map.getScaledInstance(mapPanel.getWidth(), mapPanel.getHeight(), Image.SCALE_SMOOTH);
            branch_office = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Sucursal.png")));
            branch_office_selected = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/SucursalSel.png")));
            mapPanel.setIcon(new ImageIcon(map));
            draw_all();
        } catch(Exception ex) {
            System.err.println(ex.getMessage());
        }

        results_field.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = results_field.getSelectedRow();
                if (e.getClickCount() == 1) {
                    temp_code = model.get_branch_offices().get(row).get_code();
                    map_update();
                }
                else if (e.getClickCount() >= 2) {
                    controller.edit(row);
                }
            }
        });

        search_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!code_field.getText().isEmpty() && !reference_field.getText().isEmpty() && !zonage_percentage_field.getText().isEmpty()) {
                    Branch_Office b = new Branch_Office();
                    b.set_code(code_field.getText());
                    b.set_reference(reference_field.getText());
                    b.set_zonage_percentage(Double.parseDouble(zonage_percentage_field.getText()));
                    controller.search(b);
                }
                else if (!code_field.getText().isEmpty())
                    controller.search_by_code(code_field.getText());
                else if (!reference_field.getText().isEmpty())
                    controller.search_by_reference(reference_field.getText());
//                else if (!zonage_percentage_field.getText().isEmpty())
//                    controller.search_by_zonage_percentage(zonage_percentage_field.getText());
//                else if (!direction_field.getText().isEmpty())
//                    controller.search(direction_field.getText());
                else
                    controller.update();
            }
        });

        add_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.pre_add();
            }
        });
        results_field.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int row = results_field.getSelectedRow();
                    controller.edit(row);
                }
            }
        });
        erase_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = results_field.getSelectedRow();
//                controller.erase(row);
            }
        });
        pdf_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    controller.print();
                    if (Desktop.isDesktopSupported()) {
                        File myFile = new File("branch_offices.pdf");
                        Desktop.getDesktop().open(myFile);
                    }
                } catch (Exception ex) {}
            }
        });
    }

    public JPanel get_panel() {
        return panel;
    }



    public void set_controller(Controller controller) {
        this.controller = controller;
    }

    public void set_model(Model model) {
        this.model = model;
        model.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        map_update();
        int[] columns = {TableModel.CODE, TableModel.REFERENCE, TableModel.ZONAGEPERCENTAGE};
        results_field.setModel(new TableModel(columns, model.get_branch_offices()));
        results_field.setRowHeight(30);
        draw_all();
        this.panel.revalidate();
    }

    public void draw_all() {
        for (int j = 0; j < model.get_branch_offices().size(); j++) {
            JLabel temp = new JLabel();
            Branch_Office b = model.get_branch_offices().get(j);
            temp.setSize(30, 30);
            temp.setLocation((b.getX() )*2, (b.getY() - 28)*2);
            temp.setToolTipText("Code: " + b.get_code() + ", Reference: " + b.get_reference());
            if(Objects.equals(temp_code, b.get_code()))
                temp.setIcon(new ImageIcon(branch_office_selected));
            else
                temp.setIcon(new ImageIcon(branch_office));
            temp.setVisible(true);
            mapPanel.add(temp);
            locations.add(temp);
            temp.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    try {
                        for (JLabel location : locations) {
                            location.setIcon(new ImageIcon(branch_office));
                        }
                        temp.setIcon(new ImageIcon(branch_office_selected));
                    }catch(Exception exc) {
                        System.out.println("Error");
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
        }
    }
    public void map_update() {
        mapPanel.removeAll();
        draw_all();
        panel.updateUI();
    }
}
