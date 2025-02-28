package TrivialPursuit.model;

public enum Kleur {
    BLUE("Aardrijkskunde"),
    PINK("Amusement"),
    YELLOW("Geschiedenis"),
    BROWN("Kunst & Literatuur"),
    GREEN("Wetenschap & Natuur"),
    ORANGE("Sport & Ontspanning"),
    WHITE("Roll Again");

    private final String description;

    Kleur(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static Kleur fromThema(String thema) {
        if (thema == null)
            return BLUE; // fallback kleur

        String themaFormatted = thema.toUpperCase().replace(" & ", "_").replace(" ", "_");
        for (Kleur kleur : Kleur.values()) {
            if (kleur.getDescription().toUpperCase().replace(" & ", "_").replace(" ", "_")
                    .equals(themaFormatted)) {
                return kleur;
            }
        }
        return BLUE; // fallback kleur
    }

    @Override
    public String toString() {
        return description;
    }
}