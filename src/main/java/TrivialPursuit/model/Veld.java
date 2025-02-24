package TrivialPursuit.model;

public class Veld {
    private Kleur kleur;
    private boolean isPartjeVeld;
    private boolean isOpnieuwGooienVeld;
    private boolean isStartVeld;
    private int x;
    private int y;
    private Veld linkerVeld;
    private Veld rechterVeld;
    private Veld onderVeld;

    // Hoofdconstructor
    public Veld(Kleur kleur, boolean isPartjeVeld, boolean isOpnieuwGooienVeld, boolean isStartVeld, int x, int y) {
        this.kleur = kleur;
        this.isPartjeVeld = isPartjeVeld;
        this.isOpnieuwGooienVeld = isOpnieuwGooienVeld;
        this.isStartVeld = isStartVeld;
        this.x = x;
        this.y = y;
    }

    // Getters
    public Kleur getColor() { return kleur; }
    public boolean isPartjeVeld() { return isPartjeVeld; }
    public boolean isOpnieuwGooienVeld() { return isOpnieuwGooienVeld; }
    public boolean isStartVeld() { return isStartVeld; }
    public int getX() { return x; }
    public int getY() { return y; }
    public Veld getLinkerVeld() { return linkerVeld; }
    public Veld getRechterVeld() { return rechterVeld; }
    public Veld getOnderVeld() { return onderVeld; }

    // Setters (om velden later te koppelen)
    public void setLinkerVeld(Veld linkerVeld) { this.linkerVeld = linkerVeld; }
    public void setRechterVeld(Veld rechterVeld) { this.rechterVeld = rechterVeld; }
    public void setOnderVeld(Veld onderVeld) { this.onderVeld = onderVeld; }
}
