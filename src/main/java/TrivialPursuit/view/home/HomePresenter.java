package TrivialPursuit.view.home;

import TrivialPursuit.model.TrivialPursuitController;
import TrivialPursuit.view.about.AboutView;
import TrivialPursuit.view.about.AboutPresenter;
import TrivialPursuit.view.admin.AdminPresenter;
import TrivialPursuit.view.leaderboard.LeaderboardPresenter;
import TrivialPursuit.view.leaderboard.LeaderboardView;
import TrivialPursuit.view.make.MakePresenter;
import TrivialPursuit.view.make.MakeView;
import TrivialPursuit.view.help.HelpPresenter;
import TrivialPursuit.view.help.HelpView;
import TrivialPursuit.view.admin.AdminPopup; // Import AdminView
import TrivialPursuit.view.admin.AdminView; // Import QuestionView
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.util.Pair;

import java.util.Optional;

public class HomePresenter {
    private TrivialPursuitController model;
    private HomeView view;

    // ADMIN CREDS NIET VEILIG
    private static final String ADMIN_USERNAME = "Matter";
    private static final String ADMIN_PASSWORD = "123";

    public HomePresenter(
            TrivialPursuitController model,
            HomeView view) {
        this.model = model;
        this.view = view;
        this.addEventHandlers();
        this.updateView();
        updateLeaderboard(); // Update de leaderboard bij creatie
    }

    public void addEventHandlers() {
        view.getAboutButton().setOnAction(event -> {
            AboutView aboutView = new AboutView();
            AboutPresenter aboutPresenter = new AboutPresenter(model, aboutView);
            aboutPresenter.addWindowEventHandlers();
            view.getScene().setRoot(aboutView);
            aboutView.getScene().getWindow().sizeToScene();
        });

        view.getHelpButton().setOnAction(event -> {
            HelpView helpView = new HelpView();
            HelpPresenter helpPresenter = new HelpPresenter(model, helpView);
            helpPresenter.addWindowEventHandlers();
            view.getScene().setRoot(helpView);
            helpView.getScene().getWindow().sizeToScene();
        });

        view.getStartButton().setOnAction(event -> {
            MakeView makeView = new MakeView();
            MakePresenter makePresenter = new MakePresenter(model, makeView);
            makePresenter.addWindowEventHandlers();
            view.getScene().setRoot(makeView);
            makeView.getScene().getWindow().sizeToScene();
        });

        view.getLeaderboardBox().setOnMouseClicked(event -> {
            LeaderboardView leaderboardView = new LeaderboardView();
            LeaderboardPresenter leaderboardPresenter = new LeaderboardPresenter(model, leaderboardView);
            leaderboardPresenter.addWindowEventHandlers();
            view.getScene().setRoot(leaderboardView);
            leaderboardView.getScene().getWindow().sizeToScene();
        });

        view.getAdminMenuItem().setOnAction(event -> {
            AdminPopup adminPopup = new AdminPopup();
            Optional<Pair<String, String>> result = adminPopup.showAndWait();
            result.ifPresent(usernamePassword -> {
                if (ADMIN_USERNAME.equals(usernamePassword.getKey())
                        && ADMIN_PASSWORD.equals(usernamePassword.getValue())) {
                    AdminView adminView = new AdminView();
                    AdminPresenter adminPresenter = new AdminPresenter(model, adminView);
                    adminPresenter.addWindowEventHandlers();
                    view.getScene().setRoot(adminView);
                    adminView.getScene().getWindow().sizeToScene();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid username or password.");
                    alert.showAndWait();
                }
            });
        });

    }

    private void updateView() {
    }

    public void addWindowEventHandlers() {
    }

    public void updateLeaderboard() {
        view.getLeaderboard().getItems().setAll(model.getScores());
    }
}
