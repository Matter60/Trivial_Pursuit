package TrivialPursuit.view.make;

import TrivialPursuit.model.TrivialPursuitController;
import TrivialPursuit.view.create.CreateGameView;
import TrivialPursuit.view.create.CreateGamePresenter;
import TrivialPursuit.view.home.HomeView;
import TrivialPursuit.view.home.HomePresenter;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import TrivialPursuit.view.game.GameView;
import TrivialPursuit.view.game.GamePresenter;
import javafx.scene.control.Alert;

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

        view.getLaadGameButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (model.savedGameExists()) {
                    if (model.loadGame()) {
                        GameView gameView = new GameView();
                        GamePresenter gamePresenter = new GamePresenter(model, gameView);
                        gamePresenter.addWindowEventHandlers();
                        view.getScene().setRoot(gameView);
                        gameView.getScene().getWindow().sizeToScene();
                    } else {
                        // Toon foutmelding als laden mislukt
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Fout bij laden");
                        alert.setHeaderText(null);
                        alert.setContentText("Het opgeslagen spel kon niet worden geladen.");
                        alert.showAndWait();
                    }
                } else {
                    // Toon melding als er geen opgeslagen spel is
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Geen opgeslagen spel");
                    alert.setHeaderText(null);
                    alert.setContentText("Er is geen opgeslagen spel gevonden.");
                    alert.showAndWait();
                }
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
    }

    private void updateView() {
    }

    public void addWindowEventHandlers() {
    }
}
