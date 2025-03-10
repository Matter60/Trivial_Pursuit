package TrivialPursuit.model;

import java.util.List;
import java.util.ArrayList;

public class TrivialPursuitController {
    private Scoreboard scoreboard;
    private VraagManager vraagManager;
    private Dobbelsteen dobbelsteen;
    private Game game;
    private Bord bord;

    public TrivialPursuitController() {
        scoreboard = new Scoreboard();
        vraagManager = new VraagManager();
        dobbelsteen = new Dobbelsteen();
        game = new Game();
        bord = new Bord();
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

    public List<Vraag> laadVraag(Kleur category) {
        return vraagManager.laadVraag(category);
    }

    public void addWin(String spelernaam) {
        scoreboard.addWin(spelernaam);
    }

    public int worp() {
        return dobbelsteen.worp();
    }

    public void voegSpelerToe(String naam, Kleur kleur) {
        game.voegSpelerToe(naam, kleur);
    }

    public void startSpel() {
        game.startSpel();
    }

    // Game methodes
    public int gooiDobbelsteen() {
        return game.gooiDobbelsteen();
    }

    public void verplaatsHuidigeSpeler(int aantalStappen) {
        game.verplaatsHuidigeSpeler(aantalStappen);
    }

    public void volgendeSpeler() {
        game.volgendeSpeler();
    }

    public Speler getHuidigeSpeler() {
        return game.getHuidigeSpeler();
    }

    public int getSpelerPositie(Speler speler) {
        return game.getSpelerPositie(speler);
    }

    public Speler getWinnaar() {
        return game.getWinnaar();
    }

    public List<Speler> getSpelers() {
        return game.getSpelers();
    }

    public boolean isSpelAfgelopen() {
        return game.isSpelAfgelopen();
    }

    // Nieuwe methodes voor spellogica
    public List<Integer> getMogelijkeBestemmingen(int worp) {
        return game.getMogelijkeBestemmingen(worp);
    }

    public Kleur getVeldKleur(int positie) {
        return game.getVeldKleur(positie);
    }

    public boolean isPartjeVeld(int positie) {
        return game.isPartjeVeld(positie);
    }

    public boolean isRollAgainVeld(int positie) {
        return game.isRollAgainVeld(positie);
    }

    public void geefPartje(Speler speler, Kleur kleur) {
        game.geefPartje(speler, kleur);
    }

    public List<Kleur> getSpelerPartjes(Speler speler) {
        return game.getSpelerPartjes(speler);
    }

    public boolean checkAntwoord(Vraag vraag, int selectedIndex, Speler speler, int positie) {
        boolean correct = vraag.checkAntwoord(selectedIndex);

        if (correct) {
            // Voeg score toe voor juist antwoord
            addScore(speler.getNaam());

            if (isPartjeVeld(positie)) {
                // Check eerst of de speler het partje al heeft
                Kleur veldKleur = getVeldKleur(positie);
                if (!speler.heeftPartje(veldKleur)) {
                    // Geef partje aan speler
                    geefPartje(speler, veldKleur);
                    return true;
                }
            }
        }
        return false;
    }

    public int[] getCoordinaten(int positie) {
        return bord.getCoordinaten(positie);
    }
}
