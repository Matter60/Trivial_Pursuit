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
                        "Het doel van Trivial Pursuit is om door middel van het beantwoorden van vragen vakjes op je pion in te vullen en uiteindelijk naar het centrum van het bord te gaan om de laatste vraag te beantwoorden.\n\n" +
                        "Spelers:\n" +
                        "Trivial Pursuit kan gespeeld worden door 2 tot 6 spelers. Je kunt ook in teams spelen, afhankelijk van het aantal spelers.\n\n" +
                        "Voorbereiding:\n" +
                        "1. Speelbord: Plaats het bord in het midden van de tafel.\n" +
                        "2. Elke speler kiest een pion en plaatst deze op het startvak (de ronde vakjes aan de buitenkant van het bord).\n" +
                        "3. Het vragenkaartje wordt klaargelegd. Elke kaart heeft 6 vragen, één voor elke categorie.\n" +
                        "4. Zorg ervoor dat iedereen een scorekaart heeft om de voortgang bij te houden.\n\n" +
                        "Spelverloop:\n" +
                        "1. Beurt: Spelers gooien om de beurt de dobbelsteen en bewegen hun pion het aantal vakjes dat ze gooien.\n" +
                        "2. Vragen beantwoorden:\n" +
                        "   - De kleur van het vakje waar je terechtkomt bepaalt welke categorie de vraag heeft.\n" +
                        "   - De speler die aan de beurt is, krijgt een vraag uit de betreffende categorie.\n" +
                        "   - Als de speler de vraag goed beantwoordt, krijgt hij/zij een punt in de betreffende categorie (in de vorm van een pion).\n" +
                        "   - Als de speler de vraag fout beantwoordt, blijft het vakje leeg en is de beurt voorbij.\n" +
                        "3. Categorieën: Er zijn 6 categorieën op het bord:\n" +
                        "   - Geografie\n" +
                        "   - Geschiedenis\n" +
                        "   - Entertainment\n" +
                        "   - Kunst & Literatuur\n" +
                        "   - Wetenschap\n" +
                        "   - Sport & Spel\n" +
                        "4. Speciale vakjes: Er zijn vakjes op het bord die een speler naar een speciale vakje sturen, wat betekent dat je meer pionnen kunt verdienen als je de vraag correct beantwoordt.\n\n" +
                        "Het Vullen van de Pion:\n" +
                        "Om de volledige pion te verzamelen, moet de speler een punt verdienen in elke categorie. Zodra een speler alle pionnen heeft, kan hij/zij naar het centrum van het bord gaan.\n\n" +
                        "Het Centrum:\n" +
                        "1. Centrum bereiken: Wanneer een speler alle pionnen heeft, kan hij/zij naar het centrum van het bord gaan.\n" +
                        "2. Daar krijgt de speler een speciale vraag die alle categorieën combineert.\n" +
                        "3. Winnen: De speler moet deze vraag correct beantwoorden om het spel te winnen.\n\n" +
                        "Winnen:\n" +
                        "De eerste speler die de juiste vraag in het centrum beantwoordt, wint het spel."
        );
        descriptionLabel1.setFont(Font.font("Georgia", FontWeight.BOLD, 16));
        descriptionLabel1.setStyle("-fx-text-fill: white;");
        descriptionLabel1.setWrapText(true);  // Zorgt ervoor dat de tekst binnen de label wordt gewrapt
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
        scrollPane.setFitToWidth(true);  // Zorg ervoor dat de inhoud de breedte van de scrollpane volgt
        scrollPane.setFitToHeight(false); // Zorg ervoor dat de inhoud alleen horizontaal schaalt
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);  // Verticale scrollbar altijd zichtbaar
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);  // Geen horizontale scrollbar

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
