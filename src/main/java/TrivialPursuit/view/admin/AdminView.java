package TrivialPursuit.view.admin;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class AdminView extends VBox {
    private ComboBox<String> thema;
    private TextField vraagInput;
    private TextField antwoord1;
    private TextField antwoord2;
    private TextField antwoord3;
    private TextField antwoord4;
    private Button submitButton;

    public AdminView() {
        this.setPadding(new Insets(20));
        this.setSpacing(10);

        Label titleLabel = new Label("Maak een vraag");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        thema = new ComboBox<>();
        thema.setPromptText("Kies een thema");

        // Adding themes to the ComboBox
        thema.getItems().addAll(
                "Aardrijkskunde",
                "Amusement",
                "Geschiedenis",
                "Kunst & Literatuur",
                "Wetenschap & Natuur",
                "Sport & Ontspanning"
        );

        vraagInput = new TextField();
        vraagInput.setPromptText("Voer je vraag hier in...");

        antwoord1 = new TextField();
        antwoord1.setPromptText("Geef het juiste antwoord");

        antwoord2 = new TextField();
        antwoord2.setPromptText("Geef een fout antwoord");

        antwoord3 = new TextField();
        antwoord3.setPromptText("Geef een fout antwoord");

        antwoord4 = new TextField();
        antwoord4.setPromptText("Geef een fout antwoord");

        submitButton = new Button("Indienen");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setVgap(8);
        grid.setHgap(10);

        grid.add(titleLabel, 0, 0, 2, 1);
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

        this.getChildren().add(grid);
    }

//    private void handleSubmit() {
//        String question = questionInput.getText();
//        String answer1 = antwoord1.getText();
//        String answer2 = antwoord2.getText();
//        String answer3 = antwoord3.getText();
//        String answer4 = antwoord4.getText();
//        String selectedTheme = thema.getValue();


//        questionInput.clear();
//        antwoord1.clear();
//        antwoord2.clear();
//        antwoord3.clear();
//        antwoord4.clear();
//        thema.setValue(null);
//    }


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
}
