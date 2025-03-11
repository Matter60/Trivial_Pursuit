package TrivialPursuit.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class FileManager {

    private static final String FILE_PATH = "data/";

    public FileManager() {
        // Create data directory if it doesn't exist
        File directory = new File(FILE_PATH);
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }

    public boolean saveGame(String filePath, Game game) {
        try {
            FileWriter writer = new FileWriter(filePath);

            // Sla het aantal spelers op
            List<Speler> spelers = game.getSpelers();
            writer.write(spelers.size() + "\n");

            // Sla de spelerinformatie op
            for (Speler speler : spelers) {
                writer.write(speler.getNaam() + "," + speler.getSpelerKleur() + "\n");

                // Sla de spelerpositie op
                writer.write(game.getSpelerPositie(speler) + "\n");

                // Sla de speler partjes op
                List<Kleur> partjes = speler.getPartjes();
                writer.write(partjes.size() + "\n");
                for (Kleur kleur : partjes) {
                    writer.write(kleur.toString() + "\n");
                }
            }

            // Sla de index van de huidige speler op
            writer.write(spelers.indexOf(game.getHuidigeSpeler()) + "\n");

            writer.close();
            return true;
        } catch (IOException e) {
            System.err.println("Error saving game: " + e.getMessage());
            return false;
        }
    }

    /**
     * Loads a saved game from file
     *
     * @return A new Game object with the saved state, or null if loading failed
     */
    public Game loadGame(String filePath) {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                return null;
            }

            Scanner scanner = new Scanner(file);

            // Create a new game
            Game game = new Game();

            // Read number of players
            int numPlayers = Integer.parseInt(scanner.nextLine());

            // Map to store player positions
            Map<Speler, Integer> playerPositions = new HashMap<>();

            // Read player information
            for (int i = 0; i < numPlayers; i++) {
                // Read player name and color
                String[] playerInfo = scanner.nextLine().split(",");
                String naam = playerInfo[0];
                Kleur kleur = getKleurFromString(playerInfo[1]);

                // Add player to game
                game.voegSpelerToe(naam, kleur);
                Speler speler = game.getSpelers().get(i);

                // Read player position
                int position = Integer.parseInt(scanner.nextLine());
                playerPositions.put(speler, position);

                // Read player partjes
                int numPartjes = Integer.parseInt(scanner.nextLine());
                for (int j = 0; j < numPartjes; j++) {
                    Kleur partjeKleur = getKleurFromString(scanner.nextLine());
                    game.geefPartje(speler, partjeKleur);
                }
            }

            // Start the game
            game.startSpel();

            // Set player positions
            for (Map.Entry<Speler, Integer> entry : playerPositions.entrySet()) {
                Speler speler = entry.getKey();
                int positie = entry.getValue();
                game.setSpelerPositie(speler, positie);
            }

            // Set current player
            int currentPlayerIndex = Integer.parseInt(scanner.nextLine());
            // Reset to first player
            while (game.getSpelers().indexOf(game.getHuidigeSpeler()) != 0) {
                game.volgendeSpeler();
            }
            // Move to correct player
            for (int i = 0; i < currentPlayerIndex; i++) {
                game.volgendeSpeler();
            }

            scanner.close();
            return game;
        } catch (FileNotFoundException e) {
            System.err.println("Save file not found: " + e.getMessage());
            return null;
        } catch (Exception e) {
            System.err.println("Error loading game: " + e.getMessage());
            return null;
        }
    }

    private Kleur getKleurFromString(String kleurNaam) {
        // Custom method to convert color string to Kleur enum
        switch (kleurNaam.toLowerCase()) {
            case "blauw":
                return Kleur.BLAUW;
            case "roze":
                return Kleur.ROZE;
            case "geel":
                return Kleur.GEEL;
            case "bruin":
                return Kleur.BRUIN;
            case "groen":
                return Kleur.GROEN;
            case "oranje":
                return Kleur.ORANJE;
            case "wit":
                return Kleur.WIT;
            default:
                throw new IllegalArgumentException("Onbekende kleur: " + kleurNaam);
        }
    }
}
