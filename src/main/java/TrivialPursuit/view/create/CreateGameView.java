package TrivialPursuit.view.create;

import java.util.ArrayList;
import java.util.List;

import TrivialPursuit.model.Kleur;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class CreateGameView extends BorderPane {

    private Button backButton;
    private Button startButton;
    private Label titelLabel;
    private Label minMaxLabel;
    private List<TextField> spelerVelden;
    private List<ComboBox<Kleur>> kleurSelectors;
    private VBox spelersBox;
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

        titelLabel = new Label("Voeg spelers toe");
        titelLabel.setFont(Font.font("Georgia", FontWeight.BOLD, 24));
        titelLabel.setUnderline(true);
        titelLabel.setStyle("-fx-text-fill: white;");

        minMaxLabel = new Label("Minimaal 2, maximaal 6 spelers");
        minMaxLabel.setFont(Font.font("Georgia", FontWeight.NORMAL, 14));
        minMaxLabel.setStyle("-fx-text-fill: white;");

        spelerVelden = new ArrayList<>();
        kleurSelectors = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            TextField spelerVeld = new TextField();
            spelerVeld.setPromptText("Speler " + (i + 1));
            spelerVeld.setPrefWidth(150);
            spelerVeld.setFont(Font.font("Georgia", FontWeight.NORMAL, 14));
            spelerVelden.add(spelerVeld);

            ComboBox<Kleur> kleurSelector = new ComboBox<>(FXCollections.observableArrayList(Kleur.values()));
            kleurSelector.setPromptText("Kies kleur");
            kleurSelector.setStyle("-fx-font-family: 'Georgia'; -fx-font-size: 14px;");
            kleurSelectors.add(kleurSelector);
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

        spelersBox = new VBox(10);
        spelersBox.setAlignment(Pos.TOP_CENTER);
        spelersBox.getChildren().addAll(titelLabel, minMaxLabel);

        for (int i = 0; i < 6; i++) {
            HBox spelerEntry = new HBox(10);
            spelerEntry.setAlignment(Pos.CENTER_LEFT);
            spelerEntry.getChildren().addAll(spelerVelden.get(i), kleurSelectors.get(i));
            spelersBox.getChildren().add(spelerEntry);
        }
        spelersBox.setPadding(new Insets(20));
        spelersBox.setStyle("-fx-background-color: DARKCYAN;");

        contentBox = new HBox(20);
        contentBox.setAlignment(Pos.CENTER);
        contentBox.getChildren().addAll(spelersBox, startButton);
        contentBox.setPadding(new Insets(0, 20, 0, 0));
        contentBox.setStyle("-fx-background-color: DARKCYAN;");

        this.setCenter(contentBox);
    }

    private void applyStyles() {
        // Styling voor input velden
        String inputStyle = "-fx-background-color: white; -fx-text-fill: black;";
        for (TextField veld : spelerVelden) {
            veld.setStyle(inputStyle);
        }

        // Styling voor kleur selectors
        String comboBoxStyle = "-fx-background-color: white; -fx-text-fill: black;";
        for (ComboBox<Kleur> selector : kleurSelectors) {
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

    public List<TextField> getSpelerVelden() {
        return spelerVelden;
    }

    public List<ComboBox<Kleur>> getKleurSelectors() {
        return kleurSelectors;
    }
}
