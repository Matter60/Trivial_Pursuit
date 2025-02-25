package TrivialPursuit.model;

public class Veld {
    private Kleur kleur;
    private boolean isPartjeVeld;
    private boolean isOpnieuwGooienVeld;
    private boolean isStartVeld;
    private int x;
    private int y;




    // Standaard
    public Veld(Kleur kleur, boolean isPartjeVeld, boolean isOpnieuwGooienVeld, boolean isStartVeld, int x, int y) {
        this.kleur = kleur;
        this.isPartjeVeld = isPartjeVeld;
        this.isOpnieuwGooienVeld = isOpnieuwGooienVeld;
        this.isStartVeld = isStartVeld;
        this.x = x;
        this.y = y;
    }




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
