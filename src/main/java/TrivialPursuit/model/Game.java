package TrivialPursuit.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Game {

    private List<Speler> spelers;
    private Bord bord;
    private Dobbelsteen dobbelsteen;
    private VraagManager vraagManager;
    private Scoreboard scoreboard;
    private int huidigeSpelerIndex;
    private Map<Speler, Integer> spelerPosities;
    private Speler winnaar;

    public Game() {
        this.spelers = new ArrayList<>();
        this.bord = new Bord();
        this.dobbelsteen = new Dobbelsteen();
        this.vraagManager = new VraagManager();
        this.scoreboard = new Scoreboard();
        this.huidigeSpelerIndex = 0;
        this.spelerPosities = new HashMap<>();
        this.winnaar = null;
    }

    public void voegSpelerToe(String naam, Kleur kleur) {
        Speler speler = new Speler(naam, kleur);
        spelers.add(speler);
        spelerPosities.put(speler, 0); // Start op positie 0
    }

    public void startSpel() {
        if (spelers.size() >= 2) {
            System.out.println("Het spel begint!");
            for (Speler speler : spelers) {
                System.out.println("Speler: " + speler.getNaam() + " - Kleur: " + speler.getSpelerKleur());
            }
            huidigeSpelerIndex = 0;
        } else {
            throw new IllegalArgumentException("Er zijn minimaal 2 spelers nodig om het spel te starten!");
        }
    }

    public int gooiDobbelsteen() {
        return dobbelsteen.worp();
    }

    public List<Integer> getMogelijkeBestemmingen(int worp) {
        int huidigePositie = getSpelerPositie(getHuidigeSpeler());
        return bord.getMogelijkeBestemmingsPosities(huidigePositie, worp);
    }

    public void verplaatsHuidigeSpeler(int nieuwePositie) {
        Speler huidigeSpeler = getHuidigeSpeler();
        spelerPosities.put(huidigeSpeler, nieuwePositie);

        // Check voor winnaar (alle partjes verzameld en op middenvak)
        if (huidigeSpeler.heeftAllePartjes() && nieuwePositie == bord.getMiddenVakIndex()) {
            winnaar = huidigeSpeler;
            scoreboard.addWin(huidigeSpeler.getNaam());
        }
    }

    public boolean checkAntwoord(Vraag vraag, int selectedIndex, Speler speler, int positie) {
        boolean correct = vraag.checkAntwoord(selectedIndex);
        boolean partjeVerdiend = false;

        if (correct) {
            scoreboard.addScore(speler.getNaam());

            if (isPartjeVeld(positie)) {
                Kleur veldKleur = getVeldKleur(positie);
                if (!speler.heeftPartje(veldKleur)) {
                    geefPartje(speler, veldKleur);
                    partjeVerdiend = true;
                }
            }
        }
        return partjeVerdiend;
    }

    public void volgendeSpeler() {
        huidigeSpelerIndex = (huidigeSpelerIndex + 1) % spelers.size();
    }

    // Getters

    public Speler getHuidigeSpeler() {
        if (spelers.isEmpty()) {
            throw new IllegalArgumentException("Er zijn geen spelers in het spel!");
        }
        return spelers.get(huidigeSpelerIndex);
    }

    public int getSpelerPositie(Speler speler) {
        return spelerPosities.getOrDefault(speler, 0);
    }

    public void setSpelerPositie(Speler speler, int positie) {
        spelerPosities.put(speler, positie);
    }

    public Kleur getVeldKleur(int positie) {
        return bord.getVeldKleur(positie);
    }

    public boolean isPartjeVeld(int positie) {
        return bord.isPartjeVeld(positie);
    }

    public boolean isRollAgainVeld(int positie) {
        // Als het het middenveld is (positie 0)
        if (positie == bord.getMiddenVakIndex()) {
            // Alleen opnieuw gooien als de speler NIET alle partjes heeft
            return !getHuidigeSpeler().heeftAllePartjes();
        }
        // Anders, gebruik de normale check voor opnieuw gooien velden
        return bord.isRollAgainVeld(positie);
    }

    public void geefPartje(Speler speler, Kleur kleur) {
        speler.voegPartjeToe(kleur);
    }

    public List<Kleur> getSpelerPartjes(Speler speler) {
        return speler.getPartjes();
    }

    public int[] getCoordinaten(int positie) {
        return bord.getCoordinaten(positie);
    }

    public List<Vraag> laadVraag(Kleur category) {
        return vraagManager.laadVraag(category);
    }

    public boolean heeftSpelerAllePartjes(Speler speler) {
        return speler.heeftAllePartjes();
    }

    public Speler getWinnaar() {
        return winnaar;
    }

    public boolean isSpelAfgelopen() {
        return winnaar != null;
    }

    public List<Speler> getSpelers() {
        return new ArrayList<>(spelers);
    }

    public boolean isMiddenVak(int positie) {
        return positie == bord.getMiddenVakIndex();
    }
}
