package TrivialPursuit.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Bord {
    // Lijst van alle velden op het bord

    private List<Veld> velden = new ArrayList<>();
    // Constanten voor bordgrootte
    private static final int AANTAL_VAKJES = 43;
    private static final int MIDDEN_VAK_INDEX = 0;
    private static final int AANTAL_VELDEN = 42;

    // X,Y coördinaten voor elk veld
    private int[][] coordinaten = {
        {270, 271}, {195, 230}, {146, 200}, {99, 170}, {47, 142}, {86, 90}, {141, 48},
        {200, 24}, {269, 13}, {334, 24}, {392, 48}, {444, 90}, {490, 144}, {515, 207},
        {523, 264}, {514, 335}, {490, 396}, {447, 449}, {402, 489}, {336, 516},
        {270, 527},
        {201, 517}, {138, 490}, {87, 447}, {42, 398}, {20, 332}, {14, 263}, {20, 200},
        {195, 306}, {144, 340}, {99, 365}, {271, 344}, {265, 406}, {262, 468}, {334, 306},
        {387, 343}, {429, 366}, {338, 226}, {390, 196}, {441, 171}, {266, 71}, {271, 134},
        {272, 188}
    };

    // Kleur van elk veld
    private Kleur[] veldKleurenArray = {
        Kleur.WIT, Kleur.GROEN, Kleur.BRUIN, Kleur.ROZE, Kleur.GEEL, Kleur.WIT, Kleur.ROZE,
        Kleur.ORANJE,
        Kleur.BRUIN, Kleur.WIT, Kleur.GROEN, Kleur.BLAUW, Kleur.ORANJE, Kleur.WIT, Kleur.GEEL,
        Kleur.GROEN, Kleur.BLAUW, Kleur.WIT, Kleur.ORANJE, Kleur.BLAUW, Kleur.GROEN, Kleur.WIT,
        Kleur.GEEL, Kleur.BRUIN, Kleur.ROZE, Kleur.WIT, Kleur.ROZE, Kleur.BRUIN, Kleur.ORANJE,
        Kleur.BRUIN, Kleur.GROEN, Kleur.GEEL, Kleur.ROZE, Kleur.BLAUW, Kleur.BRUIN, Kleur.GEEL,
        Kleur.ORANJE, Kleur.ROZE, Kleur.GROEN, Kleur.BLAUW, Kleur.BLAUW, Kleur.GEEL, Kleur.ORANJE
    };

    // Aangrenzende velden voor elk veld
    private int[][] veldenDieErnaastLiggen = {
        {1, 42, 37, 34, 31, 28}, {2, 0}, {3, 1}, {4, 2}, {27, 3, 5}, {4, 6}, {5, 7},
        {6, 8}, {7, 40, 9}, {8, 10}, {9, 11}, {10, 12}, {11, 39, 13}, {12, 14},
        {13, 15}, {14, 16}, {15, 36, 17}, {16, 18}, {17, 19}, {18, 20}, {19, 33, 21},
        {20, 22}, {21, 23}, {22, 24}, {23, 30, 25}, {24, 26}, {25, 27}, {26, 4},
        {29, 0}, {30, 28}, {24, 29}, {0, 32}, {31, 33}, {32, 20}, {0, 35}, {34, 36},
        {35, 16}, {0, 38}, {37, 39}, {38, 12}, {8, 41}, {40, 42}, {41, 0}
    };

    // Initialiseer het bord
    public Bord() {
        initializeVelden();
    }

    // Maak alle velden aan en koppel buren
    private void initializeVelden() {
        // Maak alle velden aan
        for (int i = 0; i < coordinaten.length; i++) {
            boolean isStartVeld = (i == 0);
            boolean isPartjeVeld = (i == 4 || i == 8 || i == 12 || i == 16 || i == 20 || i == 24);
            boolean isOpnieuwGooienVeld = (i == 0 || i == 5 || i == 9 || i == 13 || i == 17 || i == 21
                    || i == 25);
            Kleur kleur = veldKleurenArray[i];

            Veld nieuwVeld = new Veld(kleur, isPartjeVeld, isOpnieuwGooienVeld, isStartVeld,
                    coordinaten[i][0], coordinaten[i][1], new ArrayList<>());
            velden.add(nieuwVeld);
        }

        // Koppel buren aan elk veld
        for (int i = 0; i < velden.size(); i++) {
            Veld huidigVeld = velden.get(i);
            List<Veld> buren = new ArrayList<>();
            for (int buurIndex : veldenDieErnaastLiggen[i]) {
                buren.add(velden.get(buurIndex));
            }
            huidigVeld.setVeldenDieErnaastLiggen(buren);
        }
    }

    // Bereken bereikbare velden binnen aantal zetten vanaf x,y positie
    // Bijvoorbeeld: berekenBereikbareVelden(270, 271, 2) -> [Veld(4), Veld(8), Veld(12)]
    public List<Veld> berekenBereikbareVelden(int spelerX, int spelerY, int aantalZetten) {
        Veld startVeld = vindVeldOpCoordinaten(spelerX, spelerY);
        if (startVeld == null) {
            System.out.println("Geen veld gevonden op deze coördinaten!");
            return new ArrayList<>();
        }

        List<Veld> velden = new ArrayList<>();
        velden.add(startVeld);
        Set<Veld> bezocht = new HashSet<>();
        bezocht.add(startVeld);

        // Bereken alle bereikbare velden binnen aantal zetten
        for (int i = 0; i < aantalZetten; i++) {
            List<Veld> nieuweVelden = new ArrayList<>();
            for (Veld veld : velden) {
                for (Veld buur : veld.getVeldenDieErnaastLiggen()) {
                    if (!bezocht.contains(buur)) {
                        nieuweVelden.add(buur);
                        bezocht.add(buur);
                    }
                }
            }
            velden = nieuweVelden;
        }

        return velden;
    }

    // Bereken bereikbare veldindices binnen aantal zetten vanaf startpositie
    // Bijvoorbeeld: berekenBereikbareVeldIndices(0, 2) -> [4, 8, 12]
    public List<Integer> berekenBereikbareVeldIndices(int startPositie, int aantalZetten) {
        if (startPositie < 0 || startPositie >= velden.size()) {
            return new ArrayList<>();
        }

        // Converteer startpositie naar coördinaten en bereken bestemmingen
        Veld startVeld = velden.get(startPositie);
        List<Veld> mogelijkeVelden = berekenBereikbareVelden(startVeld.getX(), startVeld.getY(), aantalZetten);

        // Converteer velden terug naar indices
        List<Integer> indices = new ArrayList<>();
        for (Veld veld : mogelijkeVelden) {
            for (int i = 0; i < velden.size(); i++) {
                if (velden.get(i) == veld) {
                    indices.add(i);
                    break;
                }
            }
        }

        return indices;
    }

    // Zoek veld op basis van x,y coördinaten
    private Veld vindVeldOpCoordinaten(int x, int y) {
        for (Veld veld : velden) {
            if (veld.getX() == x && veld.getY() == y) {
                return veld;
            }
        }
        return null;
    }


    public int getMiddenVakIndex() {
        return MIDDEN_VAK_INDEX;
    }

    // Haal kleur op van specifiek veld
    public Kleur getVeldKleur(int positie) {
        if (positie >= 0 && positie < veldKleurenArray.length) {
            return veldKleurenArray[positie];
        }
        return null;
    }

    // Haal veld op basis van index
    public Veld getVeld(int index) {
        if (index >= 0 && index < velden.size()) {
            return velden.get(index);
        }
        return null;
    }


    // Check of veld een partjeveld is
    public boolean isPartjeVeld(int positie) {
        return positie == 4 || positie == 8 || positie == 12
                || positie == 16 || positie == 20 || positie == 24;
    }

    // Check of veld een opnieuw-gooien veld is
    public boolean isRollAgainVeld(int positie) {
        return positie == 5 || positie == 9 || positie == 13
                || positie == 17 || positie == 21 || positie == 25;
    }

    // Haal coördinaten op van een veld
    public int[] getCoordinaten(int positie) {
        if (positie >= 0 && positie < coordinaten.length) {
            return coordinaten[positie];
        }
        return null;
    }

}
