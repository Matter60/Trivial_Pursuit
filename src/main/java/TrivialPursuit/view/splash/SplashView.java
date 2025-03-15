package TrivialPursuit.view.splash;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class SplashView extends StackPane {

    private VBox content;
    private Label titel;
    private Label loading;

    public SplashView() {
        this.initialiseNodes();
        this.layoutNodes();
        this.applyStyles();
    }

    private void initialiseNodes() {
        // Initialiseer de nodes
        this.setPrefSize(600, 400);

        content = new VBox(20);
        content.setAlignment(Pos.CENTER);


        titel = new Label("Trivial Pursuit");
        titel.setFont(Font.font("Arial", FontWeight.BOLD, 36));
        titel.setTextFill(Color.WHITE);

        loading = new Label("Laden...");
        loading.setFont(Font.font("Arial", 18));
        loading.setTextFill(Color.LIGHTGRAY);
    }

    private void layoutNodes() {
        // Layout de nodes
        content.getChildren().addAll(titel, loading);
        this.getChildren().add(content);
    }

    private void applyStyles() {
        this.setBackground(new Background(new BackgroundFill(Color.DARKCYAN, CornerRadii.EMPTY, Insets.EMPTY)));
    }
}
