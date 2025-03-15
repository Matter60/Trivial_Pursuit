package TrivialPursuit.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Scoreboard {

    private static final String SCORES_FILE = "data/scores.txt";
    private Map<String, Integer> scores;
    private Map<String, Integer> wins;

    public Scoreboard() {
        this.scores = new HashMap<>();
        this.wins = new HashMap<>();
        laadScores();
    }

    // Voeg een score toe aan een speler
    public void addScore(String spelerNaam) {
        if (scores.containsKey(spelerNaam)) {
            scores.put(spelerNaam, scores.get(spelerNaam) + 1);
        } else {
            scores.put(spelerNaam, 1);
        }
        saveScores();
    }

    // Voeg een win toe aan een speler
    public void addWin(String spelerNaam) {
        if (wins.containsKey(spelerNaam)) {
            wins.put(spelerNaam, wins.get(spelerNaam) + 1);
        } else {
            wins.put(spelerNaam, 1);
        }
        saveScores();
    }

    // Haal de scores op
    public List<String> getScores() {
        if (scores.isEmpty()) {
            return List.of("Geen scores beschikbaar");
        }

        // Maak een lijst van spelers (keys) en sorteer deze op basis van hun scores (values)
        List<String> spelers = new ArrayList<>(scores.keySet());
        spelers.sort((s1, s2) -> scores.get(s2).compareTo(scores.get(s1))); // Sorteer aflopend

        List<String> formateerdeScores = new ArrayList<>();
        int rank = 1;
        for (String speler : spelers) {
            int juisteAntwoorden = scores.get(speler);
            int playerWins = wins.getOrDefault(speler, 0);
            String formattedEntry = String.format("%d) %s : Wins: %d, Juiste antwoorden: %d",
                    rank, speler, playerWins, juisteAntwoorden);
            formateerdeScores.add(formattedEntry);
            rank++;
        }
        return formateerdeScores;
    }


    // Laad de scores
    private void laadScores() {
        File file = new File(SCORES_FILE);
        if (!file.exists()) {
            System.out.println("Geen scores bestand gevonden, laad niet.");
            file.getParentFile().mkdirs();
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 3) {
                    String name = parts[0];
                    int score = Integer.parseInt(parts[1]);
                    int playerWins = Integer.parseInt(parts[2]);
                    scores.put(name, score);
                    wins.put(name, playerWins);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Fout bij het laden van de scores", e);
        }
    }

    // Sla de scores op
    private void saveScores() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(SCORES_FILE))) {
            for (String player : scores.keySet()) {
                int score = scores.get(player);
                int playerWins = wins.getOrDefault(player, 0);
                bw.write(String.format("%s|%d|%d\n", player, score, playerWins));
            }
        } catch (IOException e) {
            throw new RuntimeException("Fout bij het opslaan van de scores", e);
        }
    }

    // Toon de scores
    // public void displayScores() {
    // if (scores.isEmpty()) {
    // System.out.println("Nog geen scores beschikbaar.");
    // return;
    // }

    // List<Map.Entry<String, Integer>> sortedScores = new
    // ArrayList<>(scores.entrySet());
    // sortedScores.sort((e1, e2) -> e2.getValue().compareTo(e1.getValue()));

    // int num = 1;
    // for (Map.Entry<String, Integer> entry : sortedScores) {
    // int playerWins = wins.getOrDefault(entry.getKey(), 0);
    // System.out.printf("%d. %s: %d punten (Wins: %d)%n",
    // num++, entry.getKey(), entry.getValue(), playerWins);
    // }
    // } // console output
}
