package TrivialPursuit.view.oefenen;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import TrivialPursuit.model.Vraag;

public class OefenenView extends BorderPane {
    private Label vraagLabel;
    private VBox antwoordBox;
    private ToggleGroup antwoordGroup;
    private Button checkAnswerButton;
    private Label resultaatLabel;

    public OefenenView() {
        vraagLabel = new Label("Vraag hier");
        vraagLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        antwoordBox = new VBox(10);
        antwoordGroup = new ToggleGroup();
        checkAnswerButton = new Button("Controleer Antwoord");
        resultaatLabel = new Label("");

        // Voeg de elementen toe aan de layout
        VBox centerBox = new VBox(20, vraagLabel, antwoordBox, checkAnswerButton, resultaatLabel);
        centerBox.setStyle("-fx-alignment: center;");
        this.setCenter(centerBox);
    }

    // Toon de vraag en de mogelijke antwoorden
    public void showQuestion(String vraag, String[] antwoorden) {
        vraagLabel.setText(vraag);
        antwoordBox.getChildren().clear(); // Reset de antwoordbox

        // Voeg de radio buttons toe voor de mogelijke antwoorden
        for (String antwoord : antwoorden) {
            RadioButton rb = new RadioButton(antwoord);
            rb.setToggleGroup(antwoordGroup);
            antwoordBox.getChildren().add(rb);
        }
    }

    // Toon het resultaat (juist/fout)
    public void showResult(boolean correct, String juisteAntwoord) {
        if (correct) {
            resultaatLabel.setText("Correct!");
            resultaatLabel.setTextFill(Color.GREEN);
        } else {
            resultaatLabel.setText("Fout! Het juiste antwoord is: " + juisteAntwoord);
            resultaatLabel.setTextFill(Color.RED);
        }
    }

    // Getter voor de check answer button
    public Button getCheckAnswerButton() {
        return checkAnswerButton;
    }

    // Getter voor de geselecteerde antwoordgroep
    public ToggleGroup getAnswerGroup() {
        return antwoordGroup;
    }
}
