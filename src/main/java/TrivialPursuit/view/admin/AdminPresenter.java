package TrivialPursuit.view.admin;

import TrivialPursuit.model.TrivialPursuitController;


public class AdminPresenter {
    private TrivialPursuitController model;
    private AdminView view;


    public AdminPresenter(TrivialPursuitController model, AdminView view) {
        this.model = model;
        this.view = view;
        this.addEventHandlers();
    }


    private void addEventHandlers() {

    }


}
