package TrivialPursuit.view.game;

import TrivialPursuit.model.TrivialPursuitController;
import TrivialPursuit.view.create.CreateGameView;
import TrivialPursuit.view.create.CreateGamePresenter;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class GamePresenter {
    private TrivialPursuitController model;
    private GameView view;

    public GamePresenter(
            TrivialPursuitController model,
            GameView view) {
        this.model = model;
        this.view = view;
        this.addEventHandlers();
        this.updateView();
    }

    private void addEventHandlers() {
        view.getRollDiceButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Handle dice roll
                // Update player position
                // Check for question space
                // etc.
            }
        });

        view.getBackButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                CreateGameView createGameView = new CreateGameView();
                CreateGamePresenter createGamePresenter = new CreateGamePresenter(model, createGameView);
                createGamePresenter.addWindowEventHandlers();
                view.getScene().setRoot(createGameView);
                createGameView.getScene().getWindow().sizeToScene();
            }
        });
    }

    private void updateView() {
        // Update view with current game state
        // - Current player
        // - Player positions
        // - Scores
        // etc.
    }

    public void addWindowEventHandlers() {

    }
}