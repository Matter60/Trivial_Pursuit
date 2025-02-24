package TrivialPursuit.view.game;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class GameView extends BorderPane {
    private Button backButton;
    private Button rollDiceButton;
    private Label currentPlayerLabel;
    private ImageView boardImageView;
    private VBox playerInfoBox;
    private Pane boardPane; // To hold the board image and pions

    public GameView() {
        this.initialiseNodes();
        this.layoutNodes();
    }

    private void initialiseNodes() {
        // Back button
        backButton = new Button("←");
        backButton.setFont(Font.font("System", FontWeight.BOLD, 24));
        backButton.setPadding(new Insets(10));

        // Roll dice button
        rollDiceButton = new Button("Roll Dice");
        rollDiceButton.setPrefWidth(100);
        rollDiceButton.setPadding(new Insets(10));

        // Current player label
        currentPlayerLabel = new Label("Current Player: ");
        currentPlayerLabel.setFont(Font.font("System", FontWeight.BOLD, 16));

        // Board image
        Image boardImage = new Image(getClass().getResourceAsStream("/bord.png"));
        boardImageView = new ImageView(boardImage);
        boardImageView.setFitWidth(600);
        boardImageView.setFitHeight(600);
        boardImageView.setPreserveRatio(true);

        // Player info box
        playerInfoBox = new VBox(10);
        playerInfoBox.setPadding(new Insets(10));
        playerInfoBox.setAlignment(Pos.TOP_CENTER);

        // Board container
        boardPane = new Pane();
        boardPane.getChildren().add(boardImageView);

    }

    private void layoutNodes() {
        // Top section with back button and current player
        HBox topBox = new HBox(20);
        topBox.getChildren().addAll(backButton, currentPlayerLabel);
        topBox.setAlignment(Pos.CENTER_LEFT);
        topBox.setPadding(new Insets(10));
        this.setTop(topBox);

        // Center section with board
        this.setCenter(boardPane);

        // Right section with player info and dice
        VBox rightBox = new VBox(20);
        rightBox.getChildren().addAll(playerInfoBox, rollDiceButton);
        rightBox.setAlignment(Pos.TOP_CENTER);
        rightBox.setPadding(new Insets(20));
        this.setRight(rightBox);
    }

    public Button getBackButton() {
        return backButton;
    }

    public Button getRollDiceButton() {
        return rollDiceButton;
    }

}