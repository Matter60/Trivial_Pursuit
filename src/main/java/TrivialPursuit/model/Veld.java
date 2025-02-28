package TrivialPursuit.model;

import java.util.List;

public class Veld {
    private Kleur kleur;
    private boolean isPartjeVeld;
    private boolean isOpnieuwGooienVeld;
    private boolean isStartVeld;
    private List<Integer> mogelijkeIndexOpties;
    private int x;
    private int y;

    // Standaard
    public Veld(Kleur kleur, boolean isPartjeVeld, boolean isOpnieuwGooienVeld, boolean isStartVeld, int x, int y, List<Integer> mogelijkeIndexOpties) {
        this.kleur = kleur;
        this.isPartjeVeld = isPartjeVeld;
        this.isOpnieuwGooienVeld = isOpnieuwGooienVeld;
        this.isStartVeld = isStartVeld;
        this.x = x;
        this.y = y;
        this.mogelijkeIndexOpties = mogelijkeIndexOpties;

    }

    // Voeg deze constructor toe voor basis veld creatie


    public Kleur getKleur() {
        return kleur;
    }

    public boolean isPartjeVeld() {
        return isPartjeVeld;
    }

    public boolean isOpnieuwGooienVeld() {
        return isOpnieuwGooienVeld;
    }

    public boolean isStartVeld() {
        return isStartVeld;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}

