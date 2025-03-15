package TrivialPursuit.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Vraag {

    private String vraag;
    private List<String> mogelijkeAntwoorden;
    private int juisteAntwoordIndex;
    private Kleur categorie;
    private String antwoord;

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

    // Haal de geshufflede antwoorden op
    public List<String> getShuffledAnswers() {
        List<String> shuffled = new ArrayList<>(mogelijkeAntwoorden);
        Collections.shuffle(shuffled);
        return shuffled;
    }

    // Haal de index van het juiste antwoord op
    public int getJuisteAntwoordIndex() {
        return juisteAntwoordIndex;
    }

    // Haal de categorie op
    public Kleur getCategorie() {
        return categorie;
    }

    // Haal het juiste antwoord op
    public String getJuisteAntwoord() {
        return mogelijkeAntwoorden.get(juisteAntwoordIndex);
    }

    // Haal het antwoord op
    public String getAntwoord() {
        return antwoord;
    }

}
