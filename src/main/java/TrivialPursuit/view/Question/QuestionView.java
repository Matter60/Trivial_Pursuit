package TrivialPursuit.view.Question;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class QuestionView extends VBox {
    private ComboBox<String> thema; // Changed to generic type for better type safety
    private TextField questionInput;
    private TextField antwoord1;
    private TextField antwoord2;
    private TextField antwoord3;
    private TextField antwoord4;
    private Button submitButton;

    public QuestionView() {
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

        questionInput = new TextField();
        questionInput.setPromptText("Voer je vraag hier in...");

        antwoord1 = new TextField();
        antwoord1.setPromptText("Geef het juiste antwoord");

        antwoord2 = new TextField();
        antwoord2.setPromptText("Geef een fout antwoord");

        antwoord3 = new TextField();
        antwoord3.setPromptText("Geef een fout antwoord");

        antwoord4 = new TextField();
        antwoord4.setPromptText("Geef een fout antwoord");

        submitButton = new Button("Indienen");
        submitButton.setOnAction(e -> handleSubmit());

        // Layout setup
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setVgap(8);
        grid.setHgap(10);

        grid.add(titleLabel, 0, 0, 2, 1);
        grid.add(new Label("Thema:"), 0, 1);
        grid.add(thema, 1, 1);
        grid.add(new Label("Vraag:"), 0, 2);
        grid.add(questionInput, 1, 2);
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

    private void handleSubmit() {
        String question = questionInput.getText();
        String answer1 = antwoord1.getText();
        String answer2 = antwoord2.getText();
        String answer3 = antwoord3.getText();
        String answer4 = antwoord4.getText();
        String selectedTheme = thema.getValue();

        // Add logic here to handle the submitted question (e.g., save to model)
        System.out.println("Submitted question: " + question);
        System.out.println("Correct answer: " + answer1);
        System.out.println("Wrong answer 1: " + answer2);
        System.out.println("Wrong answer 2: " + answer3);
        System.out.println("Wrong answer 3: " + answer4);
        System.out.println("Selected theme: " + selectedTheme);

        // Clear the input boxes after submission
        questionInput.clear();
        antwoord1.clear();
        antwoord2.clear();
        antwoord3.clear();
        antwoord4.clear();
        thema.setValue(null); // Reset the combo box
    }
}
