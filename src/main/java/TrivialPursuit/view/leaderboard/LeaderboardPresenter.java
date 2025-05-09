package TrivialPursuit.view.leaderboard;

import TrivialPursuit.model.TrivialPursuitController;
import TrivialPursuit.view.home.HomePresenter;
import TrivialPursuit.view.home.HomeView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class LeaderboardPresenter {
    private TrivialPursuitController model;
    private LeaderboardView view;

    public LeaderboardPresenter(
            TrivialPursuitController model, LeaderboardView view) {
        this.model = model;
        this.view = view;
        this.addEventHandlers();
        this.updateView();
        this.updateLeaderboard(); // Update de leaderboard bij creatie
    }

    private void addEventHandlers() {
        view.getBackButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                HomeView homeView = new HomeView();
                HomePresenter homePresenter = new HomePresenter(model, homeView);
                homePresenter.addWindowEventHandlers();
                view.getScene().setRoot(homeView);
                homeView.getScene().getWindow().sizeToScene();
                homePresenter.updateLeaderboard();
            }
        });
    }

    private void updateView() {
        // Vult de view met data uit model
    }

    public void addWindowEventHandlers() {
        // Window event handlers (anon. inner klassen)
        // Koppeling via view.getScene().getWindow()
    }

    private void updateLeaderboard() {
        view.getLeaderboard().getItems().setAll(model.getScores());
    }
}