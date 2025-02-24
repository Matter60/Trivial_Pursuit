package TrivialPursuit.model;

import java.util.*;

public class Main {


    public static void main(String[] args) {
        VraagManager vraagManager = new VraagManager();
        Scoreboard scoreboard = new Scoreboard();
        Speler speler = new Speler("Test", Kleur.BLUE);

        scoreboard.addScore(speler.getNaam());



    }
} 