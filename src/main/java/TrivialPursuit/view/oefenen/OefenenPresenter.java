package TrivialPursuit.view.oefenen;

import TrivialPursuit.model.Vraag;
import javafx.scene.control.RadioButton;

public class OefenenPresenter {
    private OefenenView view;
    private Vraag huidigeVraag;

    // Constructor
    public OefenenPresenter(OefenenView view) {
        this.view = view;

        // Zorg ervoor dat de knop voor het controleren van het antwoord goed werkt
        this.view.getCheckAnswerButton().setOnAction(event -> handleAnswer());
    }

    // Start oefenmodus met een vraag
    public void startOefenen(Vraag vraag) {
        this.huidigeVraag = vraag;

        // Zet de List van mogelijke antwoorden om naar een String[]
        String[] antwoordenArray = vraag.getMogelijkeAntwoorden().toArray(new String[0]);

        // Toon de vraag en antwoorden in de view
        view.showQuestion(vraag.getVraag(), antwoordenArray);
    }

    // Verwerk het antwoord van de speler
    private void handleAnswer() {
        if (huidigeVraag == null) return;

        // Kijk welk antwoord is geselecteerd
        RadioButton selectedButton = (RadioButton) view.getAnswerGroup().getSelectedToggle();
        if (selectedButton == null) return;

        String selectedAnswer = selectedButton.getText();

        // Controleer of het antwoord correct is
        // Controleer of de geselecteerde tekst overeenkomt met het juiste antwoord als een string
        boolean correct = huidigeVraag.checkAntwoord(Integer.parseInt(selectedAnswer));

        // Toon het resultaat aan de gebruiker
        view.showResult(correct, huidigeVraag.getJuisteAntwoord());
    }

    public void addWindowEventHandlers() {
        // Optional: Event handlers for window actions (if needed)
    }
}
