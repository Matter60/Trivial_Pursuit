package TrivialPursuit.view.make;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class MakeView extends BorderPane {

    private Button backButton;
    private Button createGameButton;
    private Button loadGameButton;
    private Label titleLabel;

    public MakeView() {
        this.initialiseNodes();
        this.layoutNodes();
    }

    private void initialiseNodes() {

        backButton = new Button("←");
        backButton.setFont(Font.font("System", FontWeight.BOLD, 24));
        backButton.setPadding(new Insets(10));


        createGameButton = new Button("Create Game");
        createGameButton.setFont(Font.font("System", FontWeight.NORMAL, 18));
        createGameButton.setPadding(new Insets(10));

        loadGameButton = new Button("Load Game");
        loadGameButton.setFont(Font.font("System", FontWeight.NORMAL, 18));
        loadGameButton.setPadding(new Insets(10));

        titleLabel = new Label("Create or load a game");
        titleLabel.setFont(Font.font("System", FontWeight.BOLD, 18));
        titleLabel.setPadding(new Insets(10));
    }

    private void layoutNodes() {

        HBox topBox = new HBox(backButton);
        topBox.setAlignment(Pos.TOP_LEFT);
        topBox.setPadding(new Insets(10));
        this.setTop(topBox);


        VBox centerBox = new VBox(20);


        centerBox.setAlignment(Pos.CENTER);
        centerBox.setPadding(new Insets(0,10,40,10));
        centerBox.getChildren().addAll(titleLabel,createGameButton, loadGameButton);
        this.setCenter(centerBox);
    }

    public Button getBackButton() {
        return backButton;
    }

    public Button getCreateGameButton() {
        return createGameButton;
    }

    public Button getLoadGameButton() {
        return loadGameButton;
    }
}
