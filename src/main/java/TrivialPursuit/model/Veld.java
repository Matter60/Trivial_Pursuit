package TrivialPursuit.model;

import java.util.List;

public class Veld {
    private Kleur kleur;
    private boolean isPartjeVeld;
    private boolean isOpnieuwGooienVeld;
    private boolean isStartVeld;
    private List<Veld> veldenDieErnaastLiggen;
    private int x;
    private int y;

    public Veld(Kleur kleur, boolean isPartjeVeld, boolean isOpnieuwGooienVeld, boolean isStartVeld, int x, int y, List<Veld> veldenDieErnaastLiggen) {
        this.kleur = kleur;
        this.isPartjeVeld = isPartjeVeld;
        this.isOpnieuwGooienVeld = isOpnieuwGooienVeld;
        this.isStartVeld = isStartVeld;
        this.x = x;
        this.y = y;
        this.veldenDieErnaastLiggen = veldenDieErnaastLiggen;
    }

    public void setVeldenDieErnaastLiggen(List<Veld> buren) {
        this.veldenDieErnaastLiggen = buren;
    }

    public List<Veld> getVeldenDieErnaastLiggen() {
        return veldenDieErnaastLiggen;
    }

    public Kleur getKleur() { return kleur; }
    public boolean isPartjeVeld() { return isPartjeVeld; }
    public boolean isOpnieuwGooienVeld() { return isOpnieuwGooienVeld; }
    public boolean isStartVeld() { return isStartVeld; }
    public int getX() { return x; }
    public int getY() { return y; }


    @Override
    public String toString() {
        return "Veld{" +
                "x=" + x +
                ", y=" + y +
                ", kleur=" + kleur +
                (isStartVeld ? ", Startveld" : "") +
                (isPartjeVeld ? ", Partjeveld" : "") +
                (isOpnieuwGooienVeld ? ", Opnieuw gooien veld" : "") +
                ", naast=" + veldenDieErnaastLiggen.size() +
                '}';
    }


}
