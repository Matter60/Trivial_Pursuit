package TrivialPursuit.view.admin;

import TrivialPursuit.model.TrivialPursuitController;
import TrivialPursuit.model.Vraag;
import TrivialPursuit.model.Kleur;
import TrivialPursuit.view.home.HomePresenter;
import TrivialPursuit.view.home.HomeView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.util.List;

public class AdminPresenter {
    private TrivialPursuitController model;
    private AdminView view;

    public AdminPresenter(TrivialPursuitController model, AdminView view) {
        this.model = model;
        this.view = view;
        this.addEventHandlers();  // This will add the handlers for the submit and back buttons
        this.updateView();
    }

    private void updateView() {
        // Optional: Update the view after adding a question
    }

    // Method to safely convert theme string to Kleur enum
    private Kleur getCategorieFromString(String thema) {
        String themaFormatted = thema.toUpperCase().replace(" & ", "_").replace(" ", "_");

        for (Kleur kleur : Kleur.values()) {
            if (kleur.getDescription().toUpperCase().replace(" & ", "_").replace(" ", "_").equals(themaFormatted)) {
                return kleur; // Return matching Kleur
            }
        }

        // If no match, return a default color or handle the error
        System.out.println("Invalid theme: " + thema);
        return Kleur.BLUE; // Default to BLUE or choose another fallback color
    }

    private void addVraag() {
        String vraagTekst = view.getVraagInput().getText();
        List<String> antwoorden = List.of(
                view.getAntwoord1().getText(),
                view.getAntwoord2().getText(),
                view.getAntwoord3().getText(),
                view.getAntwoord4().getText()
        );
        int juisteAntwoordIndex = 0; // Answer 1 is always correct
        String thema = view.getThema().getValue();

        // Get the corresponding Kleur from the theme
        Kleur categorie = getCategorieFromString(thema);

        // Add the question to the model
        Vraag nieuweVraag = new Vraag(vraagTekst, antwoorden, juisteAntwoordIndex, categorie);
        model.addVraag(nieuweVraag);

        // Optionally: Clear the fields after submission
        view.getVraagInput().clear();
        view.getAntwoord1().clear();
        view.getAntwoord2().clear();
        view.getAntwoord3().clear();
        view.getAntwoord4().clear();
        view.getThema().setValue(null);
    }

    // Only one addEventHandlers method to handle button actions
    private void addEventHandlers() {
        view.getSubmitButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                addVraag();
            }
        });

        view.getBackButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                HomeView homeView = new HomeView();
                HomePresenter homePresenter = new HomePresenter(model, homeView);
                homePresenter.addWindowEventHandlers();
                view.getScene().setRoot(homeView);
                homeView.getScene().getWindow().sizeToScene();
            }
        });
    }

    public void addWindowEventHandlers() {
        // Optional: Handlers for window events
    }
}
