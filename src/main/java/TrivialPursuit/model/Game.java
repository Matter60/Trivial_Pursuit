package TrivialPursuit.model;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private List<Speler> spelers;
    private Bord bord;
    private Dobbelsteen dobbelsteen;
    private VraagManager vraagManager;
    private Scoreboard scoreboard;
    private int huidigeSpelerIndex;
    private Speler winnaar;

    public Game() {
        this.spelers = new ArrayList<>();
        this.bord = new Bord();
        this.dobbelsteen = new Dobbelsteen();
        this.vraagManager = new VraagManager();
        this.scoreboard = new Scoreboard();
        this.huidigeSpelerIndex = 0;
        this.winnaar = null;
    }

    public void voegSpelerToe(String naam, Kleur kleur) {
        Speler speler = new Speler(naam, kleur);
        spelers.add(speler);
    }

    public void startSpel() {
        if (spelers.size() >= 2) {
            huidigeSpelerIndex = 0;
        } else {
            throw new IllegalArgumentException("Er zijn minimaal 2 spelers nodig om het spel te starten!");
        }
    }

    public int gooiDobbelsteen() {
        return dobbelsteen.worp();

        // op 1 zetten testmode
    }
    // Bijvoorbeeld: berekenBereikbareVeldIndices(3) -> [4, 8, 12] (bereikbare velden na 3 stappen)

    public List<Integer> berekenBereikbareVeldIndices(int worp) {
        int huidigePositie = getHuidigeSpeler().getPositie();
        return bord.berekenBereikbareVeldIndices(huidigePositie, worp);
    }

    // Verplaats de huidige speler naar de nieuwe positie
    public void verplaatsHuidigeSpeler(int nieuwePositie) {
        Speler huidigeSpeler = getHuidigeSpeler();
        huidigeSpeler.setPositie(nieuwePositie);

        // Check voor winnaar (alle partjes verzameld en op middenvak)
        if (nieuwePositie == bord.getMiddenVakIndex() && huidigeSpeler.heeftAllePartjes()) {
            winnaar = huidigeSpeler;
            scoreboard.addWin(huidigeSpeler.getNaam());
        }
    }

    // Controleer of het antwoord correct is
    public boolean checkAntwoord(Vraag vraag, int selectedIndex, Speler speler, int positie) {
        boolean correct = vraag.checkIndex(selectedIndex);

        if (correct) {
            // Voeg score toe voor juist antwoord
            scoreboard.addScore(speler.getNaam());

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

    // Ga naar de volgende speler modulo gebruiken we omdat we terug naar de eerste speler willen als we aan het einde van de lijst zijn
    // Bv: als we 3 spelers hebben en we zijn aan het einde van de lijst, dan gaan we naar de eerste speler 3 % 3 = 0
    public void volgendeSpeler() {
        huidigeSpelerIndex = (huidigeSpelerIndex + 1) % spelers.size();
    }

    // Haal de huidige speler op
    public Speler getHuidigeSpeler() {
        if (spelers.isEmpty()) {
            throw new IllegalArgumentException("Er zijn geen spelers in het spel!");
        }
        return spelers.get(huidigeSpelerIndex);
    }

    // Haal de positie van een speler op
    public int getSpelerPositie(Speler speler) {
        return speler.getPositie();
    }

    // Stel de positie van een speler in
    public void setSpelerPositie(Speler speler, int positie) {
        speler.setPositie(positie);
    }

    // Haal de kleur van een veld op
    public Kleur getVeldKleur(int positie) {
        return bord.getVeldKleur(positie);
    }

    // Controleer of een veld een partjeveld is
    public boolean isPartjeVeld(int positie) {
        return bord.isPartjeVeld(positie);
    }

    // Controleer of een veld een opnieuw gooienveld is
    public boolean isRollAgainVeld(int positie) {
        // Als het het middenveld is (positie 0)
        if (positie == bord.getMiddenVakIndex()) {
            // Alleen opnieuw gooien als de speler NIET alle partjes heeft
            return !getHuidigeSpeler().heeftAllePartjes();
        }
        // Anders, gebruik de normale check voor opnieuw gooien velden
        return bord.isRollAgainVeld(positie);
    }

    // Geef een partje aan een speler
    public void geefPartje(Speler speler, Kleur kleur) {
        speler.voegPartjeToe(kleur);
    }

    // Haal de partjes van een speler op
    public List<Kleur> getSpelerPartjes(Speler speler) {
        return speler.getPartjes();
    }

    // Haal de coordinaten van een veld op
    public int[] getCoordinaten(int positie) {
        return bord.getCoordinaten(positie);
    }

    // Laad een vraag van een bepaalde categorie
    public List<Vraag> laadVraag(Kleur category) {
        return vraagManager.laadVraag(category);
    }

    // Controleer of een speler alle partjes heeft
    public boolean heeftSpelerAllePartjes(Speler speler) {
        return speler.heeftAllePartjes();
    }

    // Haal de winnaar op
    public Speler getWinnaar() {
        return winnaar;
    }

    // Controleer of het spel afgelopen is
    public boolean isSpelAfgelopen() {
        return winnaar != null;
    }

    // Haal de spelers op
    public List<Speler> getSpelers() {
        return new ArrayList<>(spelers);
    }

    // Controleer of een veld het middenveld is
    public boolean isMiddenVak(int positie) {
        return positie == bord.getMiddenVakIndex();
    }
}
