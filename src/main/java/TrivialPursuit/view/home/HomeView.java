package TrivialPursuit.view.home;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

public class HomeView extends BorderPane {
    // Private node attributes
    private Button aboutButton;
    private Button helpButton;
    private Button startButton;
    private Label titelLabel;
    private ListView<String> leaderboard;
    private Label leaderboardTitel;
    private VBox leaderboardBox;
    private MenuBar menuBar;

    public HomeView() {
        this.initialiseNodes();
        this.layoutNodes();
        this.applyStyles();
    }

    private void initialiseNodes() {

        aboutButton = new Button("About");
        helpButton = new Button("Help");

        menuBar = new MenuBar();
        Menu adminMenu = new Menu("Admin");

        MenuItem adminMenuItem = new MenuItem("Admin Panel");

        adminMenu.getItems().add(adminMenuItem);

        menuBar.getMenus().addAll(adminMenu);
        menuBar.setPrefWidth(Double.MAX_VALUE);

        titelLabel = new Label("Trivial Pursuit");
        titelLabel.setFont(Font.font("Georgia", FontWeight.BOLD, 24));

        startButton = new Button("START");
        startButton.setPrefSize(300, 50);

        leaderboard = new ListView<>();
        leaderboardTitel = new Label("Leaderboard");
    }

    private void layoutNodes() {

        HBox topBar = new HBox(10);
        topBar.setAlignment(Pos.CENTER);
        topBar.setSpacing(40);
        topBar.getChildren().addAll(aboutButton, helpButton);
        topBar.setPadding(new Insets(10, 0, 0, 0));

        VBox topContainer = new VBox(5);
        topContainer.getChildren().addAll(menuBar);
        this.setTop(topContainer);

        VBox middle = new VBox(20);
        middle.setAlignment(Pos.CENTER);
        middle.getChildren().addAll(titelLabel, startButton, topBar);
        middle.setPadding(new Insets(0, 10, 20, 10));
        this.setCenter(middle);

        leaderboard.setPrefWidth(400);
        leaderboard.setPrefHeight(200);

        leaderboardBox = new VBox(10);
        leaderboardBox.setAlignment(Pos.CENTER);
        leaderboardBox.getChildren().addAll(leaderboardTitel, leaderboard);
        leaderboardBox.setBorder(new Border(
                new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2))));

        leaderboardBox.setMaxWidth(250);
        leaderboardBox.setPrefWidth(250);

        leaderboardBox.setSpacing(10);
        BorderPane.setAlignment(leaderboardBox, Pos.CENTER);

        this.setBottom(leaderboardBox);
    }

    private void applyStyles() {

        this.setBackground(new Background(new BackgroundFill(Color.DARKCYAN, CornerRadii.EMPTY, Insets.EMPTY)));

        aboutButton.setStyle("-fx-background-color: #8B8000; -fx-text-fill: white; -fx-padding: 10px;");
        helpButton.setStyle("-fx-background-color: #8B8000; -fx-text-fill: white; -fx-padding: 10px;");

        aboutButton.setFont(Font.font("Georgia", FontWeight.BOLD, 16));
        helpButton.setFont(Font.font("Georgia", FontWeight.BOLD, 16));

        titelLabel.setTextFill(Color.YELLOW);

        startButton.setStyle("-fx-background-color: #8B8000; -fx-text-fill: white; -fx-padding: 10px;");
        startButton.setFont(Font.font("Georgia", FontWeight.BOLD, 18));

        leaderboardTitel.setTextFill(Color.YELLOW);
        leaderboardTitel.setFont(Font.font("Georgia", FontWeight.BOLD, 18));
    }

    // Getters
    public Button getAboutButton() {
        return aboutButton;
    }

    public Button getHelpButton() {
        return helpButton;
    }

    public MenuItem getAdminMenuItem() {
        return (MenuItem) menuBar.getMenus().get(0).getItems().get(0);
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
