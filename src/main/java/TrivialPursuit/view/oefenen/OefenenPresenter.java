package TrivialPursuit.view.oefenen;

import TrivialPursuit.model.Kleur;
import TrivialPursuit.model.TrivialPursuitController;
import TrivialPursuit.model.Vraag;
import TrivialPursuit.view.home.HomePresenter;
import TrivialPursuit.view.home.HomeView;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Alert.AlertType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class OefenenPresenter {

    private OefenenView view;
    private Vraag huidigeVraag;
    private TrivialPursuitController model;

    // Constructor
    public OefenenPresenter(TrivialPursuitController model, OefenenView view) {
        this.view = view;
        this.view.getAnswerButton().setOnAction(event -> handleAnswer());
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

    public void startOefenen() {
        System.out.println("Beschikbare categorieën:");
        for (Kleur kleur : Kleur.values()) {
            System.out.println("- " + kleur);
        }

        Kleur randomCategory = getRandomCategory(); // Kies een willekeurige categorie
        System.out.println("Gekozen categorie: " + randomCategory);

        // Laad de vragen voor de geselecteerde categorie
        List<Vraag> vragen = model.laadVraag(randomCategory);
        System.out.println("Aantal vragen geladen in categorie '" + randomCategory + "': " + vragen.size());

        if (!vragen.isEmpty()) {
            // Kies een willekeurige vraag uit de geladen vragen
            huidigeVraag = vragen.get(new Random().nextInt(vragen.size()));
            System.out.println("Gekozen vraag: " + huidigeVraag.getVraag());

            // Print de mogelijke antwoorden voor de vraag
            List<String> answers = huidigeVraag.getMogelijkeAntwoorden();
            for (int i = 0; i < answers.size(); i++) {
                System.out.println("Antwoord " + (i + 1) + ": " + answers.get(i));
            }

            // Toon de vraag en antwoorden in de view
            view.showQuestion(huidigeVraag.getVraag(), answers);
        } else {
            // Als er geen vragen zijn, toon een foutmelding
            showError("Geen vragen beschikbaar in deze categorie: " + randomCategory);
        }
    }

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
    public void handleAnswer() {
        if (huidigeVraag != null) {
            RadioButton selectedButton = (RadioButton) view.getAnswerGroup().getSelectedToggle();
            if (selectedButton != null) {
                int selectedIndex = view.getAnswerButtons().indexOf(selectedButton);
                boolean correct = huidigeVraag.checkAntwoord(selectedIndex);
                showResult(correct);
            }
        }
    }

    // Toont een pop-up met het resultaat en laadt daarna een nieuwe categorie en vraag
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
