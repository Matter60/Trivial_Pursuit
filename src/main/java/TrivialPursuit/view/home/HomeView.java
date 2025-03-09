package TrivialPursuit.view.home;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class HomeView extends BorderPane {
    // Private node attributes
    private Button aboutButton;
    private Button adminButton;
    private Button helpButton;
    private Button startButton;
    private Button leaderboardButton;
    private Label titelLabel;
    private ListView<String> leaderboard;
    private Label leaderboardTitel;
    private VBox leaderboardBox;

    public HomeView() {
        this.initialiseNodes();
        this.layoutNodes();
        this.applyStyles(); // Apply styles
    }

    private void initialiseNodes() {
        aboutButton = new Button("About");
        adminButton = new Button("Admin");
        helpButton = new Button("Help");
        leaderboardButton = new Button();

        titelLabel = new Label("Trivial Pursuit");
        titelLabel.setFont(Font.font("Georgia", FontWeight.BOLD, 24)); // Set font to Georgia

        startButton = new Button("START");
        startButton.setPrefSize(300, 50);

        leaderboard = new ListView<>();

        leaderboardTitel = new Label("Leaderboard");
        leaderboardTitel.setFont(Font.font("Georgia", FontWeight.BOLD, 16)); // Set font to Georgia
    }

    private void layoutNodes() {
        HBox topBar = new HBox(10);
        topBar.setAlignment(Pos.CENTER);
        topBar.setSpacing(40);
        topBar.getChildren().addAll(aboutButton, adminButton, helpButton);
        this.setTop(topBar);
        this.setPadding(new Insets(20));

        VBox middle = new VBox(10);
        middle.setAlignment(Pos.TOP_CENTER);
        middle.getChildren().addAll(titelLabel, startButton);
        middle.setSpacing(10);
        middle.setPadding(new Insets(50, 10, 10, 10));
        this.setCenter(middle);

        leaderboard.setPrefWidth(150);
        leaderboard.setPrefHeight(80);

        leaderboardBox = new VBox(10);
        leaderboardBox.setAlignment(Pos.CENTER);
        leaderboardBox.getChildren().addAll(leaderboardTitel, leaderboard);
        leaderboardBox.setBorder(new Border(
                new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2))));

        leaderboardBox.setMaxWidth(200);
        leaderboardBox.setPrefWidth(200);
        BorderPane.setAlignment(leaderboardBox, Pos.CENTER);
        leaderboardButton.setAlignment(leaderboardBox.getAlignment());
        leaderboardButton.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");

        this.setBottom(leaderboardBox);
    }

    private void applyStyles() {
        // Set background color for the entire view
        this.setBackground(new Background(new BackgroundFill(Color.DARKCYAN, CornerRadii.EMPTY, Insets.EMPTY)));

        // Set button styles with font and color
        aboutButton.setStyle("-fx-background-color: #8B8000; -fx-text-fill: white; -fx-padding: 10px;");
        adminButton.setStyle("-fx-background-color: #8B8000; -fx-text-fill: white; -fx-padding: 10px;");
        helpButton.setStyle("-fx-background-color: #8B8000; -fx-text-fill: white; -fx-padding: 10px;");
        startButton.setStyle("-fx-background-color: #8B8000; -fx-text-fill: white; -fx-padding: 10px;");

        // Apply Georgia font to buttons
        aboutButton.setFont(Font.font("Georgia", FontWeight.BOLD, 16));
        adminButton.setFont(Font.font("Georgia", FontWeight.BOLD, 16));
        helpButton.setFont(Font.font("Georgia", FontWeight.BOLD, 16));
        startButton.setFont(Font.font("Georgia", FontWeight.BOLD, 18));

        // Set leaderboard styles
        leaderboardTitel.setTextFill(Color.YELLOW); // Set leaderboard title color
        leaderboardTitel.setFont(Font.font("Georgia", FontWeight.BOLD, 18)); // Set font for leaderboard title

        // Set font color for the title
        titelLabel.setTextFill(Color.YELLOW); // Set title label text color
    }

    public Button getAboutButton() {
        return aboutButton;
    }

    public Button getAdminButton() {
        return adminButton;
    }

    public Button getHelpButton() {
        return helpButton;
    }

    public Button getStartButton() {
        return startButton;
    }

    public VBox getLeaderboardBox() {
        return leaderboardBox;
    }

    public ListView<String> getLeaderboard() {
        return leaderboard;
    }
}
