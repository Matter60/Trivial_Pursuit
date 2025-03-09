package TrivialPursuit.view.game;

import TrivialPursuit.model.Speler;
import TrivialPursuit.model.TrivialPursuitController;
import TrivialPursuit.model.Vraag;
import TrivialPursuit.model.Kleur;
import TrivialPursuit.view.create.CreateGameView;
import TrivialPursuit.view.create.CreateGamePresenter;
import javafx.event.ActionEvent;
import javafx.scene.control.RadioButton;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import java.util.List;

public class GamePresenter {
    private TrivialPursuitController model;
    private GameView view;
    private List<Integer> mogelijkeBestemmingen;
    private Vraag huidigeVraag;

    public GamePresenter(TrivialPursuitController model, GameView view) {
        this.model = model;
        this.view = view;
        this.initializePlayers();
        this.addEventHandlers();
        this.updateView();
    }

    private void initializePlayers() {
        view.clearPlayers();
        for (Speler speler : model.getSpelers()) {
            view.addPlayer(speler);
            updatePlayerPosition(speler);
            updatePlayerPartjes(speler);
        }
    }

    private void addEventHandlers() {
        // Dobbelsteen knop
        view.getRollDiceButton().setOnAction(event -> {
            // Verberg eventuele openstaande vraag
            view.hideQuestion();

            // Gooi de dobbelsteen
            int worp = model.gooiDobbelsteen();
            view.getDiceResultLabel().setText("Worp: " + worp);


            // Toon mogelijke bestemmingen
            mogelijkeBestemmingen = model.getMogelijkeBestemmingen(worp);
            view.clearPossibleMoves();

            for (Integer pos : mogelijkeBestemmingen) {
                int[] coords = model.getCoordinaten(pos);
                view.addPossibleMove(pos, coords[0], coords[1]);
                Circle moveCircle = view.getPossibleMoves().get(pos);
                moveCircle.setOnMouseClicked(e -> handleMove(pos));
            }

            // Disable de dobbelsteen knop tot er een zet is gedaan
            view.getRollDiceButton().setDisable(true);
        });

        // Antwoord knop
        view.getAnswerButton().setOnAction(event -> handleAnswer());

        // Terug knop
        view.getBackButton().setOnAction(event -> {
            CreateGameView createGameView = new CreateGameView();
            CreateGamePresenter createGamePresenter = new CreateGamePresenter(model, createGameView);
            createGamePresenter.addWindowEventHandlers();
            view.getScene().setRoot(createGameView);
            createGameView.getScene().getWindow().sizeToScene();
        });
    }

    private void handleMove(int positie) {
        // Verplaats de speler
        Speler huidigeSpeler = model.getHuidigeSpeler();
        model.verplaatsHuidigeSpeler(positie);
        updatePlayerPosition(huidigeSpeler);

        // Verwijder de mogelijke zetten
        view.clearPossibleMoves();

        // Check het type veld en handel het af
        Kleur veldKleur = model.getVeldKleur(positie);

        // Check eerst voor roll again, want dit moet altijd gebeuren ongeacht vragen
        if (model.isRollAgainVeld(positie)) {
            view.showAlert("Opnieuw Gooien!", "Je mag nog een keer gooien!");
            view.getRollDiceButton().setDisable(false);
            return; // Niet naar volgende speler gaan
        }

        // Dan check voor vragen als het geen roll again veld is
        if (veldKleur != null) {
            // Haal een vraag op voor deze kleur
            List<Vraag> vragen = model.laadVraag(veldKleur);
            if (!vragen.isEmpty()) {
                huidigeVraag = vragen.get(0); // Neem de eerste vraag
                view.showQuestion(huidigeVraag.getVraag(), huidigeVraag.getMogelijkeAntwoorden());
                return; // Wacht op antwoord via handleAnswer
            }
        }

        // Als er geen vraag is, ga direct door naar de volgende speler
        volgendeSpeler();
    }

    private void handleAnswer() {
        if (huidigeVraag == null)
            return;

        // Bepaal welk antwoord is geselecteerd
        RadioButton selectedButton = (RadioButton) view.getAnswerGroup().getSelectedToggle();
        if (selectedButton == null)
            return;

        int selectedIndex = -1;
        for (int i = 0; i < huidigeVraag.getMogelijkeAntwoorden().size(); i++) {
            if (huidigeVraag.getMogelijkeAntwoorden().get(i).equals(selectedButton.getText())) {
                selectedIndex = i;
                break;
            }
        }

        Speler huidigeSpeler = model.getHuidigeSpeler();
        int positie = model.getSpelerPositie(huidigeSpeler);

        view.hideQuestion();

        boolean correct = huidigeVraag.checkAntwoord(selectedIndex);
        boolean partjeVerdiend = model.checkAntwoord(huidigeVraag, selectedIndex, huidigeSpeler, positie);

        if (correct) {
            // Toon alert voor juist antwoord
            view.showAlert("Correct!", "Je hebt het juiste antwoord gegeven!");

            if (partjeVerdiend) {
                // Update UI voor nieuw partje
                updatePlayerPartjes(huidigeSpeler);

                // Toon alert voor verdiend partje
                Kleur veldKleur = model.getVeldKleur(positie);
                view.showAlert("Partje Verdiend!",
                        huidigeSpeler.getNaam() + " heeft een " + veldKleur.toString().toLowerCase()
                                + " partje verdiend!");
            }
        } else {
            // Toon alert voor fout antwoord
            view.showAlert("Helaas!",
                    "Dat was niet het juiste antwoord.\nHet juiste antwoord was: " + huidigeVraag.getJuisteAntwoord());
        }

        volgendeSpeler();
    }

    private void volgendeSpeler() {
        model.volgendeSpeler();
        view.getRollDiceButton().setDisable(false);
        updateView();
    }

    private void updateView() {
        Speler huidigeSpeler = model.getHuidigeSpeler();
        view.updateCurrentPlayer(
                huidigeSpeler.getNaam(),
                huidigeSpeler.getSpelerKleur());
    }

    private void updatePlayerPosition(Speler speler) {
        int positie = model.getSpelerPositie(speler);
        int[] coords = model.getCoordinaten(positie);
        view.updatePawnPosition(speler, coords[0], coords[1]);
    }

    private void updatePlayerPartjes(Speler speler) {
        List<Kleur> partjes = model.getSpelerPartjes(speler);
        view.updatePlayerPartjes(speler, partjes);
    }

    public void addWindowEventHandlers() {
        // Voeg hier eventuele window event handlers toe
    }
}