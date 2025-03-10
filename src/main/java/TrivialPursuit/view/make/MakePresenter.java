package TrivialPursuit.view.make;

import TrivialPursuit.model.TrivialPursuitController;
import TrivialPursuit.view.create.CreateGameView;
import TrivialPursuit.view.create.CreateGamePresenter;
import TrivialPursuit.view.home.HomeView;
import TrivialPursuit.view.home.HomePresenter;
import TrivialPursuit.view.oefenen.OefenenView;
import TrivialPursuit.view.oefenen.OefenenPresenter;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class MakePresenter {

    private TrivialPursuitController model;
    private MakeView view;

    public MakePresenter(TrivialPursuitController model, MakeView view) {
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

        view.getCreateGameButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                CreateGameView createGameView = new CreateGameView();
                CreateGamePresenter createGamePresenter = new CreateGamePresenter(model, createGameView);
                createGamePresenter.addWindowEventHandlers();
                view.getScene().setRoot(createGameView);
                createGameView.getScene().getWindow().sizeToScene();
            }
        });

        view.getOefenGameButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                OefenenView oefenenView = new OefenenView();
                OefenenPresenter oefenenPresenter = new OefenenPresenter(model,oefenenView); // Corrected this line
                oefenenPresenter.addWindowEventHandlers();
                view.getScene().setRoot(oefenenView);
                oefenenView.getScene().getWindow().sizeToScene();
            }
        });
    }

    private void updateView() {
    }

    public void addWindowEventHandlers() {
    }
}
