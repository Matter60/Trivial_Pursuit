package TrivialPursuit.view.admin;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class AdminView extends VBox {
    private ComboBox<String> thema;
    private TextField vraagInput;
    private TextField antwoord1;
    private TextField antwoord2;
    private TextField antwoord3;
    private TextField antwoord4;
    private Button submitButton;
    private Button backButton;

    public AdminView() {
        this.setPadding(new Insets(20));
        this.setSpacing(10);

        // Back Button
        backButton = new Button("←");
        backButton.setFont(Font.font("Georgia", FontWeight.BOLD, 24));
        backButton.setPadding(new Insets(10));
        backButton.setStyle("-fx-background-color: #8B8000; -fx-text-fill: white; -fx-padding: 10px;");

        // Title Label
        Label titleLabel = new Label("Maak een vraag");
        titleLabel.setFont(Font.font("Georgia", FontWeight.BOLD, 20));
        titleLabel.setStyle("-fx-text-fill: white;");

        // ComboBox for Thema
        thema = new ComboBox<>();
        thema.setPromptText("Kies een thema");
        thema.setStyle("-fx-font-size: 16px;");

        // Adding themes to the ComboBox
        thema.getItems().addAll(
                "Aardrijkskunde",
                "Amusement",
                "Geschiedenis",
                "Kunst & Literatuur",
                "Wetenschap & Natuur",
                "Sport & Ontspanning"
        );

        // TextFields for input
        vraagInput = new TextField();
        vraagInput.setPromptText("Voer je vraag hier in...");
        vraagInput.setStyle("-fx-font-size: 16px;");

        antwoord1 = new TextField();
        antwoord1.setPromptText("Geef het juiste antwoord");
        antwoord1.setStyle("-fx-font-size: 16px;");

        antwoord2 = new TextField();
        antwoord2.setPromptText("Geef een fout antwoord");
        antwoord2.setStyle("-fx-font-size: 16px;");

        antwoord3 = new TextField();
        antwoord3.setPromptText("Geef een fout antwoord");
        antwoord3.setStyle("-fx-font-size: 16px;");

        antwoord4 = new TextField();
        antwoord4.setPromptText("Geef een fout antwoord");
        antwoord4.setStyle("-fx-font-size: 16px;");

        // Submit Button
        submitButton = new Button("Indienen");
        submitButton.setStyle("-fx-background-color: #8B8000; -fx-text-fill: white; -fx-padding: 10px;");

        // GridPane for layout
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setVgap(8);
        grid.setHgap(10);

        grid.add(backButton, 0, 0);
        grid.add(titleLabel, 0, 0, 2, 1);
        GridPane.setHalignment(titleLabel, HPos.CENTER);
        grid.add(new Label("Thema:"), 0, 1);
        grid.add(thema, 1, 1);
        grid.add(new Label("Vraag:"), 0, 2);
        grid.add(vraagInput, 1, 2);
        grid.add(new Label("Juiste antwoord:"), 0, 3);
        grid.add(antwoord1, 1, 3);
        grid.add(new Label("Fout antwoord 1:"), 0, 4);
        grid.add(antwoord2, 1, 4);
        grid.add(new Label("Fout antwoord 2:"), 0, 5);
        grid.add(antwoord3, 1, 5);
        grid.add(new Label("Fout antwoord 3:"), 0, 6);
        grid.add(antwoord4, 1, 6);
        grid.add(submitButton, 1, 7);

        // Add GridPane to VBox
        this.getChildren().add(grid);
        this.setStyle("-fx-background-color: DARKCYAN;");
    }

    public ComboBox<String> getThema() {
        return thema;
    }

    public TextField getVraagInput() {
        return vraagInput;
    }

    public TextField getAntwoord1() {
        return antwoord1;
    }

    public TextField getAntwoord2() {
        return antwoord2;
    }

    public TextField getAntwoord3() {
        return antwoord3;
    }

    public TextField getAntwoord4() {
        return antwoord4;
    }

    public Button getSubmitButton() {
        return submitButton;
    }

    public Button getBackButton() {
        return backButton;
    }
}
