package TrivialPursuit.model;

import java.util.ArrayList;
import java.util.List;

public class Bord {
        private List<Veld> velden = new ArrayList<>();

        private int[][] coordinaten = {
                        { 270, 271 }, { 195, 230 }, { 146, 200 }, { 99, 170 }, { 47, 142 }, { 86, 90 }, { 141, 48 },
                        { 200, 24 }, { 269, 13 },
                        { 334, 24 }, { 392, 48 }, { 444, 90 }, { 490, 144 }, { 515, 207 }, { 523, 264 }, { 514, 335 },
                        { 490, 396 }, { 447, 449 },
                        { 402, 489 }, { 336, 516 }, { 270, 527 }, { 201, 517 }, { 138, 490 }, { 87, 447 }, { 42, 398 },
                        { 20, 332 }, { 14, 263 },
                        { 20, 200 }, { 195, 306 }, { 144, 340 }, { 99, 365 }, { 271, 344 }, { 265, 406 }, { 262, 468 },
                        { 334, 306 }, { 387, 343 },
                        { 429, 366 }, { 338, 226 }, { 390, 196 }, { 441, 171 }, { 266, 71 }, { 271, 134 }, { 272, 188 }
        };

        private Kleur[] veldKleuren = {
                        null, Kleur.GREEN, Kleur.BROWN, Kleur.PINK, Kleur.YELLOW, Kleur.WHITE, Kleur.PINK, Kleur.ORANGE,
                        Kleur.BROWN, Kleur.WHITE, Kleur.GREEN,
                        Kleur.BLUE, Kleur.ORANGE, Kleur.WHITE, Kleur.YELLOW, Kleur.GREEN, Kleur.BLUE, Kleur.WHITE,
                        Kleur.ORANGE, Kleur.BLUE, Kleur.GREEN,
                        Kleur.WHITE, Kleur.YELLOW, Kleur.BROWN, Kleur.PINK, Kleur.WHITE, Kleur.PINK, Kleur.BROWN,
                        Kleur.ORANGE, Kleur.BROWN, Kleur.GREEN,
                        Kleur.YELLOW, Kleur.PINK, Kleur.BLUE, Kleur.BROWN, Kleur.YELLOW, Kleur.ORANGE, Kleur.PINK,
                        Kleur.GREEN, Kleur.BLUE, Kleur.BLUE,
                        Kleur.YELLOW, Kleur.ORANGE,
        };

        private int[] partjeVelden = { 4, 8, 12, 16, 20, 24 };
        private int[] opnieuwGooienVelden = { 5, 9, 13, 17, 21, 25 };
        private int[][] mogelijkeOptiesIndex = {
                {1,42,37,34,31,28} //0
                ,{2,0} //1
                ,{3,1} //2
                ,{4,2} //3
                ,{27,3,5} //4
                ,{4,6} //5
                ,{5,7} //6
                ,{6,8} //7
                ,{7,40,9} //8
                ,{8,10} //9
                ,{9,11} //10
                ,{10,12} //11
                ,{11,39,13} //12
                ,{12,14} //13
                ,{13,15} //14
                ,{14,16} //15
                ,{15,36,17} //16
                ,{16,18} //17
                ,{17,19} //18
                ,{18,20} //19
                ,{19,33,21} //20
                ,{20,22} //21
                ,{21,23} //22
                ,{22,24} //23
                ,{23,30,25} //24
                ,{24,26} //25
                ,{25,27} //26
                ,{26,4}, //27
                {29,0} //28
                ,{30,28} //29
                ,{24,29} //30
        };
        public Bord() {

                for (int i = 0; coordinaten.length > i; i++) {
                        boolean isStartVeld = (i == 0);
                        boolean isPartjeVeld = (i == 4 || i == 8 || i == 12 || i == 16 || i == 20 || i == 24);
                        boolean isOpnieuwGooienVeld = (i == 5 || i == 9 || i == 13 || i == 17 || i == 21 || i == 25);
                        Kleur kleur = veldKleuren[i];

                   //     Veld nieuwVeld = new Veld(kleur, isPartjeVeld, isOpnieuwGooienVeld, isStartVeld,
                     //                   coordinaten[i][0], coordinaten[i][1]);

                     //  velden.add(nieuwVeld);
                }

        }

        public void printVelden() {
                for (Veld veld : velden) {
                        System.out.println("Veld op (" + veld.getX() + ", " + veld.getY() + ") - "
                                        + "Kleur: " + veld.getKleur() + ", "
                                        + (veld.isStartVeld() ? "Startveld, " : "")
                                        + (veld.isPartjeVeld() ? "Partjeveld, " : "")
                                        + (veld.isOpnieuwGooienVeld() ? "Opnieuw gooien veld" : ""));
                }
        }

}
