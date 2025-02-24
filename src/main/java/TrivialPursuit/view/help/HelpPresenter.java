package TrivialPursuit.view.help;

import TrivialPursuit.model.TrivialPursuitController;
import TrivialPursuit.view.home.HomePresenter;
import TrivialPursuit.view.home.HomeView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class HelpPresenter {
    private TrivialPursuitController model;
    private HelpView view;

    public HelpPresenter(TrivialPursuitController model, HelpView view) {
        this.model = model;
        this.view = view;
        this.addEventHandlers();
        this.updateView();
    }

    private void addEventHandlers() {
        view.getBackButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                HomeView homeView = new HomeView();
                HomePresenter homePresenter = new HomePresenter(model, homeView);
                homePresenter.addWindowEventHandlers();
                view.getScene().setRoot(homeView);
                homeView.getScene().getWindow().sizeToScene();
            }
        });
    }

    private void updateView() {
        // Populate the view with data from the model if needed
    }

    public void addWindowEventHandlers() {
        // Add additional window event handlers if necessary
    }
}
