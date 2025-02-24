package TrivialPursuit.view.create;

import TrivialPursuit.model.TrivialPursuitController;
import TrivialPursuit.view.make.MakeView;
import TrivialPursuit.view.make.MakePresenter;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import TrivialPursuit.view.game.GameView;
import TrivialPursuit.view.game.GamePresenter;

public class CreateGamePresenter {
    private TrivialPursuitController model;
    private CreateGameView view;

    public CreateGamePresenter(
            TrivialPursuitController model,
            CreateGameView view) {
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

        view.getStartButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                GameView gameView = new GameView();
                GamePresenter gamePresenter = new GamePresenter(model, gameView);
                gamePresenter.addWindowEventHandlers();
                view.getScene().setRoot(gameView);
                gameView.getScene().getWindow().sizeToScene();
            }
        });
    }

    private void updateView() {
        // Update view with model data
    }

    public void addWindowEventHandlers() {

    }
}