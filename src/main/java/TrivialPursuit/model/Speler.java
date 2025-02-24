package TrivialPursuit.model;

import java.util.HashSet;
import java.util.Set;



public class Speler {
    private String naam;
    private int position;
    private Set<Kleur> PartjeSet;
    private Kleur playerKleur; // Kleur van de pion

    public Speler(String naam, Kleur playerKleur) {
        this.naam = naam;
        this.position = 0;
        this.PartjeSet = new HashSet<>();
        this.playerKleur = playerKleur;
    }

    public boolean hasWon() {
        return PartjeSet.size() == 6;
    }

    public void addPartje(Kleur kleur) {
        PartjeSet.add(kleur);
    }

    // Getters en setters
    public String getNaam() { return naam; }
    public int getPosition() { return position; }
    public void setPosition(int position) { this.position = position; }
    public Set<Kleur> getPartjSet() { return PartjeSet; }
    public Kleur getPlayerKleur() { return playerKleur; }
} 