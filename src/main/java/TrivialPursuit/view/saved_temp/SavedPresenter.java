package TrivialPursuit.view.saved_temp;

import TrivialPursuit.model.TrivialPursuitController;
import TrivialPursuit.view.make.MakeView;
import TrivialPursuit.view.make.MakePresenter;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class SavedPresenter {

    private TrivialPursuitController model;
    private SavedView view;

    public SavedPresenter(
            TrivialPursuitController model, SavedView view) {
        this.model = model;
        this.view = view;
        this.addEventHandlers();
        this.updateView();
    }

    private void addEventHandlers() {
        view.getBackButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                MakeView makeView = new MakeView();
                MakePresenter makePresenter = new MakePresenter(model, makeView);
                makePresenter.addWindowEventHandlers();
                view.getScene().setRoot(makeView);
                makeView.getScene().getWindow().sizeToScene();
            }
        });
    }

    private void updateView() {
    }

    public void addWindowEventHandlers() {
    }
}
