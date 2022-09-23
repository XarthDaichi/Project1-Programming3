package project.presentation.main;

import project.Application;
import project.logic.Service;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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
        Application.window.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                Service.instance().store();
            }
        });
    }
}
