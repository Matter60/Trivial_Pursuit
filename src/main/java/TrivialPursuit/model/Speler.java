package TrivialPursuit.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Speler {

    private String naam;
    private Set<Kleur> partjes;
    private Kleur spelerKleur;
    private int positie;

    public Speler(String naam, Kleur kleur) {
        this.naam = naam;
        this.spelerKleur = kleur;
        this.partjes = new HashSet<>();
        this.positie = 0; // Start op positie 0
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

    public int getPositie() {
        return positie;
    }

    public void setPositie(int positie) {
        this.positie = positie;
    }

}
