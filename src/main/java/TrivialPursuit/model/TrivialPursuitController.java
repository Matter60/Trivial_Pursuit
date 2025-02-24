package TrivialPursuit.model;

import java.util.List;

public class TrivialPursuitController {
    private Scoreboard scoreboard;
    private VraagManager vraagManager;

    public TrivialPursuitController() {
        scoreboard = new Scoreboard();
        vraagManager = new VraagManager();

    }
    // implementatie logica van de
    // applicatie ahv methods
    // implementatie van de nodige Getters
    // implementatie van de nodige Setters

    public void addScore(String spelernaam) {
        scoreboard.addScore(spelernaam);
    }

    public List<String> getScores() {
        return scoreboard.getScores();
    }

    public void addVraag(Vraag vraag) {
        vraagManager.addVraag(vraag);
    }

    public List<Vraag> loadVraag(Kleur category) {
        return vraagManager.loadVraag(category);
    }

    public void addWin(String spelernaam) {
        scoreboard.addWin(spelernaam);
    }
}