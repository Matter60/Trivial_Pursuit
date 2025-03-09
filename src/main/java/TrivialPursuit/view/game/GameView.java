package TrivialPursuit.view.game;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import java.util.*;
import TrivialPursuit.model.Speler;
import TrivialPursuit.model.Kleur;
import javafx.scene.control.Alert;

public class GameView extends BorderPane {
    private Button backButton;
    private Button rollDiceButton;
    private Button answerButton;
    private Label currentPlayerLabel;
    private Label diceResultLabel;
    private Label questionLabel;
    private VBox questionBox;
    private ToggleGroup answerGroup;
    private List<RadioButton> answerButtons;
    private ImageView boardImageView;
    private Map<Speler, ImageView> playerPawns;
    private Map<Integer, Circle> possibleMoves;
    private VBox playerInfoBox;
    private Pane boardPane;
    private Map<Speler, HBox> playerPartjesBoxes;

    public GameView() {
        this.initialiseNodes();
        this.layoutNodes();
        this.applyStyles();
    }

    private void initialiseNodes() {
        // Back button
        backButton = new Button("←");
        backButton.setFont(Font.font("Georgia", FontWeight.BOLD, 24));
        backButton.setPadding(new Insets(10));

        // Roll dice button
        rollDiceButton = new Button("Gooi Dobbelsteen");
        rollDiceButton.setPrefWidth(150);
        rollDiceButton.setPadding(new Insets(10));

        // Current player label
        currentPlayerLabel = new Label("Huidige Speler: ");
        currentPlayerLabel.setFont(Font.font("Georgia", FontWeight.BOLD, 16));

        // Dice result label
        diceResultLabel = new Label("");
        diceResultLabel.setFont(Font.font("Georgia", FontWeight.BOLD, 24));

        // Question components
        questionLabel = new Label("");
        questionLabel.setFont(Font.font("Georgia", FontWeight.BOLD, 16));
        questionLabel.setWrapText(true);

        answerGroup = new ToggleGroup();
        answerButtons = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            RadioButton rb = new RadioButton("");
            rb.setToggleGroup(answerGroup);
            rb.setWrapText(true);
            answerButtons.add(rb);
        }

        answerButton = new Button("Beantwoord");
        answerButton.setPrefWidth(150);
        answerButton.setVisible(false);

        questionBox = new VBox(10);
        questionBox.getChildren().addAll(questionLabel);
        questionBox.getChildren().addAll(answerButtons);
        questionBox.getChildren().add(answerButton);
        questionBox.setVisible(false);

        // Board image
        Image boardImage = new Image(getClass().getResourceAsStream("/bord.png"));
        boardImageView = new ImageView(boardImage);
        boardImageView.setFitWidth(boardImage.getWidth() / 2);
        boardImageView.setFitHeight(boardImage.getHeight() / 2);
        boardImageView.setPreserveRatio(true);

        // Initialize maps
        playerPawns = new HashMap<>();
        possibleMoves = new HashMap<>();
        playerPartjesBoxes = new HashMap<>();

        // Player info box
        playerInfoBox = new VBox(10);
        playerInfoBox.setPadding(new Insets(10));
        playerInfoBox.setAlignment(Pos.TOP_CENTER);

        // Board container
        boardPane = new Pane();
        boardPane.getChildren().add(boardImageView);
    }

    private void layoutNodes() {
        // Top section with back button and current player
        HBox topBox = new HBox(20);
        topBox.getChildren().addAll(backButton, currentPlayerLabel);
        topBox.setAlignment(Pos.CENTER_LEFT);
        topBox.setPadding(new Insets(10));
        topBox.setStyle("-fx-background-color: DARKCYAN;");
        this.setTop(topBox);

        // Center section with board
        this.setCenter(boardPane);

        // Right section with player info, dice and questions
        VBox rightBox = new VBox(20);
        rightBox.getChildren().addAll(
                playerInfoBox,
                rollDiceButton,
                diceResultLabel,
                questionBox);
        rightBox.setAlignment(Pos.TOP_CENTER);
        rightBox.setPadding(new Insets(20));
        rightBox.setStyle("-fx-background-color: DARKCYAN;");
        this.setRight(rightBox);
    }

    private void applyStyles() {
        // Achtergrondkleur
        this.setBackground(new Background(new BackgroundFill(Color.DARKCYAN, CornerRadii.EMPTY, Insets.EMPTY)));

        // Knop styling
        String buttonStyle = "-fx-background-color: #8B8000; -fx-text-fill: white; -fx-padding: 10px;";
        backButton.setStyle(buttonStyle);
        rollDiceButton.setStyle(buttonStyle);
        answerButton.setStyle(buttonStyle);
        rollDiceButton.setFont(Font.font("Georgia", FontWeight.BOLD, 16));
        answerButton.setFont(Font.font("Georgia", FontWeight.BOLD, 16));

        // Label kleuren
        currentPlayerLabel.setTextFill(Color.WHITE);
        diceResultLabel.setTextFill(Color.YELLOW);
        questionLabel.setTextFill(Color.WHITE);

        // Answer button styling
        for (RadioButton rb : answerButtons) {
            rb.setTextFill(Color.WHITE);
            rb.setFont(Font.font("Georgia", 14));
        }
    }

    // Getters voor UI elementen
    public Button getBackButton() {
        return backButton;
    }

    public Button getRollDiceButton() {
        return rollDiceButton;
    }

    public Button getAnswerButton() {
        return answerButton;
    }

    public ToggleGroup getAnswerGroup() {
        return answerGroup;
    }

    public List<RadioButton> getAnswerButtons() {
        return answerButtons;
    }

    public Map<Integer, Circle> getPossibleMoves() {
        return possibleMoves;
    }

    public Pane getBoardPane() {
        return boardPane;
    }

    public Label getDiceResultLabel() {
        return diceResultLabel;
    }

    // UI update methoden
    public void showQuestion(String question, List<String> answers) {
        questionLabel.setText(question);

        // Update radio buttons with answers
        for (int i = 0; i < answers.size() && i < answerButtons.size(); i++) {
            answerButtons.get(i).setText(answers.get(i));
            answerButtons.get(i).setVisible(true);
        }

        // Hide unused radio buttons
        for (int i = answers.size(); i < answerButtons.size(); i++) {
            answerButtons.get(i).setVisible(false);
        }

        answerButton.setVisible(true);
        questionBox.setVisible(true);
    }

    public void hideQuestion() {
        questionBox.setVisible(false);
        answerButton.setVisible(false);
        answerGroup.selectToggle(null);
    }

    public void addPlayer(Speler speler) {
        // Voeg pion toe
        ImageView pawn = new ImageView(new Image(getClass().getResourceAsStream("/pawn.png")));
        pawn.setFitHeight(40);
        pawn.setFitWidth(40);
        playerPawns.put(speler, pawn);
        boardPane.getChildren().add(pawn);

        // Maak partjes overzicht voor deze speler
        HBox partjesBox = new HBox(5);
        Label playerLabel = new Label(speler.getNaam() + " partjes: ");
        playerLabel.setTextFill(Color.WHITE);
        partjesBox.getChildren().add(playerLabel);
        playerPartjesBoxes.put(speler, partjesBox);
        playerInfoBox.getChildren().add(partjesBox);
    }

    public void updatePlayerPartjes(Speler speler, List<Kleur> partjes) {
        HBox partjesBox = playerPartjesBoxes.get(speler);
        if (partjesBox != null) {
            // Verwijder oude partjes
            partjesBox.getChildren().clear();

            // Voeg naam label toe
            Label playerLabel = new Label(speler.getNaam() + " partjes: ");
            playerLabel.setTextFill(Color.WHITE);
            partjesBox.getChildren().add(playerLabel);

            // Voeg nieuwe partjes toe
            for (Kleur kleur : partjes) {
                Circle partje = new Circle(8);
                partje.setFill(convertKleurToColor(kleur));
                partje.setStroke(Color.WHITE);
                partjesBox.getChildren().add(partje);
            }
        }
    }

    public void addPossibleMove(int position, int x, int y) {
        Circle moveCircle = new Circle(15);
        moveCircle.setFill(Color.YELLOW);
        moveCircle.setOpacity(0.5);
        moveCircle.setStroke(Color.BLACK);
        moveCircle.setCenterX(x + 20);
        moveCircle.setCenterY(y + 20);
        moveCircle.setUserData(position);
        possibleMoves.put(position, moveCircle);
        boardPane.getChildren().add(moveCircle);
    }

    public void clearPossibleMoves() {
        for (Circle circle : possibleMoves.values()) {
            boardPane.getChildren().remove(circle);
        }
        possibleMoves.clear();
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

    public void updatePawnPosition(Speler speler, int x, int y) {
        ImageView pawn = playerPawns.get(speler);
        if (pawn != null) {
            // Centreer de pion door de helft van de breedte en hoogte af te trekken
            pawn.setLayoutX(x);
            pawn.setLayoutY(y);
        }
    }

    public void updateCurrentPlayer(String playerName, Kleur playerKleur) {
        currentPlayerLabel.setText("Huidige Speler: " + playerName);
        currentPlayerLabel.setTextFill(convertKleurToColor(playerKleur));
    }



    public void clearPlayers() {
        for (ImageView pawn : playerPawns.values()) {
            boardPane.getChildren().remove(pawn);
        }
        playerPawns.clear();
        playerInfoBox.getChildren().clear();
        playerPartjesBoxes.clear();
    }

    public void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}