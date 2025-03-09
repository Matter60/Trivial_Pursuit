package TrivialPursuit.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Speler {
    private String naam;
    private int x;
    private int y;
    private Set<Kleur> partjes;
    private Kleur spelerKleur;

    public Speler(String naam, Kleur kleur) {
        this.naam = naam;
        this.spelerKleur = kleur;
        this.partjes = new HashSet<>();
        this.x = 270; // Start positie x
        this.y = 271; // Start positie y
    }

    public String getNaam() {
        return naam;
    }

    public Kleur getSpelerKleur() {
        return spelerKleur;
    }

    public void voegPartjeToe(Kleur kleur) {
        partjes.add(kleur);
    }

    public boolean heeftPartje(Kleur kleur) {
        return partjes.contains(kleur);
    }

    public boolean heeftAllePartjes() {
        // Een speler heeft gewonnen als hij alle 6 kleuren heeft
        return partjes.size() == 6;
    }

    public List<Kleur> getPartjes() {
        return new ArrayList<>(partjes);
    }

    // Getters en setters
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}