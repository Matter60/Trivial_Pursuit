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
    private Label titleLabel;
    private ListView<String> leaderboard;
    private Label leaderboardTitle;
    private VBox leaderboardBox;

    public HomeView() {
        this.initialiseNodes();
        this.layoutNodes();
    }

    private void initialiseNodes() {

        aboutButton = new Button("About");
        adminButton = new Button("Admin");
        helpButton = new Button("Help");
        leaderboardButton = new Button();


        titleLabel = new Label("Trivial Pursuit");
        titleLabel.setFont(Font.font("System", FontWeight.BOLD, 24));

        startButton = new Button("START");
        startButton.setPrefSize(300, 50);

        leaderboard = new ListView<>();
        leaderboard.getItems().addAll("Tobi 60", "Matthias 40", "Cockx 10");

        leaderboardTitle = new Label("Leaderboard");
        leaderboardTitle.setFont(Font.font("System", FontWeight.BOLD, 16));
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
        middle.getChildren().addAll(titleLabel, startButton);
        middle.setSpacing(10);
        middle.setPadding(new Insets(50, 10, 10, 10));
        this.setCenter(middle);

        // Set ListView width to a smaller value
        leaderboard.setPrefWidth(150); // Smaller width here
        leaderboard.setPrefHeight(80);

        leaderboardBox = new VBox(10);
        leaderboardBox.setAlignment(Pos.CENTER);
        leaderboardBox.getChildren().addAll(leaderboardTitle, leaderboard);
        leaderboardBox.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2))));

        // Set width constraint on leaderboardBox if necessary
        leaderboardBox.setMaxWidth(200); // Constrain the container width
        leaderboardBox.setPrefWidth(200); // Set preferred width for the container
        BorderPane.setAlignment(leaderboardBox, Pos.CENTER);
        leaderboardButton.setAlignment(leaderboardBox.getAlignment());
        leaderboardButton.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");


        this.setBottom(leaderboardBox);
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
