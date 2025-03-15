package TrivialPursuit.model;

import java.util.List;
import java.io.FileNotFoundException;

public class TrivialPursuitController {

    private Scoreboard scoreboard;
    private VraagManager vraagManager;
    private Dobbelsteen dobbelsteen;
    private Game game;
    private Bord bord;
    private FileManager fileManager;

    public TrivialPursuitController() {
        scoreboard = new Scoreboard();
        vraagManager = new VraagManager();
        dobbelsteen = new Dobbelsteen();
        game = new Game();
        bord = new Bord();
        fileManager = new FileManager();
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

    public List<Vraag> laadVraag(Kleur categorie) {
        return vraagManager.laadVraag(categorie);
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
        return game.checkAntwoord(vraag, selectedIndex, speler, positie);
    }

    public int[] getCoordinaten(int positie) {
        return bord.getCoordinaten(positie);
    }

    public boolean heeftSpelerAllePartjes(Speler speler) {
        return game.heeftSpelerAllePartjes(speler);
    }

    public boolean isMiddenVak(int positie) {
        return game.isMiddenVak(positie);
    }

    /**
     * Slaat het huidige spel op
     *
     * @param filePath het pad waar het spel moet worden opgeslagen
     * @return true als het opslaan is gelukt, anders false
     */
    public boolean saveGame(String filePath) {
        return fileManager.saveGame(filePath, game);
    }

    /**
     * Laadt een opgeslagen spel
     *
     * @param filePath het pad van het spel dat moet worden geladen
     * @return true als het laden is gelukt, anders false
     */
    public boolean loadGame(String filePath) throws FileNotFoundException {
        try {
            Game loadedGame = fileManager.loadGame(filePath);
            if (loadedGame != null) {
                this.game = loadedGame;
                return true;
            }
            return false;
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("Bestand niet gevonden: " + e.getMessage());
        }
    }

    public void resetSpel() {
        this.game = new Game();
    }

    // Bereken bereikbare veldindices na dobbelsteenworp
    // Bijvoorbeeld: berekenBereikbareVeldIndices(3) -> [4, 8, 12] (bereikbare
    // velden na 3 stappen)
    public List<Integer> berekenBereikbareVeldIndices(int worp) {
        return game.berekenBereikbareVeldIndices(worp);
    }

}
