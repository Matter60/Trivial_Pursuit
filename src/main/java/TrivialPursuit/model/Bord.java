package TrivialPursuit.model;

import TrivialPursuit.model.Kleur;

public class Bord {
    private Veld[] velden = new Veld[43]; // 43 inclusief het startveld (0)

    public Bord() {
        int[][] coordinaten = {
                {270, 271}, {195, 230}, {146, 200}, {99, 170}, {47, 142}, {86, 90}, {141, 48}, {200, 24}, {269, 13},
                {334, 24}, {392, 48}, {444, 90}, {490, 144}, {515, 207}, {523, 264}, {514, 335}, {490, 396}, {447, 449},
                {402, 489}, {336, 516}, {270, 527}, {201, 517}, {138, 490}, {87, 447}, {42, 398}, {20, 332}, {14, 263},
                {20, 200}, {195, 306}, {144, 340}, {99, 365}, {271, 344}, {265, 406}, {262, 468}, {334, 306}, {387, 343},
                {429, 366}, {338, 226}, {390, 196}, {441, 171}, {266, 71}, {271, 134}, {272, 188}
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
