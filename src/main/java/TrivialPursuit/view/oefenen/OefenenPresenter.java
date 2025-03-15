package TrivialPursuit.view.oefenen;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import TrivialPursuit.model.Kleur;
import TrivialPursuit.model.TrivialPursuitController;
import TrivialPursuit.model.Vraag;
import TrivialPursuit.view.home.HomePresenter;
import TrivialPursuit.view.home.HomeView;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.RadioButton;

public class OefenenPresenter {

    private OefenenView view;
    private Vraag huidigeVraag;
    private TrivialPursuitController model;

    // Constructor
    public OefenenPresenter(TrivialPursuitController model, OefenenView view) {
        this.view = view;
        this.view.getAntwoordButton().setOnAction(event -> handleAnswer());
        this.model = model;

        view.getBackButton().setOnAction(event -> {
            HomeView homeView = new HomeView();
            HomePresenter homePresenter = new HomePresenter(model, homeView);
            homePresenter.addWindowEventHandlers();
            view.getScene().setRoot(homeView);
            homeView.getScene().getWindow().sizeToScene();
        });

        startOefenen();
    }

    private void showQuestion(String question, List<String> answers) {
        List<String> shuffledAnswers = new ArrayList<>(answers);
        Collections.shuffle(shuffledAnswers);

        view.setQuestionText(question);

        // Zet de antwoorden op de knoppen
        for (int i = 0; i < shuffledAnswers.size() && i < view.getAntwoordButtons().size(); i++) {
            view.setAnswerButtonText(i, shuffledAnswers.get(i));
            view.setAnswerButtonVisible(i, true);
        }

        // Verberg de overige antwoordknoppen
        for (int i = shuffledAnswers.size(); i < view.getAntwoordButtons().size(); i++) {
            view.setAnswerButtonVisible(i, false);
        }

        view.setAnswerButtonVisible(true);
        view.setVraagBoxVisible(true);
    }

    public void startOefenen() {
       // System.out.println("Beschikbare categorieën:");
      //  for (Kleur kleur : Kleur.values()) {
        //    System.out.println("- " + kleur);
       // }

        Kleur randomCategory = getRandomCategory(); // Kies een willekeurige categorie

        // Laad de vragen voor de geselecteerde categorie
        List<Vraag> vragen = model.laadVraag(randomCategory);

        if (!vragen.isEmpty()) {
            // Kies een willekeurige vraag uit de geladen vragen
            huidigeVraag = vragen.get(new Random().nextInt(vragen.size()));

            // Toon de vraag en antwoorden in de view
            showQuestion(huidigeVraag.getVraag(), huidigeVraag.getMogelijkeAntwoorden());
        } else {
            // Als er geen vragen zijn, toon een foutmelding
            showError("Geen vragen beschikbaar in deze categorie: " + randomCategory);
        }
    }

    // Kiest een willekeurige categorie, behalve de categorie WIT
    private Kleur getRandomCategory() {
        Kleur[] categories = Kleur.values();
        List<Kleur> validCategories = new ArrayList<>();
        for (Kleur category : categories) {
            if (!category.name().equals("WIT")) {
                validCategories.add(category);
            }
        }
        Random random = new Random();
        int randomIndex = random.nextInt(validCategories.size());
        return validCategories.get(randomIndex);
    }

    // Controleert of het gegeven antwoord correct is en toont het resultaat
    public void handleAnswer() {
        if (huidigeVraag != null) {
            RadioButton selectedButton = (RadioButton) view.getAntwoorGroep().getSelectedToggle();
            if (selectedButton != null) {
                String selectedAnswer = selectedButton.getText();
                boolean correct = selectedAnswer.equals(huidigeVraag.getJuisteAntwoord());
                showResult(correct);
            }
        }
    }

    // Toont een pop-up met het resultaat en laadt daarna een nieuwe categorie en
    // vraag
    private void showResult(boolean correct) {
        Alert alert = new Alert(correct ? AlertType.INFORMATION : AlertType.ERROR);
        alert.setTitle("Resultaat");
        alert.setHeaderText(correct ? "Correct!" : "Fout!");
        alert.setContentText(correct ? "Goed gedaan!" : "Het juiste antwoord is: " + huidigeVraag.getJuisteAntwoord());

        alert.showAndWait(); // Wacht tot de gebruiker de pop-up sluit
        startOefenen(); // Laad een nieuwe categorie en vraag
    }

    // Toont een foutmelding
    private void showError(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Fout bij het laden van vragen");
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void addWindowEventHandlers() {
    }
}
