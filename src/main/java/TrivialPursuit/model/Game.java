package TrivialPursuit.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class Game {
    private List<Speler> spelers;
    private Bord bord;
    private Dobbelsteen dobbelsteen;
    private int huidigeSpelerIndex;
    private Map<Speler, Integer> spelerPosities;
    private Speler winnaar;
    private GameStatus status;

    public enum GameStatus {
        NIET_GESTART,
        BEZIG,
        AFGELOPEN
    }

    public Game() {
        this.spelers = new ArrayList<>();
        this.bord = new Bord();
        this.dobbelsteen = new Dobbelsteen();
        this.huidigeSpelerIndex = 0;
        this.spelerPosities = new HashMap<>();
        this.status = GameStatus.NIET_GESTART;
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
            status = GameStatus.BEZIG;
            huidigeSpelerIndex = 0;
        } else {
            throw new IllegalStateException("Er zijn minimaal 2 spelers nodig om het spel te starten!");
        }
    }

    public int gooiDobbelsteen() {
        if (status != GameStatus.BEZIG) {
            throw new IllegalStateException("Het spel is nog niet begonnen of is al afgelopen!");
        }
        return dobbelsteen.worp();
    }

    public List<Integer> getMogelijkeBestemmingen(int worp) {
        List<Integer> bestemmingen = new ArrayList<>();
        int huidigePositie = getSpelerPositie(getHuidigeSpeler());

        // Voeg alle mogelijke posities toe die worp stappen verwijderd zijn
        for (int i = 0; i < bord.getAantalVelden(); i++) {
            if (bord.isGeldigeZet(huidigePositie, i, worp)) {
                bestemmingen.add(i);
            }
        }

        return bestemmingen;
    }

    public void verplaatsHuidigeSpeler(int nieuwePositie) {
        if (status != GameStatus.BEZIG) {
            throw new IllegalStateException("Het spel is nog niet begonnen of is al afgelopen!");
        }

        Speler huidigeSpeler = getHuidigeSpeler();
        spelerPosities.put(huidigeSpeler, nieuwePositie);

        // Check of speler op een categorie vakje staat
        Kleur vakKleur = bord.getVeldKleur(nieuwePositie);
        if (vakKleur != null && !huidigeSpeler.heeftPartje(vakKleur)) {
            // Hier later vraag logica toevoegen
            // Als vraag goed beantwoord, voeg partje toe
            // huidigeSpeler.voegPartjeToe(vakKleur);
        }

        // Check voor winnaar (alle partjes verzameld en op middenvak)
        if (huidigeSpeler.heeftAllePartjes() && nieuwePositie == bord.getMiddenVakIndex()) {
            winnaar = huidigeSpeler;
            status = GameStatus.AFGELOPEN;
        }
    }

    public void volgendeSpeler() {
        if (status != GameStatus.BEZIG) {
            throw new IllegalStateException("Het spel is nog niet begonnen of is al afgelopen!");
        }
        huidigeSpelerIndex = (huidigeSpelerIndex + 1) % spelers.size();
    }

    public Speler getHuidigeSpeler() {
        return spelers.get(huidigeSpelerIndex);
    }

    public int getSpelerPositie(Speler speler) {
        return spelerPosities.get(speler);
    }

    public Kleur getVeldKleur(int positie) {
        return bord.getVeldKleur(positie);
    }

    public boolean isPartjeVeld(int positie) {
        return bord.isPartjeVeld(positie);
    }

    public boolean isRollAgainVeld(int positie) {
        return bord.isRollAgainVeld(positie);
    }

    public void geefPartje(Speler speler, Kleur kleur) {
        speler.voegPartjeToe(kleur);
        // Check voor winnaar
        if (speler.heeftAllePartjes()) {
            status = GameStatus.AFGELOPEN;
        }
    }

    public List<Kleur> getSpelerPartjes(Speler speler) {
        return speler.getPartjes();
    }

    public GameStatus getStatus() {
        return status;
    }

    public Speler getWinnaar() {
        return winnaar;
    }

    public List<Speler> getSpelers() {
        return new ArrayList<>(spelers); // Retourneer een kopie voor encapsulatie
    }

    public boolean isSpelAfgelopen() {
        return status == GameStatus.AFGELOPEN;
    }

    public Bord getBord() {
        return bord;
    }
}
