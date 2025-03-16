package TrivialPursuit.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Vraag {

    private String vraag;
    private List<String> mogelijkeAntwoorden;
    private int juisteAntwoordIndex;
    private Kleur categorie;

    public Vraag(String vraag, List<String> mogelijkeAntwoorden, int juisteAntwoordIndex, Kleur categorie) {
        if (juisteAntwoordIndex < 0 || juisteAntwoordIndex >= mogelijkeAntwoorden.size()) {
            throw new IllegalArgumentException("Correct answer index moet binnen bereik van antwoorden liggen");
        }
        this.vraag = vraag;
        this.mogelijkeAntwoorden = new ArrayList<>(mogelijkeAntwoorden);
        this.juisteAntwoordIndex = juisteAntwoordIndex;
        this.categorie = categorie;
    }

    // Controleer of het geselecteerde antwoord correct is
    public boolean checkIndex(int selectedIndex) {
        return selectedIndex == juisteAntwoordIndex;
    }

    // Haal de vraag op
    public String getVraag() {
        return vraag;
    }

    // Haal de mogelijke antwoorden op
    public List<String> getMogelijkeAntwoorden() {
        return new ArrayList<>(mogelijkeAntwoorden);
    }

    // Haal de categorie op
    public Kleur getCategorie() {
        return categorie;
    }

    // Haal het juiste antwoord op
    public String getJuisteAntwoord() {
        return mogelijkeAntwoorden.get(juisteAntwoordIndex);
    }

}
