package TrivialPursuit.view.help;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.control.ScrollPane;

public class HelpView extends BorderPane {
    private Button backButton;
    private Label titleLabel;
    private Label descriptionLabel1;
    private VBox contentBox;
    private ScrollPane scrollPane;

    public HelpView() {
        this.initialiseNodes();
        this.layoutNodes();
    }

    private void initialiseNodes() {

        backButton = new Button("←");
        backButton.setFont(Font.font("Georgia", FontWeight.BOLD, 24));
        backButton.setPadding(new Insets(10));
        backButton.setStyle("-fx-background-color: #8B8000; -fx-padding: 10px;");

        titleLabel = new Label("HELP");
        titleLabel.setFont(Font.font("Georgia", FontWeight.BOLD, 24));
        titleLabel.setUnderline(true);
        titleLabel.setStyle("-fx-text-fill: white; -fx-padding: 10px;");

        descriptionLabel1 = new Label(
                "Doel van het Spel:\n" +
                        "Het doel van het spel is om vragen correct te beantwoorden en zo punten te verdienen. " +
                        "Spelers kunnen ook partjes verdienen door op speciale vakjes te landen en de vraag correct te beantwoorden. "
                        +
                        "Zodra een speler alle zes partjes heeft verzameld, moet hij/zij naar het centrum van het bord om te winnen.\n\n"
                        +

                        "Spelverloop:\n" +
                        "1. Beurt: Spelers gooien om de beurt de dobbelsteen en bewegen hun pion het aantal vakjes dat gegooid is.\n"
                        +
                        "2. Vragen beantwoorden:\n" +
                        "   - De kleur van het vakje waar je op landt, bepaalt de categorie van de vraag.\n" +
                        "   - De speler die aan de beurt is, krijgt een vraag uit de betreffende categorie.\n" +
                        "   - Bij een correct antwoord verdient de speler een punt op het leaderboard.\n" +
                        "   - Bij een fout antwoord gebeurt er niets en gaat de beurt naar de volgende speler.\n\n" +

                        "Categorieën:\n" +
                        "   - Aardrijkskunde (blauw)\n" +
                        "   - Amusement (roze)\n" +
                        "   - Geschiedenis (geel)\n" +
                        "   - Kunst & Literatuur (bruin)\n" +
                        "   - Wetenschap & Natuur (groen)\n" +
                        "   - Sport & Ontspanning (oranje)\n\n" +

                        "Speciale vakjes:\n" +
                        "   - Partje-vak: Als een speler op een partje-vakje landt en de vraag correct beantwoordt, verdient hij/zij een partje. "
                        +
                        "Een speler moet in totaal zes partjes verzamelen.\n" +
                        "   - Witte vakjes: Als een speler op een wit vakje landt, mag hij/zij nogmaals dobbelen.\n\n" +

                        "Alle Partjes?:\n" +
                        "Een speler moet alle zes de partjes verzamelen door de vragen op de partje-vakjes correct te beantwoorden. "
                        +
                        "Zodra een speler alle partjes heeft, moet hij/zij naar het centrum van het bord om te winnen.");
        descriptionLabel1.setFont(Font.font("Georgia", FontWeight.BOLD, 16));
        descriptionLabel1.setStyle("-fx-text-fill: white;");
        descriptionLabel1.setWrapText(true); // Zorgt ervoor dat de tekst binnen de label wordt gewrapt
    }

    private void layoutNodes() {

        contentBox = new VBox(20);
        contentBox.setAlignment(Pos.TOP_CENTER);
        contentBox.setPadding(new Insets(10, 10, 10, 10));
        contentBox.getChildren().addAll(titleLabel, descriptionLabel1);
        contentBox.setStyle("-fx-background-color: DARKCYAN;");

        // Maak een ScrollPane van de contentBox
        scrollPane = new ScrollPane();
        scrollPane.setContent(contentBox);
        scrollPane.setFitToWidth(true); // Zorg ervoor dat de inhoud de breedte van de scrollpane volgt
        scrollPane.setFitToHeight(false); // Zorg ervoor dat de inhoud alleen horizontaal schaalt
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS); // Verticale scrollbar altijd zichtbaar
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); // Geen horizontale scrollbar

        this.setCenter(scrollPane);

        HBox topBox = new HBox(backButton);
        topBox.setAlignment(Pos.TOP_LEFT);
        topBox.setPadding(new Insets(10, 10, 10, 10));
        topBox.setStyle("-fx-background-color: DARKCYAN;");
        this.setTop(topBox);
    }

    Button getBackButton() {
        return backButton;
    }
}
