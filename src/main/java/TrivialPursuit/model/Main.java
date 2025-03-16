package TrivialPursuit.model;


import java.util.List;

public class Main {

    public static void main(String[] args) {

        Bord bord = new Bord();

        List<Integer> resultaat = bord.berekenBereikbareVeldIndexen(0, 2);

        System.out.println(resultaat);



    }
}