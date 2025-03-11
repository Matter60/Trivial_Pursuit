package TrivialPursuit.view.make;

import TrivialPursuit.model.TrivialPursuitController;
import TrivialPursuit.view.create.CreateGameView;
import TrivialPursuit.view.create.CreateGamePresenter;
import TrivialPursuit.view.home.HomeView;
import TrivialPursuit.view.home.HomePresenter;
import TrivialPursuit.view.oefenen.OefenenView;
import TrivialPursuit.view.oefenen.OefenenPresenter;
import TrivialPursuit.view.game.GameView;
import TrivialPursuit.view.game.GamePresenter;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class MakePresenter {

    private TrivialPursuitController model;
    private MakeView view;

    public MakePresenter(
            TrivialPursuitController model, MakeView view) {
        this.model = model;
        this.view = view;
        this.addEventHandlers();
        this.updateView();
    }

    private void addEventHandlers() {
        view.getBackButton().setOnAction(event -> {
            HomeView homeView = new HomeView();
            HomePresenter homePresenter = new HomePresenter(model, homeView);
            homePresenter.addWindowEventHandlers();
            view.getScene().setRoot(homeView);
            homeView.getScene().getWindow().sizeToScene();
        });

        view.getLaadGameButton().setOnAction(event -> {
            model.loadGame();
            GameView gameView = new GameView();
            GamePresenter gamePresenter = new GamePresenter(model, gameView);
            gamePresenter.addWindowEventHandlers();
            view.getScene().setRoot(gameView);
            gameView.getScene().getWindow().sizeToScene();
        });

        view.getCreateGameButton().setOnAction(event -> {
            CreateGameView createGameView = new CreateGameView();
            CreateGamePresenter createGamePresenter = new CreateGamePresenter(model, createGameView);
            createGamePresenter.addWindowEventHandlers();
            view.getScene().setRoot(createGameView);
            createGameView.getScene().getWindow().sizeToScene();
        });

        view.getOefenGameButton().setOnAction(event -> {
            OefenenView oefenenView = new OefenenView();
            OefenenPresenter oefenenPresenter = new OefenenPresenter(model, oefenenView);
            oefenenPresenter.addWindowEventHandlers();
            view.getScene().setRoot(oefenenView);
            oefenenView.getScene().getWindow().sizeToScene();
        });
    }

    private void updateView() {
    }

    public void addWindowEventHandlers() {
    }
}
