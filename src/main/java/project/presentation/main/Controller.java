package project.presentation.main;

import project.Application;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener {
    Model model;
    View view;

    public Controller(View view, Model model) {
        this.model = model;
        this.view = view;
        view.set_model(model);
        view.set_controller(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        model.commit();
    }

    public void show() {
        Application.window.setContentPane(view.get_main_pane());
    }
}
