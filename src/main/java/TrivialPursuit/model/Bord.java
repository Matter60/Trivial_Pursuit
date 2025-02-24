package TrivialPursuit.model;

import TrivialPursuit.model.Kleur;

public class Bord {
    private Veld[] velden = new Veld[43]; // 43 inclusief het startveld (0)

    public Bord() {
        int[][] coordinaten = {
                {288, 287}, {218, 245}, {164, 214}, {117, 186}, {65, 158}, {104, 106}, {159, 64}, {218, 40}, {287, 29},
                {352, 40}, {410, 64}, {462, 106}, {508, 160}, {533, 223}, {541, 280}, {532, 351}, {508, 412}, {465, 465},
                {420, 505}, {354, 532}, {288, 543}, {219, 533}, {156, 506}, {105, 463}, {60, 414}, {38, 348}, {32, 279},
                {38, 216}, {213, 322}, {162, 356}, {117, 381}, {289, 360}, {285, 422}, {284, 484}, {352, 322}, {405, 359},
                {447, 382}, {356, 242}, {408, 212}, {459, 187}, {284, 87}, {289, 150}, {290, 204}
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
