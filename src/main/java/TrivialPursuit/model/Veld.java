package TrivialPursuit.model;


public class Veld {
    private Kleur kleur; // Kleur van het veld
    private boolean isPartjeVeld; // Of het een partjesveld is
    private boolean isOpnieuwGooienVeld; // Of je nog een keer mag gooien (witte velden)
    private int positie; // Positie op het bord
    private int x;  // X-coördinaat
    private int y;  // Y-coördinaat

    public Veld(Kleur kleur, boolean isPartjeVeld, boolean isOpnieuwGooienVeld, int positie, int x, int y) {
        this.kleur = kleur;
        this.isPartjeVeld = isPartjeVeld;
        this.isOpnieuwGooienVeld = isOpnieuwGooienVeld;
        this.positie = positie;
        this.x = x;
        this.y = y;
    }

    // Getters en setters
    public Kleur getColor() { return kleur; }
    public boolean isPartjeVeld() { return isPartjeVeld; }
    public boolean isOpnieuwGooienVeld() { return isOpnieuwGooienVeld; }
    public int getPositie() { return positie; }
    public int getX() { return x; }
    public int getY() { return y; }
} 