package TrivialPursuit.model;

public enum Kleur {
    BLAUW("Aardrijkskunde", "blauw"),
    ROZE("Amusement", "roze"),
    GEEL("Geschiedenis", "geel"),
    BRUIN("Kunst & Literatuur", "bruin"),
    GROEN("Wetenschap & Natuur", "groen"),
    ORANJE("Sport & Ontspanning", "oranje"),
    WIT("Roll Again", "wit");

    private final String beschrijving;
    private final String kleurNaam;

    Kleur(String beschrijving, String kleurNaam) {
        this.beschrijving = beschrijving;
        this.kleurNaam = kleurNaam;
    }

    public String getBeschrijving() {
        return beschrijving;
    }

    public static Kleur fromThema(String categorie) {
        if (categorie == null) {
            throw new IllegalArgumentException("Categorie is null");
        }
        for (Kleur kleur : Kleur.values()) {
            if (kleur.getBeschrijving().equals(categorie)) {
                return kleur;
            }
        }
        throw new IllegalArgumentException("Categorie is niet gevonden");
    }

    @Override
    public String toString() {
        return kleurNaam;
    }
}