package TrivialPursuit.view.oefenen;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class OefenenView extends GridPane {

    private Label vraagLabel;
    private VBox vraagBox;
    private ToggleGroup antwoorGroep;
    private List<RadioButton> AntwoordButtons;
    private Button AntwoordButton;
    private Button backButton;
    private Label titelLabel;

    public OefenenView() {
        this.initialiseNodes();
        this.layoutNodes();
        this.applyStyles();
    }

    private void initialiseNodes() {
        this.setPadding(new Insets(20));
        this.setHgap(10);
        this.setVgap(10);

        AntwoordButton = new Button("Beantwoord");
        vraagLabel = new Label(" ");

        antwoorGroep = new ToggleGroup();
        AntwoordButtons = new ArrayList<>();
        titelLabel = new Label("Oefenen");
        backButton = new Button("←");
        backButton.setFont(Font.font("Georgia", FontWeight.BOLD, 24));
        backButton.setPadding(new Insets(10));
        backButton.setStyle("-fx-background-color: #8B8000; -fx-text-fill: white; -fx-padding: 10px;");

        for (int i = 0; i < 4; i++) {
            RadioButton rb = new RadioButton("");
            rb.setToggleGroup(antwoorGroep);
            rb.setWrapText(true);
            AntwoordButtons.add(rb);
        }

        vraagBox = new VBox(10);
    }

    private void layoutNodes() {
        // Back button
        HBox topBox = new HBox();
        topBox.setAlignment(Pos.CENTER_LEFT);
        topBox.getChildren().add(backButton);
        this.add(topBox, 0, 0);

        // Centered vraagBox
        vraagBox.setAlignment(Pos.CENTER);
        vraagBox.setPadding(new Insets(20));
        vraagBox.setMaxWidth(300);

        vraagLabel.setAlignment(Pos.CENTER);
        vraagLabel.setWrapText(true);
        vraagLabel.setMaxWidth(Double.MAX_VALUE);

        titelLabel.setAlignment(Pos.CENTER);

        VBox radioButtonsBox = new VBox(10);
        radioButtonsBox.setAlignment(Pos.CENTER_LEFT);
        radioButtonsBox.getChildren().addAll(AntwoordButtons);

        vraagBox.getChildren().addAll(titelLabel, vraagLabel, radioButtonsBox, AntwoordButton);
        vraagBox.setVisible(false);

        this.add(vraagBox, 0, 1);
    }

    private void applyStyles() {
        this.setBackground(new Background(new BackgroundFill(Color.DARKCYAN, CornerRadii.EMPTY, Insets.EMPTY)));

        String buttonStyle = "-fx-background-color: #8B8000; -fx-text-fill: white; -fx-padding: 10px;";
        AntwoordButton.setStyle(buttonStyle);
        AntwoordButton.setFont(Font.font("Georgia", FontWeight.BOLD, 16));
        vraagLabel.setFont(Font.font("Georgia", FontWeight.BOLD, 16));
        titelLabel.setFont(Font.font("Georgia", FontWeight.BOLD, 30));

        vraagLabel.setTextFill(Color.WHITE);
        titelLabel.setTextFill(Color.WHITE);
        AntwoordButtons.forEach(rb -> {
            rb.setTextFill(Color.WHITE);
            rb.setFont(Font.font("Georgia", 14));
        });
    }

    public void setQuestionText(String text) {
        vraagLabel.setText(text);
    }

    public void setAnswerButtonText(int index, String text) {
        if (index >= 0 && index < AntwoordButtons.size()) {
            AntwoordButtons.get(index).setText(text);
        }
    }

    public void setAnswerButtonVisible(int index, boolean visible) {
        if (index >= 0 && index < AntwoordButtons.size()) {
            AntwoordButtons.get(index).setVisible(visible);
        }
    }

    public void setVraagBoxVisible(boolean visible) {
        vraagBox.setVisible(visible);
    }

    public void setAnswerButtonVisible(boolean visible) {
        AntwoordButton.setVisible(visible);
    }

    public Button getBackButton() {
        return backButton;
    }

    public List<RadioButton> getAntwoordButtons() {
        return AntwoordButtons;
    }

    public Button getAntwoordButton() {
        return AntwoordButton;
    }

    public ToggleGroup getAntwoorGroep() {
        return antwoorGroep;
    }

}