package TrivialPursuit.view.oefenen;

import TrivialPursuit.model.Kleur;
import TrivialPursuit.model.TrivialPursuitController;
import TrivialPursuit.model.Vraag;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Alert.AlertType;

import java.util.List;
import java.util.Random;

public class OefenenPresenter {

    private OefenenView view;
    private TrivialPursuitController controller;
    private Vraag huidigeVraag;

    // Constructor
    public OefenenPresenter(TrivialPursuitController controller, OefenenView view) {
        this.view = view;
        this.controller = controller;

        // Load and show the first question when the presenter is initialized
        startOefenen();  // No category passed, will randomize it
    }

    // Starts the oefenmodus with a random category and question
    public void startOefenen() {
        // Get a random category from the Kleur enum
        Kleur randomCategory = getRandomCategory();

        // Load the questions for the random category
        List<Vraag> vragen = controller.laadVraag(randomCategory);
        if (!vragen.isEmpty()) {
            // Get the first question from the loaded list
            huidigeVraag = vragen.get(0);
            List<String> answers = huidigeVraag.getMogelijkeAntwoorden();
            view.showQuestion(huidigeVraag.getVraag(), answers);
        } else {
            // If no questions are available, show an error
            showError("Geen vragen beschikbaar in deze categorie.");
        }
    }

    // Gets a random category from the Kleur enum
    private Kleur getRandomCategory() {
        Kleur[] categories = Kleur.values();  // Get all categories from the Kleur enum
        Random random = new Random();
        return categories[random.nextInt(categories.length)];  // Select a random category
    }

    // Handles the selected answer when the user clicks the "Beantwoord" button
    public void handleAnswer() {
        if (huidigeVraag != null) {
            // Get the selected radio button (answer)
            RadioButton selectedButton = (RadioButton) view.getAnswerGroup().getSelectedToggle();
            if (selectedButton != null) {
                String selectedAnswer = selectedButton.getText();
                int selectedIndex = view.getAnswerButtons().indexOf(selectedButton);

                // Check if the selected answer is correct
                boolean correct = huidigeVraag.checkAntwoord(selectedIndex);

                // Show the result in a popup (alert box)
                showResult(correct);
            }
        }
    }

    // Show a result in a popup
    private void showResult(boolean correct) {
        Alert alert = new Alert(correct ? AlertType.INFORMATION : AlertType.ERROR);
        alert.setTitle("Resultaat");
        alert.setHeaderText(correct ? "Correct!" : "Fout!");
        alert.setContentText(correct ? "Goed gedaan!" : "Het juiste antwoord is: " + huidigeVraag.getJuisteAntwoord());
        alert.showAndWait();
    }

    // Show an error message
    private void showError(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Fout bij het laden van vragen");
        alert.setContentText(message);
        alert.showAndWait();
    }

    // This method will be used to add event handlers for any window events or actions if needed
    public void addWindowEventHandlers() {
        // You can add additional event handlers if required
    }
}
