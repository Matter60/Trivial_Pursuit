package TrivialPursuit.view.home;

import TrivialPursuit.model.TrivialPursuitController;
import TrivialPursuit.view.about.AboutView;
import TrivialPursuit.view.about.AboutPresenter;
import TrivialPursuit.view.leaderboard.LeaderboardPresenter;
import TrivialPursuit.view.leaderboard.LeaderboardView;
import TrivialPursuit.view.make.MakePresenter;
import TrivialPursuit.view.make.MakeView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HomePresenter {
    private TrivialPursuitController model;
    private HomeView view;

    public HomePresenter(
            TrivialPursuitController model,
            HomeView view) {
        this.model = model;
        this.view = view;
        this.addEventHandlers();
        this.updateView();
    }

    public void addEventHandlers() {
        view.getAboutButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                AboutView aboutView = new AboutView();
                AboutPresenter aboutPresenter = new AboutPresenter(model, aboutView);
                aboutPresenter.addWindowEventHandlers();
                view.getScene().setRoot(aboutView);
                aboutView.getScene().getWindow().sizeToScene();
            }
        });

        view.getStartButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                MakeView makeView = new MakeView();
                MakePresenter makePresenter = new MakePresenter(model, makeView);
                makePresenter.addWindowEventHandlers();
                view.getScene().setRoot(makeView);
                makeView.getScene().getWindow().sizeToScene();
            }
        });

        view.getLeaderboardBox().setOnMouseClicked(event -> {
            LeaderboardView leaderboardView = new LeaderboardView();
            LeaderboardPresenter leaderboardPresenter = new LeaderboardPresenter(model, leaderboardView);
            leaderboardPresenter.addWindowEventHandlers();
            view.getScene().setRoot(leaderboardView);
            leaderboardView.getScene().getWindow().sizeToScene();
        });

        view.sceneProperty().addListener((observable, oldScene, newScene) -> {
            if (newScene != null) {
                updateLeaderboard();
                
                newScene.windowProperty().addListener((obs, oldWindow, newWindow) -> {
                    if (newWindow != null) {
                        newWindow.focusedProperty().addListener((prop, wasFocused, isFocused) -> {
                            if (isFocused) {
                                updateLeaderboard();
                            }
                        });
                    }
                });
            }
        });
    }

    private void updateView() {
        // Vult de view met data uit model
    }

    public void addWindowEventHandlers() {
    }

    private void updateLeaderboard() {
        view.getLeaderboard().getItems().setAll(model.getScores());
    }
}