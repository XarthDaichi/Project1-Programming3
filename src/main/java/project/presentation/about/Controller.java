package project.presentation.about;

public class Controller {
    View view;
    Model model;

    public Controller(View view, Model model) {
        this.view = view;
        this.model = model;
        view.set_controller(this);
        view.set_model(model);
    }
}
