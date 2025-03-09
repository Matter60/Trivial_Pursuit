package TrivialPursuit.view.leaderboard;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class LeaderboardView extends BorderPane {

    private ListView<String> leaderboard;
    private Label leaderboardTitel;
    private Button backButton;

    public LeaderboardView() {
        this.initialiseNodes();
        this.layoutNodes();
    }

    private void initialiseNodes() {
        leaderboard = new ListView<>();
        leaderboard.getItems().addAll("1) Tobi 50", "2) Matter 40", "3) Cockx 10", "4)", "5)", "6)", "7)", "8)", "9)",
                "10)", "11)");

        leaderboardTitel = new Label("Leaderboard");
        leaderboardTitel.setFont(Font.font("Georgia", FontWeight.BOLD, 16));
        leaderboardTitel.setStyle("-fx-text-fill: white;");

        backButton = new Button("←");
        backButton.setFont(Font.font("Georgia", FontWeight.BOLD, 24));
        backButton.setPadding(new Insets(10));
        backButton.setStyle("-fx-background-color: #8B8000; -fx-text-fill: white; -fx-padding: 10px;");
    }

    private void layoutNodes() {
        VBox leaderboardBox = new VBox(10);
        leaderboardBox.setAlignment(Pos.CENTER);
        leaderboardBox.getChildren().addAll(leaderboardTitel, leaderboard);
        leaderboardBox.setBorder(new Border(
                new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2))));
        BorderPane.setMargin(leaderboardBox, new Insets(0, 0, 40, 0));

        leaderboardBox.setMaxWidth(500);
        leaderboardBox.setPrefWidth(500);
        BorderPane.setAlignment(leaderboardBox, Pos.CENTER);

        HBox topBox = new HBox(backButton);
        topBox.setAlignment(Pos.TOP_LEFT);
        topBox.setPadding(new Insets(10, 10, 10, 10));
        topBox.setStyle("-fx-background-color: DARKCYAN;"); // Set background color to DARKCYAN
        this.setTop(topBox);

        // Add the VBox to the BorderPane
        this.setCenter(leaderboardBox);
        this.setStyle("-fx-background-color: DARKCYAN;"); // Set background color of the whole pane to DARKCYAN
    }

    public Button getBackButton() {
        return backButton;
    }

    public ListView<String> getLeaderboard() {
        return leaderboard;
    }
}
