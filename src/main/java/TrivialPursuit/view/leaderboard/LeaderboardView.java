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
    private Label leaderboardTitle;
    private Button backButton;

    public LeaderboardView() {
        this.initialiseNodes();
        this.layoutNodes();
    }


    private void initialiseNodes() {
        leaderboard = new ListView<>();
        leaderboard.getItems().addAll("1) Tobi 50", "2) Matter 40", "3) Cockx 10","4)", "5)","6)","7)","8)","9)","10)","11)");

        leaderboardTitle = new Label("Leaderboard");
        leaderboardTitle.setFont(Font.font("System", FontWeight.BOLD, 16));

        backButton = new Button("←");
        backButton.setFont(Font.font("System", FontWeight.BOLD, 24));
        backButton.setPadding(new Insets(10));

    }

    private void layoutNodes() {
        VBox leaderboardBox = new VBox(10);
        leaderboardBox.setAlignment(Pos.CENTER);
        leaderboardBox.getChildren().addAll(leaderboardTitle, leaderboard);
        leaderboardBox.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2))));
        BorderPane.setMargin(leaderboardBox, new Insets(0,0,40,0));


        leaderboardBox.setMaxWidth(500);
        leaderboardBox.setPrefWidth(500);
        BorderPane.setAlignment(leaderboardBox, Pos.CENTER);

        HBox topBox = new HBox(backButton);
        topBox.setAlignment(Pos.TOP_LEFT);
        topBox.setPadding(new Insets(10,10,10,10));
        this.setTop(topBox);

        // Voeg de VBox toe aan de BorderPane
        this.setCenter(leaderboardBox);
    }

    public Button getBackButton() {
        return backButton;
    }

    public ListView<String> getLeaderboard() {
        return leaderboard;
    }





}
