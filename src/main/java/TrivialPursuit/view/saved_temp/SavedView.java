package TrivialPursuit.view.saved_temp;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class SavedView extends BorderPane {
    private Button backButton;

    public SavedView() {
        this.initialiseNodes();
        this.layoutNodes();
    }

    private void initialiseNodes() {
        backButton = new Button("←");
        backButton.setFont(Font.font("System", FontWeight.BOLD, 24));
        backButton.setPadding(new Insets(10));
    }

    private void layoutNodes() {
        HBox topBox = new HBox(backButton);
        topBox.setAlignment(Pos.TOP_LEFT);
        topBox.setPadding(new Insets(10));
        this.setTop(topBox);
    }

    public Button getBackButton() {
        return backButton;
    }
}
