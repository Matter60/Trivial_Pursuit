package TrivialPursuit.view.create;

import TrivialPursuit.model.Kleur;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.ArrayList;
import java.util.List;

public class CreateGameView extends BorderPane {
    private Button backButton;
    private Button startButton;
    private Label titleLabel;
    private Label minMaxLabel;
    private List<TextField> playerFields;
    private List<ComboBox<Kleur>> colorSelectors;
    private VBox playersBox;
    private HBox contentBox;

    public CreateGameView() {
        this.initialiseNodes();
        this.layoutNodes();
        this.applyStyles();

    }

    private void initialiseNodes() {
        backButton = new Button("←");
        backButton.setFont(Font.font("Georgia", FontWeight.BOLD, 24));
        backButton.setPadding(new Insets(10));
        backButton.setStyle("-fx-background-color: #8B8000; -fx-text-fill: white; -fx-padding: 10px;");

        titleLabel = new Label("Voeg spelers toe");
        titleLabel.setFont(Font.font("Georgia", FontWeight.BOLD, 24));
        titleLabel.setUnderline(true);
        titleLabel.setStyle("-fx-text-fill: white;");

        minMaxLabel = new Label("Minimaal 2, maximaal 6 spelers");
        minMaxLabel.setFont(Font.font("Georgia", FontWeight.NORMAL, 14));
        minMaxLabel.setStyle("-fx-text-fill: white;");

        playerFields = new ArrayList<>();
        colorSelectors = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            TextField playerField = new TextField();
            playerField.setPromptText("Speler " + (i + 1));
            playerField.setPrefWidth(150);
            playerField.setFont(Font.font("Georgia", FontWeight.NORMAL, 14));
            playerFields.add(playerField);

            ComboBox<Kleur> colorSelector = new ComboBox<>(FXCollections.observableArrayList(Kleur.values()));
            colorSelector.setPromptText("Kies kleur");
            colorSelector.setStyle("-fx-font-family: 'Georgia'; -fx-font-size: 14px;");
            colorSelectors.add(colorSelector);
        }

        startButton = new Button("START");
        startButton.setPrefWidth(100);
        startButton.setPadding(new Insets(10));
        startButton.setStyle("-fx-background-color: #8B8000; -fx-text-fill: white; -fx-padding: 10px;");
        startButton.setFont(Font.font("Georgia", FontWeight.BOLD, 18));
    }

    private void layoutNodes() {
        HBox topBox = new HBox(backButton);
        topBox.setAlignment(Pos.TOP_LEFT);
        topBox.setPadding(new Insets(10));
        topBox.setStyle("-fx-background-color: DARKCYAN;");
        this.setTop(topBox);

        playersBox = new VBox(10);
        playersBox.setAlignment(Pos.TOP_CENTER);
        playersBox.getChildren().addAll(titleLabel, minMaxLabel);

        for (int i = 0; i < 6; i++) {
            HBox playerEntry = new HBox(10);
            playerEntry.setAlignment(Pos.CENTER_LEFT);
            playerEntry.getChildren().addAll(playerFields.get(i), colorSelectors.get(i));
            playersBox.getChildren().add(playerEntry);
        }
        playersBox.setPadding(new Insets(20));
        playersBox.setStyle("-fx-background-color: DARKCYAN;");

        contentBox = new HBox(20);
        contentBox.setAlignment(Pos.CENTER);
        contentBox.getChildren().addAll(playersBox, startButton);
        contentBox.setPadding(new Insets(0, 20, 0, 0));
        contentBox.setStyle("-fx-background-color: DARKCYAN;");

        this.setCenter(contentBox);
    }

    private void applyStyles() {
        // Styling voor input velden
        String inputStyle = "-fx-background-color: white; -fx-text-fill: black;";
        for (TextField field : playerFields) {
            field.setStyle(inputStyle);
        }

        // Styling voor kleur selectors
        String comboBoxStyle = "-fx-background-color: white; -fx-text-fill: black;";
        for (ComboBox<Kleur> selector : colorSelectors) {
            selector.setStyle(comboBoxStyle);
        }
        this.setPrefSize(800, 600);
    }

    public Button getBackButton() {
        return backButton;
    }

    public Button getStartButton() {
        return startButton;
    }

    public List<TextField> getPlayerFields() {
        return playerFields;
    }

    public List<ComboBox<Kleur>> getColorSelectors() {
        return colorSelectors;
    }
}