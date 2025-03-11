package TrivialPursuit.view.oefenen;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import TrivialPursuit.view.home.HomeView;
import TrivialPursuit.model.TrivialPursuitController;

import java.util.ArrayList;
import java.util.List;

public class OefenenView extends BorderPane {
    private Label questionLabel;
    private VBox vraagBox;
    private ToggleGroup answerGroup;
    private List<RadioButton> answerButtons;
    private Button answerButton;
    private Button backButton;
    private TrivialPursuitController controller;

    public OefenenView() {
        this.initialiseNodes();
        this.layoutNodes();
        this.applyStyles();
    }

    private void initialiseNodes() {
        answerButton = new Button("Beantwoord");
        questionLabel = new Label(" ");

        answerGroup = new ToggleGroup();
        answerButtons = new ArrayList<>();
        backButton = new Button("←");
        backButton.setFont(Font.font("Georgia", FontWeight.BOLD, 24));
        backButton.setPadding(new Insets(10));
        backButton.setStyle("-fx-background-color: #8B8000; -fx-text-fill: white; -fx-padding: 10px;");


        // Voeg de backButton toe aan de layout
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setVgap(8);
        grid.setHgap(10);
        grid.add(backButton, 0, 0);
        this.setTop(grid);

        for (int i = 0; i < 4; i++) {
            RadioButton rb = new RadioButton("");
            rb.setToggleGroup(answerGroup);
            rb.setWrapText(true);
            answerButtons.add(rb);
        }

        vraagBox = new VBox(10);
    }

    private void layoutNodes() {
        // Create a top HBox for the back button
        HBox topBox = new HBox(20);
        topBox.setAlignment(Pos.CENTER_LEFT);
        topBox.setPadding(new Insets(10));
        topBox.getChildren().add(backButton); // Add backButton to the topBox
        this.setTop(topBox);

        // Center the vraagBox in the center of the BorderPane
        this.setCenter(vraagBox);

        // Set alignment and padding for vraagBox
        vraagBox.setAlignment(Pos.CENTER);
        vraagBox.setPadding(new Insets(20));
        vraagBox.setMaxWidth(300); // Optional: Set a maximum width for vraagBox

        // Configure questionLabel
        questionLabel.setAlignment(Pos.CENTER);
        questionLabel.setWrapText(true);
        questionLabel.setMaxWidth(Double.MAX_VALUE); // Allow label to use maximum available width

        // Add components to vraagBox
        vraagBox.getChildren().add(questionLabel);
        vraagBox.getChildren().addAll(answerButtons);
        vraagBox.getChildren().add(answerButton);

        // Initially hide vraagBox
        vraagBox.setVisible(false);

        // Set answerButton to fill available width
        answerButton.setMaxWidth(Double.MAX_VALUE);
    }


    private void applyStyles() {
        this.setBackground(new Background(new BackgroundFill(Color.DARKCYAN, CornerRadii.EMPTY, Insets.EMPTY)));

        String buttonStyle = "-fx-background-color: #8B8000; -fx-text-fill: white; -fx-padding: 10px;";
        answerButton.setStyle(buttonStyle);

        answerButton.setFont(Font.font("Georgia", FontWeight.BOLD, 16));
        questionLabel.setFont(Font.font("Georgia", FontWeight.BOLD, 16));

        questionLabel.setTextFill(Color.WHITE);

        answerButtons.forEach(rb -> {
            rb.setTextFill(Color.WHITE);
            rb.setFont(Font.font("Georgia", 14));
        });
    }


    public Label getQuestionLabel() {
        return questionLabel;
    }

    public List<RadioButton> getAnswerButtons() {
        return answerButtons;
    }

    public Button getAnswerButton() {
        return answerButton;
    }

    public VBox getVraagBox() {
        return vraagBox;
    }

    public ToggleGroup getAnswerGroup() {
        return answerGroup;
    }

    public void showQuestion(String vraag, List<String> answers) {
        questionLabel.setText(vraag);

        for (int i = 0; i < answers.size(); i++) {
            answerButtons.get(i).setText(answers.get(i));
            answerButtons.get(i).setVisible(true);
        }

        for (int i = answers.size(); i < answerButtons.size(); i++) {
            answerButtons.get(i).setVisible(false);
        }

        vraagBox.setVisible(true);
        answerButton.setVisible(true);
    }

    public Button getBackButton() {
        return backButton;
    }
}
