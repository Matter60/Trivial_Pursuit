package TrivialPursuit.view.create;

import TrivialPursuit.model.Kleur;
import TrivialPursuit.model.TrivialPursuitController;
import TrivialPursuit.view.game.GamePresenter;
import TrivialPursuit.view.game.GameView;
import TrivialPursuit.view.home.HomeView;
import TrivialPursuit.view.home.HomePresenter;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
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
                    toonFoutmelding("Spelers", "Niet alle spelers zijn juist");
                }
                // nodig om nieuw spel te maken als er al eentje bezig is
                model.resetSpel();

                for (int i = 0; i < view.getSpelerVelden().size(); i++) {
                    TextField spelerVeld = view.getSpelerVelden().get(i);
                    ComboBox<Kleur> kleurSelector = view.getKleurSelectors().get(i);

                    if (!spelerVeld.getText().trim().isEmpty() && kleurSelector.getValue() != null) {
                        model.voegSpelerToe(spelerVeld.getText().trim(), kleurSelector.getValue());
                    }
                }

                try {
                    model.startSpel();

                    GameView gameView = new GameView();
                    GamePresenter gamePresenter = new GamePresenter(model, gameView);
                    gamePresenter.addWindowEventHandlers();
                    view.getScene().setRoot(gameView);
                    gameView.prefWidthProperty().set(1200);
                    gameView.prefHeightProperty().set(900);
                } catch (IllegalArgumentException e) {
                    toonFoutmelding("Fout bij starten spel", e.getMessage());
                }
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
        Set<String> gekozenNamen = new HashSet<>();
        boolean heeftFouten = false;
        StringBuilder foutBericht = new StringBuilder("De volgende fouten zijn gevonden:\n");

        // Controleer eerst of er spelers zijn met zowel naam als kleur
        for (int i = 0; i < view.getSpelerVelden().size(); i++) {
            TextField spelerVeld = view.getSpelerVelden().get(i);
            ComboBox<Kleur> kleurSelector = view.getKleurSelectors().get(i);
            String naam = spelerVeld.getText().trim();
            Kleur kleur = kleurSelector.getValue();

            // Als er een naam is maar geen kleur, of een kleur maar geen naam
            if (!naam.isEmpty() && kleur == null) {
                foutBericht.append("- Speler ").append(i + 1).append(" (").append(naam)
                        .append(") heeft geen kleur geselecteerd\n");
                heeftFouten = true;
            } else if (naam.isEmpty() && kleur != null) {
                foutBericht.append("- Kleur ").append(kleur).append(" heeft geen spelernaam\n");
                heeftFouten = true;
            } else if (!naam.isEmpty() && kleur != null) {
                // Beide zijn ingevuld, controleer op duplicaten
                aantalSpelers++;

                // Controleer op dubbele namen
                if (!gekozenNamen.add(naam)) {
                    foutBericht.append("- Spelernaam '").append(naam).append("' komt meerdere keren voor\n");
                    heeftFouten = true;
                }

                // Controleer op dubbele kleuren
                if (!gekozenKleuren.add(kleur)) {
                    foutBericht.append("- Kleur ").append(kleur).append(" is meerdere keren gekozen\n");
                    heeftFouten = true;
                }
            }
        }

        // Controleer minimaal aantal spelers
        if (aantalSpelers < 2) {
            foutBericht.append("- Er moeten minstens 2 spelers zijn om het spel te starten\n");
            heeftFouten = true;
        }

        // Controleer maximaal aantal spelers (optioneel)
        if (aantalSpelers > 6) {
            foutBericht.append("- Er kunnen maximaal 6 spelers deelnemen\n");
            heeftFouten = true;
        }

        if (heeftFouten) {
            toonFoutmelding("Validatiefout", foutBericht.toString());
            return false;
        }

        return true;
    }

    public void toonFoutmelding(String titel, String bericht) {
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
