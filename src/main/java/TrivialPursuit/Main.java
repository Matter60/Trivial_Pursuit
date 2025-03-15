package TrivialPursuit;

import TrivialPursuit.model.TrivialPursuitController;
import TrivialPursuit.view.home.HomePresenter;
import TrivialPursuit.view.home.HomeView;
import TrivialPursuit.view.splash.SplashView;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class Main extends Application {

    private Stage primaryStage;
    private TrivialPursuitController model;

    public void splashScreen() {

        Stage splashStage = new Stage();
        splashStage.initStyle(StageStyle.UNDECORATED);

        SplashView splashView = new SplashView();
        Scene splashScene = new Scene(splashView);

        splashStage.setScene(splashScene);
        splashStage.centerOnScreen();
        splashStage.setAlwaysOnTop(true);
        splashStage.show();

        PauseTransition pause = new PauseTransition(Duration.seconds(3));
        pause.setOnFinished(event -> {

            splashStage.close();

            showMainStage();
        });
        pause.play();
    }

    private void showMainStage() {
        // Maak de hoofdview en presenter
        HomeView view = new HomeView();
        HomePresenter presenter = new HomePresenter(model, view);

        // Stel de scene in en toon de hoofdapplicatie
        primaryStage.setScene(new Scene(view, 1000, 700));
        primaryStage.setTitle("Trivial-Pursuit");
        presenter.addWindowEventHandlers();
        primaryStage.show();
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.model = new TrivialPursuitController();

        splashScreen();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
