package TrivialPursuit.model;

import java.util.ArrayList;
import java.util.List;

public class Vraag {
    private String vraag;
    private List<String> mogelijkeAntwoorden;
    private int juisteAntwoordIndex;
    private Kleur categorie;

    public Vraag(String vraag, List<String> answers, int juisteIndex, Kleur categorie) {
        if (juisteIndex < 0 || juisteIndex >= answers.size()) {
            throw new IllegalArgumentException("Correct answer index moet binnen bereik van antwoorden liggen");
        }
        this.vraag = vraag;
        this.mogelijkeAntwoorden = new ArrayList<>(answers);
        this.juisteAntwoordIndex = juisteIndex;
        this.categorie = categorie;
    }

    public boolean checkAntwoord(int selectedIndex) {
        return selectedIndex == juisteAntwoordIndex;
    }

    // Getters
    public String getVraag() { return vraag; }
    public List<String> getMogelijkeAntwoorden() { return new ArrayList<>(mogelijkeAntwoorden); }
    public Kleur getCategorie() { return categorie; }
    

    public String getJuisteAntwoord() { return mogelijkeAntwoorden.get(juisteAntwoordIndex); }
} 