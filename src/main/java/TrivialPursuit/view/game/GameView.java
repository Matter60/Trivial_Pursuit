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

import java.util.*;

public class GameView extends BorderPane {
    private Button backButton;
    private Button rollDiceButton;
    private Button saveGameButton;
    private Button answerButton;
    private Label currentPlayerLabel;
    private Label diceResultLabel;
    private Label questionLabel;
    private VBox vraagBox;
    private ToggleGroup answerGroup;
    private List<RadioButton> answerButtons;
    private ImageView boardImageView;
    private Map<String, ImageView> playerPawns;
    private Map<Integer, Circle> possibleMoves;
    private VBox playerInfoBox;
    private Pane boardPane;
    private Map<String, HBox> playerPartjesBoxes;

    public GameView() {
        this.initialiseNodes();
        this.layoutNodes();
        this.applyStyles();
    }

    private void initialiseNodes() {
        // Initialize basic UI components
        backButton = new Button("←");
        rollDiceButton = new Button("Gooi Dobbelsteen");
        answerButton = new Button("Beantwoord");
        currentPlayerLabel = new Label("Huidige Speler: ");
        diceResultLabel = new Label("");
        questionLabel = new Label("");
        saveGameButton = new Button("Opslaan");

        // Initialize collections
        answerGroup = new ToggleGroup();
        answerButtons = new ArrayList<>();
        playerPawns = new HashMap<>();
        possibleMoves = new HashMap<>();
        playerPartjesBoxes = new HashMap<>();

        // Create answer buttons
        for (int i = 0; i < 4; i++) {
            RadioButton rb = new RadioButton("");
            rb.setToggleGroup(answerGroup);
            rb.setWrapText(true);
            answerButtons.add(rb);
        }

        // Initialize containers
        vraagBox = new VBox(20);
        playerInfoBox = new VBox(10);
        boardPane = new Pane();

        // Load and set up board image
        Image boardImage = new Image(getClass().getResourceAsStream("/bord.png"));
        boardImageView = new ImageView(boardImage);
        boardImageView.setFitWidth(boardImage.getWidth() / 2);
        boardImageView.setFitHeight(boardImage.getHeight() / 2);
        boardImageView.setPreserveRatio(true);

        boardPane.getChildren().add(boardImageView);

    }

    private void layoutNodes() {
        // Set up top section
        HBox topBox = new HBox(20);
        topBox.getChildren().addAll(backButton, saveGameButton, currentPlayerLabel);
        topBox.setAlignment(Pos.CENTER_LEFT);
        topBox.setPadding(new Insets(10));
        this.setTop(topBox);

        this.setCenter(boardPane);

        // Maak een VBox voor de rechter sectie en geef het een vaste breedte en hoogte
        VBox rightBox = new VBox(20);
        rightBox.setAlignment(Pos.TOP_CENTER);
        rightBox.setPadding(new Insets(20));

        // Stel de gewenste afmetingen in voor de rightBox
        rightBox.setPrefWidth(300); // Vaste breedte
        rightBox.setPrefHeight(600); // Vaste hoogte

        // Voeg de inhoud toe aan de VBox
        rightBox.getChildren().addAll(
                playerInfoBox,
                rollDiceButton,
                diceResultLabel,
                vraagBox);

        // Zet de rechter sectie als de inhoud van de BorderPane
        this.setRight(rightBox);

        vraagBox.setSpacing(10);
        vraagBox.getChildren().addAll(questionLabel);
        vraagBox.getChildren().addAll(answerButtons);
        vraagBox.getChildren().add(answerButton);
        vraagBox.setVisible(false);

        rollDiceButton.setMaxWidth(Double.MAX_VALUE);
        answerButton.setMaxWidth(Double.MAX_VALUE);

        questionLabel.setWrapText(true);
        questionLabel.setMaxWidth(250);
    }

    private void applyStyles() {
        // Apply basic styles
        this.setBackground(new Background(new BackgroundFill(Color.DARKCYAN, CornerRadii.EMPTY, Insets.EMPTY)));

        String buttonStyle = "-fx-background-color: #8B8000; -fx-text-fill: white; -fx-padding: 10px;";
        backButton.setStyle(buttonStyle);
        rollDiceButton.setStyle(buttonStyle);
        answerButton.setStyle(buttonStyle);

        // Set fonts
        backButton.setFont(Font.font("Georgia", FontWeight.BOLD, 24));
        rollDiceButton.setFont(Font.font("Georgia", FontWeight.BOLD, 16));
        answerButton.setFont(Font.font("Georgia", FontWeight.BOLD, 16));
        currentPlayerLabel.setFont(Font.font("Georgia", FontWeight.BOLD, 16));
        diceResultLabel.setFont(Font.font("Georgia", FontWeight.BOLD, 24));
        questionLabel.setFont(Font.font("Georgia", FontWeight.BOLD, 16));

        // Set text colors
        currentPlayerLabel.setTextFill(Color.WHITE);
        diceResultLabel.setTextFill(Color.YELLOW);
        questionLabel.setTextFill(Color.WHITE);

        answerButtons.forEach(rb -> {
            rb.setTextFill(Color.WHITE);
            rb.setFont(Font.font("Georgia", 14));
        });
    }

    // Getters
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

    public Label getCurrentPlayerLabel() {
        return currentPlayerLabel;
    }

    public Label getQuestionLabel() {
        return questionLabel;
    }

    public VBox getVraagBox() {
        return vraagBox;
    }

    public Map<String, ImageView> getPlayerPawns() {
        return playerPawns;
    }

    public Map<String, HBox> getPlayerPartjesBoxes() {
        return playerPartjesBoxes;
    }

    public VBox getPlayerInfoBox() {
        return playerInfoBox;
    }

    public Button getSaveGameButton() {
        return saveGameButton;
    }

}