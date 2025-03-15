package TrivialPursuit.model;

public class Dobbelsteen {
    private int laatsteWorp;

    public Dobbelsteen() {
        this.laatsteWorp = 0;
    }

    public int worp() {
        laatsteWorp = (int) (Math.random() * 6) + 1;
        return laatsteWorp;
    }

}