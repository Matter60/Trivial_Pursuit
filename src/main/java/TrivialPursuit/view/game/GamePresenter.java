package TrivialPursuit.view.game;

import TrivialPursuit.model.Speler;
import TrivialPursuit.model.TrivialPursuitController;
import TrivialPursuit.model.Vraag;
import TrivialPursuit.model.Kleur;
import TrivialPursuit.view.create.CreateGameView;
import TrivialPursuit.view.create.CreateGamePresenter;

import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import javafx.scene.control.Alert;
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
        clearPlayers();
        for (Speler speler : model.getSpelers()) {
            addPlayer(speler);
            updatePlayerPosition(speler);
            updatePlayerPartjes(speler);
        }
    }

    private void addPlayer(Speler speler) {
        // Voeg pion toe

        ImageView pawn = new ImageView(new Image(getClass().getResourceAsStream( "/"+ speler.getSpelerKleur().toString().toLowerCase() + ".png")));
        pawn.setFitHeight(40);
        pawn.setFitWidth(40);
        view.getPlayerPawns().put(speler.getNaam(), pawn);
        view.getBoardPane().getChildren().add(pawn);

        HBox partjesBox = new HBox(5);
        Label playerLabel = new Label(speler.getNaam() + " partjes: ");
        playerLabel.setTextFill(Color.WHITE);
        partjesBox.getChildren().add(playerLabel);
        view.getPlayerPartjesBoxes().put(speler.getNaam(), partjesBox);
        view.getPlayerInfoBox().getChildren().add(partjesBox);
    }

    private void clearPlayers() {
        for (ImageView pawn : view.getPlayerPawns().values()) {
            view.getBoardPane().getChildren().remove(pawn);
        }
        view.getPlayerPawns().clear();
        view.getPlayerInfoBox().getChildren().clear();
        view.getPlayerPartjesBoxes().clear();
    }

    private void addEventHandlers() {

        view.getRollDiceButton().setOnAction(event -> {

            int worp = model.gooiDobbelsteen();
            view.getDiceResultLabel().setText("Worp: " + worp);

            mogelijkeBestemmingen = model.getMogelijkeBestemmingen(worp);
            clearPossibleMoves();

            for (Integer pos : mogelijkeBestemmingen) {
                int[] coords = model.getCoordinaten(pos);
                addPossibleMove(pos, coords[0], coords[1]);
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

        Speler huidigeSpeler = model.getHuidigeSpeler();
        model.verplaatsHuidigeSpeler(positie);
        updatePlayerPosition(huidigeSpeler);

        clearPossibleMoves();

        Kleur veldKleur = model.getVeldKleur(positie);

        if (model.isRollAgainVeld(positie)) {
            showAlert("Opnieuw Gooien!", "Je mag nog een keer gooien!");
            view.getRollDiceButton().setDisable(false);
            return; // Niet naar volgende speler gaan
        }


        if (veldKleur != null) {

            List<Vraag> vragen = model.laadVraag(veldKleur);
            if (!vragen.isEmpty()) {
                huidigeVraag = vragen.get(0);
                showQuestion(huidigeVraag.getVraag(), huidigeVraag.getMogelijkeAntwoorden());
            }
        }

    }

    private void handleAnswer() {
        if (huidigeVraag == null)
            return;

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

        hideQuestion();

        boolean correct = huidigeVraag.checkAntwoord(selectedIndex);
        boolean partjeVerdiend = model.checkAntwoord(huidigeVraag, selectedIndex, huidigeSpeler, positie);

        if (correct) {
            // Toon alert voor juist antwoord
           showAlert("Correct!", "Je hebt het juiste antwoord gegeven!");

            if (partjeVerdiend) {
                // Update UI voor nieuw partje
                updatePlayerPartjes(huidigeSpeler);

                // Toon alert voor verdiend partje
                Kleur veldKleur = model.getVeldKleur(positie);
                showAlert("Partje Verdiend!",
                        huidigeSpeler.getNaam() + " heeft een " + veldKleur.toString().toLowerCase()
                                + " partje verdiend!");
            }
        } else {
            // Toon alert voor fout antwoord
            showAlert("Helaas!",
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
        updateCurrentPlayer(
                huidigeSpeler.getNaam(),
                huidigeSpeler.getSpelerKleur());
    }

    private void updatePlayerPosition(Speler speler) {
        int positie = model.getSpelerPositie(speler);
        int[] coords = model.getCoordinaten(positie);
        ImageView pawn = view.getPlayerPawns().get(speler.getNaam());
        if (pawn != null) {
            pawn.setLayoutX(coords[0]);
            pawn.setLayoutY(coords[1]);
        }
    }

    private void updatePlayerPartjes(Speler speler) {
        HBox partjesBox = view.getPlayerPartjesBoxes().get(speler.getNaam());
        if (partjesBox != null) {
            partjesBox.getChildren().clear();

            Label playerLabel = new Label(speler.getNaam() + " partjes: ");
            playerLabel.setTextFill(Color.WHITE);
            partjesBox.getChildren().add(playerLabel);

            for (Kleur kleur : model.getSpelerPartjes(speler)) {
                Circle partje = new Circle(8);
                partje.setFill(convertKleurToColor(kleur));
                partje.setStroke(Color.WHITE);
                partjesBox.getChildren().add(partje);
            }
        }
    }

    private Color convertKleurToColor(Kleur kleur) {
        switch (kleur) {
            case BLAUW: return Color.BLUE;
            case GROEN: return Color.GREEN;
            case GEEL: return Color.YELLOW;
            case ROZE: return Color.PINK;
            case ORANJE: return Color.ORANGE;
            case BRUIN: return Color.BROWN;
            case WIT: return Color.WHITE;
            default: return Color.BLACK;
        }
    }

    public void addWindowEventHandlers() {
        // Voeg hier eventuele window event handlers toe
    }

    public void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public void showQuestion(String question, List<String> answers) {
        view.getQuestionLabel().setText(question);

        for (int i = 0; i < answers.size() && i < view.getAnswerButtons().size(); i++) {
            view.getAnswerButtons().get(i).setText(answers.get(i));
            view.getAnswerButtons().get(i).setVisible(true);
        }

        // Hide unused radio buttons
        for (int i = answers.size(); i < view.getAnswerButtons().size(); i++) {
            view.getAnswerButtons().get(i).setVisible(false);
        }

        view.getAnswerButton().setVisible(true);
        view.getVraagBox().setVisible(true);
    }

    public void hideQuestion() {
        view.getVraagBox().setVisible(false);
        view.getAnswerButton().setVisible(false);
        view.getAnswerGroup().selectToggle(null);
    }

    private void addPossibleMove(int position, int x, int y) {
        Circle moveCircle = new Circle(15);
        moveCircle.setFill(Color.YELLOW);
        moveCircle.setOpacity(0.5);
        moveCircle.setStroke(Color.BLACK);
        moveCircle.setCenterX(x + 20);
        moveCircle.setCenterY(y + 20);
        moveCircle.setUserData(position);
        view.getPossibleMoves().put(position, moveCircle);
        view.getBoardPane().getChildren().add(moveCircle);
    }

    private void clearPossibleMoves() {
        for (Circle circle : view.getPossibleMoves().values()) {
            view.getBoardPane().getChildren().remove(circle);
        }
        view.getPossibleMoves().clear();
    }

    private void updateCurrentPlayer(String playerName, Kleur playerKleur) {
        view.getCurrentPlayerLabel().setText("Huidige Speler: " + playerName);
        view.getCurrentPlayerLabel().setTextFill(convertKleurToColor(playerKleur));
    }

}