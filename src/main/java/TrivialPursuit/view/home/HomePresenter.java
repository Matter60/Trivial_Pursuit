package TrivialPursuit.view.home;

import TrivialPursuit.model.TrivialPursuitController;
import TrivialPursuit.view.about.AboutView;
import TrivialPursuit.view.about.AboutPresenter;
import TrivialPursuit.view.leaderboard.LeaderboardPresenter;
import TrivialPursuit.view.leaderboard.LeaderboardView;
import TrivialPursuit.view.make.MakePresenter;
import TrivialPursuit.view.make.MakeView;
import TrivialPursuit.view.help.HelpPresenter;
import TrivialPursuit.view.help.HelpView;
import TrivialPursuit.view.admin.AdminView; // Import AdminView
import TrivialPursuit.view.Question.QuestionView; // Import QuestionView
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.util.Pair;

import java.util.Optional;

public class HomePresenter {
    private TrivialPursuitController model;
    private HomeView view;

    // Define admin credentials
    private static final String ADMIN_USERNAME = "Matter";
    private static final String ADMIN_PASSWORD = "password";

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

        view.getHelpButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                HelpView helpView = new HelpView();
                HelpPresenter helpPresenter = new HelpPresenter(model, helpView);
                helpPresenter.addWindowEventHandlers();
                view.getScene().setRoot(helpView);
                helpView.getScene().getWindow().sizeToScene();
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

        view.getAdminButton().setOnAction(event -> {
            AdminView adminView = new AdminView();
            Optional<Pair<String, String>> result = adminView.showAndWait();
            result.ifPresent(usernamePassword -> {
                // Validate username and password
                if (ADMIN_USERNAME.equals(usernamePassword.getKey()) && ADMIN_PASSWORD.equals(usernamePassword.getValue())) {
                    // On successful login, show QuestionView
                    QuestionView questionView = new QuestionView();
                    view.getScene().setRoot(questionView);
                    questionView.getScene().getWindow().sizeToScene();
                } else {
                    // Handle invalid login (e.g., show an alert)
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid username or password.");
                    alert.showAndWait();
                }
            });
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
