package TrivialPursuit;

import TrivialPursuit.model.TrivialPursuitController;
import TrivialPursuit.view.home.HomePresenter;
import TrivialPursuit.view.home.HomeView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        TrivialPursuitController model = new TrivialPursuitController();
        HomeView view = new HomeView();
        HomePresenter presenter = new HomePresenter(model, view);
        primaryStage.setScene(new Scene(view, 1000, 700));
        primaryStage.setTitle("Trivial-Pursuit");
        presenter.addWindowEventHandlers();
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
