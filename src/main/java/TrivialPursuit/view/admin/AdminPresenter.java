package TrivialPursuit.view.admin;

import TrivialPursuit.model.TrivialPursuitController;
import TrivialPursuit.model.Vraag;
import TrivialPursuit.model.Kleur;
import TrivialPursuit.view.home.HomePresenter;
import TrivialPursuit.view.home.HomeView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;

import java.util.List;

public class AdminPresenter {
    private TrivialPursuitController model;
    private AdminView view;

    public AdminPresenter(TrivialPursuitController model, AdminView view) {
        this.model = model;
        this.view = view;
        this.addEventHandlers();
    }

    private void addEventHandlers() {

        view.getSubmitButton().setOnAction(event -> {
            // Valideer en verzamel alle input
            String vraagTekst = view.getVraagInput().getText();
            String antwoord1 = view.getAntwoord1().getText();
            String antwoord2 = view.getAntwoord2().getText();
            String antwoord3 = view.getAntwoord3().getText();
            String antwoord4 = view.getAntwoord4().getText();
            String thema = view.getThema().getValue();

            if (isValidInput(vraagTekst, antwoord1, antwoord2, antwoord3, antwoord4, thema)) {

                Vraag nieuweVraag = new Vraag(
                        vraagTekst,
                        List.of(antwoord1, antwoord2, antwoord3, antwoord4),
                        0, // eerste antwoord is altijd correct
                        Kleur.fromThema(thema));

                model.addVraag(nieuweVraag);
                toonMelding("Succes","De vraag is succesvol toegevoegd");
                clearInputFields();

            } else {
                toonMelding("Fout","Alle velden moeten ingevuld zijn");
            }

        });

        view.getBackButton().setOnAction(event -> {
            HomeView homeView = new HomeView();
            HomePresenter homePresenter = new HomePresenter(model, homeView);
            homePresenter.addWindowEventHandlers();
            view.getScene().setRoot(homeView);
            homeView.getScene().getWindow().sizeToScene();
        });
    }

    public void addWindowEventHandlers() {
    }

    private boolean isValidInput(String vraag, String ant1, String ant2, String ant3, String ant4, String thema) {
        return !vraag.isEmpty() && !ant1.isEmpty() && !ant2.isEmpty()
                && !ant3.isEmpty() && !ant4.isEmpty() && thema != null;
    }

    private void clearInputFields() {
        view.getVraagInput().clear();
        view.getAntwoord1().clear();
        view.getAntwoord2().clear();
        view.getAntwoord3().clear();
        view.getAntwoord4().clear();
        view.getThema().setValue(null);
    }

    public void toonMelding(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

}
