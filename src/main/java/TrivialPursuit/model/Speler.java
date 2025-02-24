package TrivialPursuit.model;

import java.util.HashSet;
import java.util.Set;



public class Speler {
    private String naam;
    private int x;
    private int y;
    private Set<Kleur> PartjeSet;
    private Kleur playerKleur; // Kleur van de pion

    public Speler(String naam, Kleur playerKleur) {
        this.naam = naam;
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
    public Set<Kleur> getPartjSet() { return PartjeSet; }
    public Kleur getPlayerKleur() { return playerKleur; }
    public int getX() { return x; }
    public int getY() { return y; }
    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }
} 