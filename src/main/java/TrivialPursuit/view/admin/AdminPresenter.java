package TrivialPursuit.view.admin;

import TrivialPursuit.model.TrivialPursuitController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class AdminPresenter {
    private TrivialPursuitController model;
    private AdminView view;

    public AdminPresenter(TrivialPursuitController model, AdminView view) {
        this.model = model;
        this.view = view;
        this.addEventHandlers();
        this.updateView();
    }

    private void updateView() {

    }

    private void addEventHandlers() {

            view.getSubmitButton().setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {

                    System.out.println("System geklikt");
                    System.out.println(view.getThema().getValue());
                    System.out.println(view.getAntwoord1().getText());
                    System.out.println(view.getAntwoord2().getText());
                    System.out.println(view.getAntwoord3().getText());
                    System.out.println(view.getAntwoord4().getText());
                    System.out.println(view.getVraagInput().getText());
                }
        });
    }

    public void addWindowEventHandlers() {
    }


}
