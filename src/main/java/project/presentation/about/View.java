package project.presentation.about;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.util.Objects;
import java.util.Observable;
import java.util.Observer;

public class View implements Observer {
    private JLabel label;
    private JLabel icon;
    private JPanel panel;
    Image logo;

    Controller controller;
    Model model;

    public View() {
        try {
            icon = new JLabel();
            logo = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/logo.jpg")));
            logo = logo.getScaledInstance(800, 400, Image.SCALE_DEFAULT);
            icon.setIcon(new ImageIcon(logo));
        } catch( Exception ex) {}

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
    public void update(Observable updateModel, Object parameters) {
        this.panel.revalidate();
    }
}
