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

    @Override
    public String toString() {
        return description;
    }
} 