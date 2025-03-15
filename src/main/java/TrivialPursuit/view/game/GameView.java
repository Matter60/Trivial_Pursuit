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

    private Button gooiDobbelsteenButton;
    private Button saveGameButton;
    private Button antwoordButton;
    private Button terugButton;
    private Label currentPlayerLabel;
    private Label DobbelsteenResLabel;
    private Label vraagLabel;
    private VBox vraagBox;
    private ToggleGroup antwoordGroep;
    private List<RadioButton> AntwoordenButtons;
    private ImageView bordAfbeelding;
    private Map<String, ImageView> spelerPionnen;
    private Map<Integer, Circle> mogelijkeZetten;
    private VBox spelerInfoBox;
    private Pane boardPane;
    private Map<String, HBox> spelerPartjesBoxes;

    public GameView() {
        this.initialiseNodes();
        this.layoutNodes();
        this.applyStyles();
    }

    private void initialiseNodes() {

        terugButton = new Button("←");
        gooiDobbelsteenButton = new Button("Gooi Dobbelsteen");
        antwoordButton = new Button("Beantwoord");
        currentPlayerLabel = new Label("Huidige Speler: ");
        DobbelsteenResLabel = new Label("");
        vraagLabel = new Label("");
        saveGameButton = new Button("Opslaan");


        antwoordGroep = new ToggleGroup();
        AntwoordenButtons = new ArrayList<>();
        spelerPionnen = new HashMap<>();
        mogelijkeZetten = new HashMap<>();
        spelerPartjesBoxes = new HashMap<>();


        for (int i = 0; i < 4; i++) {
            RadioButton rb = new RadioButton("");
            rb.setToggleGroup(antwoordGroep);
            rb.setWrapText(true);
            AntwoordenButtons.add(rb);
        }


        vraagBox = new VBox(20);
        spelerInfoBox = new VBox(10);
        boardPane = new Pane();

        Image boardImage = new Image(getClass().getResourceAsStream("/bord.png"));
        bordAfbeelding = new ImageView(boardImage);
        bordAfbeelding.setFitWidth(boardImage.getWidth() / 2);
        bordAfbeelding.setFitHeight(boardImage.getHeight() / 2);
        bordAfbeelding.setPreserveRatio(true);

        boardPane.getChildren().add(bordAfbeelding);

    }

    private void layoutNodes() {

        HBox topBox = new HBox(20);
        topBox.getChildren().addAll(terugButton, saveGameButton, currentPlayerLabel);
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
                spelerInfoBox,
                gooiDobbelsteenButton,
                DobbelsteenResLabel,
                vraagBox);


        this.setRight(rightBox);

        vraagBox.setSpacing(10);
        vraagBox.getChildren().addAll(vraagLabel);
        vraagBox.getChildren().addAll(AntwoordenButtons);
        vraagBox.getChildren().add(antwoordButton);
        vraagBox.setVisible(false);

        gooiDobbelsteenButton.setMaxWidth(Double.MAX_VALUE);
        antwoordButton.setMaxWidth(Double.MAX_VALUE);

        vraagLabel.setWrapText(true);
        vraagLabel.setMaxWidth(250);
    }

    private void applyStyles() {

        this.setBackground(new Background(new BackgroundFill(Color.DARKCYAN, CornerRadii.EMPTY, Insets.EMPTY)));

        String buttonStyle = "-fx-background-color: #8B8000; -fx-text-fill: white; -fx-padding: 10px;";
        saveGameButton.setStyle(buttonStyle);
        terugButton.setStyle(buttonStyle);
        gooiDobbelsteenButton.setStyle(buttonStyle);
        antwoordButton.setStyle(buttonStyle);


        saveGameButton.setFont(Font.font("Georgia", FontWeight.BOLD, 24));
        terugButton.setFont(Font.font("Georgia", FontWeight.BOLD, 24));
        gooiDobbelsteenButton.setFont(Font.font("Georgia", FontWeight.BOLD, 16));
        antwoordButton.setFont(Font.font("Georgia", FontWeight.BOLD, 16));
        currentPlayerLabel.setFont(Font.font("Georgia", FontWeight.BOLD, 16));
        DobbelsteenResLabel.setFont(Font.font("Georgia", FontWeight.BOLD, 24));
        vraagLabel.setFont(Font.font("Georgia", FontWeight.BOLD, 16));

        currentPlayerLabel.setTextFill(Color.WHITE);
        DobbelsteenResLabel.setTextFill(Color.YELLOW);
        vraagLabel.setTextFill(Color.WHITE);

        AntwoordenButtons.forEach(rb -> {
            rb.setTextFill(Color.WHITE);
            rb.setFont(Font.font("Georgia", 14));
        });
    }

    // Getters
    public Button getGooiDobbelsteenButton() {
        return gooiDobbelsteenButton;
    }

    public Button getAntwoordButton() {
        return antwoordButton;
    }

    public ToggleGroup getAntwoordGroep() {
        return antwoordGroep;
    }

    public List<RadioButton> getAntwoordenButtons() {
        return AntwoordenButtons;
    }

    public Map<Integer, Circle> getMogelijkeZetten() {
        return mogelijkeZetten;
    }

    public Pane getBoardPane() {
        return boardPane;
    }

    public Label getDobbelsteenResLabel() {
        return DobbelsteenResLabel;
    }

    public Label getCurrentPlayerLabel() {
        return currentPlayerLabel;
    }

    public Map<String, ImageView> getSpelerPionnen() {
        return spelerPionnen;
    }

    public Map<String, HBox> getSpelerPartjesBoxes() {
        return spelerPartjesBoxes;
    }

    public VBox getSpelerInfoBox() {
        return spelerInfoBox;
    }

    public Button getSaveGameButton() {
        return saveGameButton;
    }

    public void setQuestionText(String text) {
        vraagLabel.setText(text);
    }

    public void setAnswerButtonText(int index, String text) {
        if (index >= 0 && index < AntwoordenButtons.size()) {
            AntwoordenButtons.get(index).setText(text);
        }
    }

    public void setAnswerButtonVisible(int index, boolean visible) {
        if (index >= 0 && index < AntwoordenButtons.size()) {
            AntwoordenButtons.get(index).setVisible(visible);
        }
    }

    public void setVraagBoxVisible(boolean visible) {
        vraagBox.setVisible(visible);
    }

    public void setAnswerButtonVisible(boolean visible) {
        antwoordButton.setVisible(visible);
    }

    public Button getTerugButton() {
        return terugButton;
    }
}
