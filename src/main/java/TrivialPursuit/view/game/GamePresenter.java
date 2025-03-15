package TrivialPursuit.view.game;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import TrivialPursuit.model.Kleur;
import TrivialPursuit.model.Speler;
import TrivialPursuit.model.TrivialPursuitController;
import TrivialPursuit.model.Vraag;
import TrivialPursuit.view.home.HomePresenter;
import TrivialPursuit.view.home.HomeView;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;

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
            updateplayerposView(speler);
            updatePlayerPartjesView(speler);
        }
    }

    private void addPlayer(Speler speler) {
        // Voeg pion toe

        ImageView pawn = new ImageView(new Image(
                getClass().getResourceAsStream("/" + speler.getSpelerKleur().toString().toLowerCase() + ".png")));
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

    // Zeker zijn dat er geen spelers meer zijn op velden
    private void clearPlayers() {
        for (ImageView pion : view.getPlayerPawns().values()) {
            view.getBoardPane().getChildren().remove(pion);
        }
        view.getPlayerPawns().clear();
        view.getPlayerInfoBox().getChildren().clear();
        view.getPlayerPartjesBoxes().clear();
    }

    private void addEventHandlers() {
        // Als er op de dobbelsteen wordt geklikt
        // Doe een worp en bereken mogelijke bestemmingen
        // Voeg gele cirkels toe op mogelijke bestemmingen
        view.getRollDiceButton().setOnAction(event -> {
            int worp = model.gooiDobbelsteen();
            view.getDiceResultLabel().setText("Worp: " + worp);

            mogelijkeBestemmingen = model.berekenBereikbareVeldIndices(worp);
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
            Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmAlert.setTitle("Terug naar hoofdmenu");
            confirmAlert.setHeaderText("Wil je het spel opslaan voordat je teruggaat?");
            confirmAlert.setContentText(
                    "Kies 'Opslaan' om je spel op te slaan, 'Nee' om terug te gaan zonder opslaan, of 'Annuleren' om door te spelen.");

            ButtonType saveButton = new ButtonType("Opslaan");
            ButtonType noSaveButton = new ButtonType("Nee");
            ButtonType cancelButton = new ButtonType("Annuleren", ButtonBar.ButtonData.CANCEL_CLOSE);

            confirmAlert.getButtonTypes().setAll(saveButton, noSaveButton, cancelButton);

            confirmAlert.showAndWait().ifPresent(response -> {
                if (response == saveButton) {
                    if (saveGame()) {
                        goToHomeView();
                    }
                } else if (response == noSaveButton) {
                    // Go back without saving
                    goToHomeView();
                }
                // If cancel is clicked, do nothing and continue the game
            });
        });

        view.getSaveGameButton().setOnAction(event -> {
            if (saveGame()) {
                goToHomeView();
            }
        });
    }

    private void handleMove(int positie) {
        Speler huidigeSpeler = model.getHuidigeSpeler();
        // backend
        model.verplaatsHuidigeSpeler(positie);
        // scherm
        updateplayerposView(huidigeSpeler);
        // Gele cirkels wegdoen
        clearPossibleMoves();

        // Check voor afgelopen spel
        if (model.isSpelAfgelopen()) {
            toonFoutMelding("Gefeliciteerd!",
                    model.getWinnaar().getNaam() + " heeft gewonnen!");
            HomeView homeView = new HomeView();
            HomePresenter homePresenter = new HomePresenter(model, homeView);
            homePresenter.addWindowEventHandlers();
            view.getScene().setRoot(homeView);
            homeView.getScene().getWindow().sizeToScene();
            return;
        }

        // Check voor opnieuw gooien veld
        if (model.isRollAgainVeld(positie)) {
            toonFoutMelding("Opnieuw Gooien!", "Je mag nog een keer gooien!");
            view.getRollDiceButton().setDisable(false);
            return;
        }

        // Check voor vraagveld
        if (model.getVeldKleur(positie) != null) {
            List<Vraag> vragen = model.laadVraag(model.getVeldKleur(positie));
            if (!vragen.isEmpty()) {
                huidigeVraag = vragen.get(0);
                showQuestion(huidigeVraag.getVraag(), huidigeVraag.getMogelijkeAntwoorden());
            }
        }
    }

    // Antwoord afhandelen
    private void handleAnswer() {
        // Check of er een vraag is
        if (huidigeVraag == null) {
            toonFoutMelding("Fout", "Er is geen vraag om te beantwoorden!");
            return;
        }

        // Check of er een antwoord is geselecteerd
        RadioButton selectedButton = (RadioButton) view.getAnswerGroup().getSelectedToggle();
        if (selectedButton == null) {
            toonFoutMelding("Fout", "Selecteer een antwoord voordat je verder gaat!");
            return;
        }

        int selectedIndex = -1;
        for (int i = 0; i < huidigeVraag.getMogelijkeAntwoorden().size(); i++) {
            if (huidigeVraag.getMogelijkeAntwoorden().get(i).equals(selectedButton.getText())) {
                selectedIndex = i;
                break;
            }
        }

        int positie = model.getSpelerPositie(model.getHuidigeSpeler());

        hideQuestion();

        boolean correct = huidigeVraag.checkIndex(selectedIndex);
        boolean partjeVerdiend = model.checkAntwoord(huidigeVraag, selectedIndex, model.getHuidigeSpeler(), positie);

        if (correct) {
            toonFoutMelding(" Correct!", "Je hebt het juiste antwoord gegeven!");

            if (partjeVerdiend) {
                // Update UI voor nieuw partje
                updatePlayerPartjesView(model.getHuidigeSpeler());

                // Toon alert voor verdiend partje
                Kleur veldKleur = model.getVeldKleur(positie);
                toonFoutMelding("Partje Verdiend!",
                        model.getHuidigeSpeler().getNaam() + " heeft een "
                                + veldKleur.toString().toLowerCase() + " partje verdiend!");

                // Als alle partjes verzameld zijn, toon aparte melding
                if (model.heeftSpelerAllePartjes(model.getHuidigeSpeler())) {
                    toonFoutMelding(" Alle Partjes!",
                            "Je hebt nu alle partjes verzameld!\nGa naar het middenvak om te winnen!");
                }
            }
        } else {
            toonFoutMelding("Helaas!",
                    "Dat was niet het juiste antwoord.\nHet juiste antwoord was: "
                            + huidigeVraag.getJuisteAntwoord());
        }

        volgendeSpeler();
    }

    // Ga naar de volgende speler
    private void volgendeSpeler() {
        model.volgendeSpeler();
        view.getRollDiceButton().setDisable(false);
        updateView();
    }

    private void updateView() {
        Speler huidigeSpeler = model.getHuidigeSpeler();
        if (huidigeSpeler != null) {
            // Update UI voor huidige speler
            updateCurrentPlayer(
                    huidigeSpeler.getNaam(),
                    huidigeSpeler.getSpelerKleur());
        }
    }

    // Update de positie van de speler op het bord
    private void updateplayerposView(Speler speler) {
        int positie = model.getSpelerPositie(speler);
        int[] coords = model.getCoordinaten(positie);
        ImageView pawn = view.getPlayerPawns().get(speler.getNaam());
        if (pawn != null) {
            pawn.setLayoutX(coords[0]);
            pawn.setLayoutY(coords[1]);
        }
    }

    // Update de partjes van de speler
    private void updatePlayerPartjesView(Speler speler) {
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
            case BLAUW:
                return Color.BLUE;
            case GROEN:
                return Color.GREEN;
            case GEEL:
                return Color.YELLOW;
            case ROZE:
                return Color.PINK;
            case ORANJE:
                return Color.ORANGE;
            case BRUIN:
                return Color.BROWN;
            case WIT:
                return Color.WHITE;
            default:
                return Color.BLACK;
        }
    }

    public void addWindowEventHandlers() {
        // Voeg hier eventuele window event handlers toe
    }

    // Alert methode voor multiple use
    public void toonFoutMelding(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    // Vraag tonen

    private void showQuestion(String question, List<String> answers) {
        List<String> shuffledAnswers = new ArrayList<>(answers);
        Collections.shuffle(shuffledAnswers); // Shuffle the answers

        view.setQuestionText(question);

        // Set answer buttons
        for (int i = 0; i < shuffledAnswers.size() && i < view.getAnswerButtons().size(); i++) {
            view.setAnswerButtonText(i, shuffledAnswers.get(i));
            view.setAnswerButtonVisible(i, true);
        }

        // Hide unused buttons
        for (int i = shuffledAnswers.size(); i < view.getAnswerButtons().size(); i++) {
            view.setAnswerButtonVisible(i, false);
        }

        view.setAnswerButtonVisible(true);
        view.setVraagBoxVisible(true);
    }

    // Vraag verbergen
    public void hideQuestion() {
        view.setVraagBoxVisible(false);
        view.setAnswerButtonVisible(false);
        view.getAnswerGroup().selectToggle(null);
    }

    // Mogelijke bestemmingen tonen (cirkels)
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

    // Mogelijke bestemmingen verwijderen
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

    // Ga naar home view
    private void goToHomeView() {
        HomeView homeView = new HomeView();
        HomePresenter homePresenter = new HomePresenter(model, homeView);
        homePresenter.addWindowEventHandlers();
        view.getScene().setRoot(homeView);
        homeView.getScene().getWindow().sizeToScene();
    }

    private boolean saveGame() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Spel opslaan");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Trivial Pursuit Save Files", "*.txt"));
        fileChooser.setInitialDirectory(new File("data/saves"));

        File selectedFile = fileChooser.showSaveDialog(view.getScene().getWindow());
        if (selectedFile != null) {
            String filePath = selectedFile.getAbsolutePath();
            if (!filePath.endsWith(".txt")) {
                filePath += ".txt";
            }

            if (model.saveGame(filePath)) {
                toonFoutMelding("Spel opgeslagen", "Het spel is succesvol opgeslagen.");
                return true;
            } else {
                toonFoutMelding("Fout bij opslaan", "Er is een fout opgetreden bij het opslaan van het spel.");
                return false;
            }
        }
        return false;
    }

}
