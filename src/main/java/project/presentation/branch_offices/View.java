package project.presentation.branch_offices;

import project.logic.Branch_Office;
import project.logic.Direction;
import javax.imageio.ImageIO;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
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
    private JLabel direction_label;
    private JTextField direction_field;
    private JTable results_field;
    private JButton add_button;
    private JPanel panel;
    private JButton erase_button;
    private JButton pdf_button;
    private JPanel map_panel;
    private JLabel mapPanel;
    private JPanel buttons_panel;

    Image map;
    Image branch_office;
    Image branch_office_selected;

    public View() {
        try {
            mapPanel.setSize(700,700);
            map = ImageIO.read(getClass().getResourceAsStream("../../../mapa.png"));
            map = map.getScaledInstance(mapPanel.getWidth(), mapPanel.getHeight(), Image.SCALE_SMOOTH);
            branch_office = ImageIO.read(getClass().getResourceAsStream("../../../Sucursal.png"));
            branch_office_selected = ImageIO.read(getClass().getResourceAsStream("../../../SucursalSel.png"));
            mapPanel.setIcon(new ImageIcon(map));
        } catch(Exception ex) {
            System.err.println(ex);
        }
        ToolTipManager.sharedInstance().setInitialDelay(2);
        mapPanel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    if(e.getX() > 110 && e.getX() < 400 && e.getY() > 125 && e.getY() < 390) {
                        BufferedImage myPicture = ImageIO.read(Objects.requireNonNull(getClass().getResource("/Sucursal.png")));
                        JLabel icon = new JLabel(new ImageIcon(myPicture));
                        mapPanel.add(icon);
                        icon.setLocation(e.getX() - 17,e.getY() - 32);
                        icon.setSize(34,34);
                        icon.setVisible(true);
                    }
                    System.out.println(e.getX() + ", " + e.getY());
                } catch(Exception ex) {
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
                controller.erase(row);
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
