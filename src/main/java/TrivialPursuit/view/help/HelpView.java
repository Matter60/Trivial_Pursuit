package TrivialPursuit.view.help;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class HelpView extends BorderPane {
    private Button backButton;
    private Label titleLabel;
    private Label descriptionLabel1;
    private VBox contentBox;

    public HelpView() {
        this.initialiseNodes();
        this.layoutNodes();
    }

    private void initialiseNodes() {

        backButton = new Button("←");
        backButton.setFont(Font.font("Georgia", FontWeight.BOLD, 24));
        backButton.setPadding(new Insets(10));
        backButton.setStyle("-fx-background-color: #8B8000; -fx-padding: 10px;");

        titleLabel = new Label("HELP");
        titleLabel.setFont(Font.font("Georgia", FontWeight.BOLD, 24));
        titleLabel.setUnderline(true);
        titleLabel.setStyle("-fx-text-fill: white; -fx-padding: 10px;");

        descriptionLabel1 = new Label("Hier komen de regels van het spel.");
        descriptionLabel1.setFont(Font.font("Georgia", FontWeight.BOLD, 24));
        descriptionLabel1.setStyle("-fx-text-fill: white;");
    }

    private void layoutNodes() {

        contentBox = new VBox(20);
        contentBox.setAlignment(Pos.CENTER);
        contentBox.setPadding(new Insets(10, 10, 10, 10));
        contentBox.getChildren().addAll(titleLabel, descriptionLabel1);
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
