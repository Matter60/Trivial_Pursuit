package TrivialPursuit.view.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class GameView extends BorderPane {
    // private Button backButton;

    private Button rollDiceButton;
    private Button saveGameButton;
    private Button answerButton;
    private Button backButton;
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

        backButton = new Button("←");
        rollDiceButton = new Button("Gooi Dobbelsteen");
        answerButton = new Button("Beantwoord");
        currentPlayerLabel = new Label("Huidige Speler: ");
        diceResultLabel = new Label("");
        questionLabel = new Label("");
        saveGameButton = new Button("Opslaan");


        answerGroup = new ToggleGroup();
        answerButtons = new ArrayList<>();
        playerPawns = new HashMap<>();
        possibleMoves = new HashMap<>();
        playerPartjesBoxes = new HashMap<>();


        for (int i = 0; i < 4; i++) {
            RadioButton rb = new RadioButton("");
            rb.setToggleGroup(answerGroup);
            rb.setWrapText(true);
            answerButtons.add(rb);
        }


        vraagBox = new VBox(20);
        playerInfoBox = new VBox(10);
        boardPane = new Pane();

        Image boardImage = new Image(getClass().getResourceAsStream("/bord.png"));
        boardImageView = new ImageView(boardImage);
        boardImageView.setFitWidth(boardImage.getWidth() / 2);
        boardImageView.setFitHeight(boardImage.getHeight() / 2);
        boardImageView.setPreserveRatio(true);

        boardPane.getChildren().add(boardImageView);

    }

    private void layoutNodes() {

        HBox topBox = new HBox(20);
        topBox.getChildren().addAll(backButton, saveGameButton, currentPlayerLabel);
        topBox.setAlignment(Pos.CENTER_LEFT);
        topBox.setPadding(new Insets(10));
        this.setTop(topBox);

        this.setCenter(boardPane);


        VBox rightBox = new VBox(20);
        rightBox.setAlignment(Pos.TOP_CENTER);
        rightBox.setPadding(new Insets(20));


        rightBox.setPrefWidth(300);
        rightBox.setPrefHeight(600);


        rightBox.getChildren().addAll(
                playerInfoBox,
                rollDiceButton,
                diceResultLabel,
                vraagBox);


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

        this.setBackground(new Background(new BackgroundFill(Color.DARKCYAN, CornerRadii.EMPTY, Insets.EMPTY)));

        String buttonStyle = "-fx-background-color: #8B8000; -fx-text-fill: white; -fx-padding: 10px;";
        saveGameButton.setStyle(buttonStyle);
        backButton.setStyle(buttonStyle);
        rollDiceButton.setStyle(buttonStyle);
        answerButton.setStyle(buttonStyle);


        saveGameButton.setFont(Font.font("Georgia", FontWeight.BOLD, 24));
        backButton.setFont(Font.font("Georgia", FontWeight.BOLD, 24));
        rollDiceButton.setFont(Font.font("Georgia", FontWeight.BOLD, 16));
        answerButton.setFont(Font.font("Georgia", FontWeight.BOLD, 16));
        currentPlayerLabel.setFont(Font.font("Georgia", FontWeight.BOLD, 16));
        diceResultLabel.setFont(Font.font("Georgia", FontWeight.BOLD, 24));
        questionLabel.setFont(Font.font("Georgia", FontWeight.BOLD, 16));

        currentPlayerLabel.setTextFill(Color.WHITE);
        diceResultLabel.setTextFill(Color.YELLOW);
        questionLabel.setTextFill(Color.WHITE);

        answerButtons.forEach(rb -> {
            rb.setTextFill(Color.WHITE);
            rb.setFont(Font.font("Georgia", 14));
        });
    }

    // Getters
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

    public void setQuestionText(String text) {
        questionLabel.setText(text);
    }

    public void setAnswerButtonText(int index, String text) {
        if (index >= 0 && index < answerButtons.size()) {
            answerButtons.get(index).setText(text);
        }
    }

    public void setAnswerButtonVisible(int index, boolean visible) {
        if (index >= 0 && index < answerButtons.size()) {
            answerButtons.get(index).setVisible(visible);
        }
    }

    public void setVraagBoxVisible(boolean visible) {
        vraagBox.setVisible(visible);
    }

    public void setAnswerButtonVisible(boolean visible) {
        answerButton.setVisible(visible);
    }

    public Button getBackButton() {
        return backButton;
    }
}
