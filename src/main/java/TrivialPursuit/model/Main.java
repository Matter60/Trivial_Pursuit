package TrivialPursuit.model;

import java.util.*;

public class Main {


    public static void main(String[] args) {


        Bord bord = new Bord();

        //bord.printVelden();


        List<Veld> bestemmingen = bord.getMogelijkeBestemmingen(270,271,1);

        System.out.println("Mogelijke bestemmingen:");
        for (Veld veld : bestemmingen) {
            System.out.println(veld);
        }



    }
} 