package TrivialPursuit.view.create;

import TrivialPursuit.model.Kleur;
import TrivialPursuit.model.TrivialPursuitController;
import TrivialPursuit.view.game.GamePresenter;
import TrivialPursuit.view.game.GameView;
import TrivialPursuit.view.home.HomeView;
import TrivialPursuit.view.home.HomePresenter;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.util.HashSet;
import java.util.Set;

public class CreateGamePresenter {
    private TrivialPursuitController model;
    private CreateGameView view;

    public CreateGamePresenter(TrivialPursuitController model, CreateGameView view) {
        this.model = model;
        this.view = view;
        this.addEventHandlers();
        this.updateView();
    }

    private void addEventHandlers() {
        view.getStartButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!valideerSpelers()) {
                    return;
                }

                for (int i = 0; i < view.getPlayerFields().size(); i++) {
                    TextField playerField = view.getPlayerFields().get(i);
                    ComboBox<Kleur> colorSelector = view.getColorSelectors().get(i);

                    if (!playerField.getText().trim().isEmpty() && colorSelector.getValue() != null) {
                        model.voegSpelerToe(playerField.getText().trim(), colorSelector.getValue());
                    }
                }

                model.startSpel();

                GameView gameView = new GameView();
                GamePresenter gamePresenter = new GamePresenter(model, gameView);
                gamePresenter.addWindowEventHandlers();
                view.getScene().setRoot(gameView);
                gameView.getScene().getWindow().sizeToScene();
            }
        });

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

    private boolean valideerSpelers() {
        int aantalSpelers = 0;
        Set<Kleur> gekozenKleuren = new HashSet<>();

        for (int i = 0; i < view.getPlayerFields().size(); i++) {
            TextField playerField = view.getPlayerFields().get(i);
            ComboBox<Kleur> colorSelector = view.getColorSelectors().get(i);

            if (!playerField.getText().trim().isEmpty() && colorSelector.getValue() != null) {
                aantalSpelers++;
                if (!gekozenKleuren.add(colorSelector.getValue())) {
                    toonFoutmelding("Dubbele kleur", "Elke speler moet een unieke kleur hebben!");
                    return false;
                }
            }
        }

        if (aantalSpelers < 2) {
            toonFoutmelding("Niet genoeg spelers", "Er moeten minstens 2 spelers zijn om het spel te starten!");
            return false;
        }

        return true;
    }

    private void toonFoutmelding(String titel, String bericht) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titel);
        alert.setHeaderText(null);
        alert.setContentText(bericht);
        alert.showAndWait();
    }

    private void updateView() {
        // Updates van de view gebaseerd op het model
    }

    public void addWindowEventHandlers() {
        // Voeg hier eventuele window event handlers toe
    }
}
