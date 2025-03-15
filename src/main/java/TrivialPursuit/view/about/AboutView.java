package TrivialPursuit.view.about;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class AboutView extends BorderPane {

    private Button backButton;
    private Label titelLabel;
    private VBox contentBox;
    private Label beschrijvingLabel1;
    private Label beschrijvingLabel2;

    public AboutView() {
        this.initialiseNodes();
        this.layoutNodes();
    }

    private void initialiseNodes() {

        backButton = new Button("←");
        backButton.setFont(Font.font("Georgia", FontWeight.BOLD, 24));
        backButton.setPadding(new Insets(10));
        backButton.setStyle("-fx-background-color: #8B8000; -fx-text-fill: white; -fx-padding: 10px;");

        titelLabel = new Label("Over ons");
        titelLabel.setFont(Font.font("Georgia", FontWeight.BOLD, 24));
        titelLabel.setUnderline(true);
        titelLabel.setStyle("-fx-text-fill: white;");

        beschrijvingLabel1 = new Label("We zijn Matthias en Tobi en hebben deze game gemaakt genaamd Trivial Pursuit.");
        beschrijvingLabel2 = new Label("Dit is een project dat we moeten maken voor Programmeren - Project.");
        beschrijvingLabel1.setFont(Font.font("Georgia", FontWeight.BOLD, 16));
        beschrijvingLabel2.setFont(Font.font("Georgia", FontWeight.BOLD, 16));
        beschrijvingLabel1.setStyle("-fx-text-fill: white;");
        beschrijvingLabel2.setStyle("-fx-text-fill: white;");
    }

    private void layoutNodes() {

        contentBox = new VBox(20);
        contentBox.setAlignment(Pos.CENTER);
        contentBox.setPadding(new Insets(0, 10, 40, 10));
        contentBox.getChildren().addAll(titelLabel, beschrijvingLabel1, beschrijvingLabel2);
        contentBox.setStyle("-fx-background-color: DARKCYAN;");

        this.setCenter(contentBox);

        HBox topBox = new HBox(backButton);
        topBox.setAlignment(Pos.TOP_LEFT);
        topBox.setPadding(new Insets(10, 10, 10, 10));
        topBox.setStyle("-fx-background-color: DARKCYAN;");

        this.setTop(topBox);
    }

    Button getBackButton() {
        return backButton;
    }
}
