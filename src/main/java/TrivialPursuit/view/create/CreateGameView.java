package TrivialPursuit.view.create;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class CreateGameView extends BorderPane {
    private Button backButton;
    private Button startButton;
    private Button addPlayerButton;
    private Label titleLabel;
    private Label minMaxLabel;
    private TextField player1Field;
    private TextField player2Field;
    private VBox playersBox;

    public CreateGameView() {
        this.initialiseNodes();
        this.layoutNodes();
    }

    private void initialiseNodes() {
        backButton = new Button("←");
        backButton.setFont(Font.font("System", FontWeight.BOLD, 24));
        backButton.setPadding(new Insets(10));

        titleLabel = new Label("Add players");
        titleLabel.setFont(Font.font("System", FontWeight.BOLD, 18));

        minMaxLabel = new Label("Min 2 max 6");
        minMaxLabel.setFont(Font.font("System", FontWeight.NORMAL, 14));

        player1Field = new TextField();
        player1Field.setPromptText("Player1");
        player1Field.setPrefWidth(200);

        player2Field = new TextField();
        player2Field.setPromptText("Player2");
        player2Field.setPrefWidth(200);

        startButton = new Button("START");
        startButton.setPrefWidth(100);
        startButton.setPadding(new Insets(10));

        addPlayerButton = new Button("+");
        addPlayerButton.setPadding(new Insets(10));
    }

    private void layoutNodes() {

        HBox topBox = new HBox(backButton);
        topBox.setAlignment(Pos.TOP_LEFT);
        topBox.setPadding(new Insets(10));
        this.setTop(topBox);

        playersBox = new VBox(10);
        playersBox.setAlignment(Pos.TOP_CENTER);
        playersBox.getChildren().addAll(
                titleLabel,
                minMaxLabel,
                player1Field,
                player2Field);
        playersBox.setPadding(new Insets(20));

        HBox buttonBox = new HBox(20);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().addAll(startButton, addPlayerButton);
        buttonBox.setPadding(new Insets(20));

        VBox centerBox = new VBox(20);
        centerBox.getChildren().addAll(playersBox, buttonBox);
        centerBox.setAlignment(Pos.CENTER);
        this.setCenter(centerBox);
    }

    public Button getBackButton() {
        return backButton;
    }

    public Button getStartButton() {
        return startButton;
    }

    public Button getAddPlayerButton() {
        return addPlayerButton;
    }
}