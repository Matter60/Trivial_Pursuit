package TrivialPursuit.model;

import TrivialPursuit.model.Kleur;

public class Bord {
    private Veld[] velden = new Veld[43]; // 43 inclusief het startveld (0)

    public Bord() {
        int[][] coordinaten = {
                {576, 575}, {436, 491}, {329, 429}, {235, 373}, {130, 317}, {208, 213}, {318, 129}, {436, 81}, {574, 59},
                {705, 81}, {821, 128}, {924, 213}, {1016, 320}, {1067, 446}, {1083, 561}, {1064, 702}, {1016, 824}, {930, 931},
                {840, 1011}, {708, 1064}, {575, 1086}, {439, 1067}, {311, 1012}, {211, 926}, {120, 828}, {76, 696}, {63, 559},
                {76, 433}, {427, 645}, {325, 712}, {235, 763}, {578, 720}, {571, 844}, {568, 968}, {704, 645}, {810, 719},
                {895, 765}, {712, 484}, {817, 424}, {919, 375}, {568, 175}, {579, 301}, {580, 409}
        };

        // Startveld (0)
        velden[0] = new Veld(null, false, false, true, coordinaten[0][0], coordinaten[0][1]);

        // Kleurvolgorde op het bord
       Kleur[] kleuren = {Kleur.BLUE, Kleur.BROWN, Kleur.PINK, Kleur.YELLOW, Kleur.BLUE, Kleur.ORANGE};

        // Velden maken
        for (int i = 1; i <= 42; i++) {
            boolean isPartjeVeld = (i % 6 == 4); // Partjevelden om de 6 stappen
            boolean isOpnieuwGooienVeld = (i % 7 == 0); // Opnieuw gooien op specifieke plekken
            velden[i] = new Veld(kleuren[i % 6], isPartjeVeld, isOpnieuwGooienVeld, false, coordinaten[i][0], coordinaten[i][1]);
        }

        // Velden verbinden
        for (int i = 1; i < 42; i++) {
            velden[i].setRechterVeld(velden[i + 1]);
            velden[i + 1].setLinkerVeld(velden[i]);
        }

        // Verbinding met startveld
        velden[0].setOnderVeld(velden[1]);
        velden[1].setLinkerVeld(velden[0]);
    }

    public Veld getVeld(int index) {
        return (index >= 0 && index < velden.length) ? velden[index] : null;
    }
}
