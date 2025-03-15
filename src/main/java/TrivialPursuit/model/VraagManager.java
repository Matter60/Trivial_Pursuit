package TrivialPursuit.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class VraagManager {

    private static final String VRAAG_DIR = "data/vragen/";

    // Voeg een vraag toe aan de categorie
    public void addVraag(Vraag vraag) {
        String filename = VRAAG_DIR + vraag.getCategorie().name().toLowerCase() + ".txt";
        try {
            File dir = new File(VRAAG_DIR);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename, true))) {
                StringBuilder sb = new StringBuilder(vraag.getVraag());
                for (String answer : vraag.getMogelijkeAntwoorden()) {
                    sb.append("|").append(answer);
                }
                sb.append("|").append(vraag.getMogelijkeAntwoorden().indexOf(vraag.getJuisteAntwoord())).append("\n");
                bw.write(sb.toString());
            }
        } catch (IOException e) {
            throw new RuntimeException("Fout bij het opslaan van de vraag: " + e.getMessage());
        }
    }

    // Laad vragen voor een bepaalde categorie
    public List<Vraag> laadVraag(Kleur category) {
        List<Vraag> vragen = new ArrayList<>();
        String filename = VRAAG_DIR + category.name().toLowerCase() + ".txt";

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length >= 6) {
                    String question = parts[0];
                    List<String> answers = new ArrayList<>();
                    for (int i = 1; i < parts.length - 1; i++) {
                        answers.add(parts[i]);
                    }
                    int correctIndex = Integer.parseInt(parts[parts.length - 1]);
                    vragen.add(new Vraag(question, answers, correctIndex, category));
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Geen vragen gevonden voor categorie " + category + ": " + e.getMessage());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Ongeldig antwoordindex in vraagbestand: " + e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException("Fout bij het laden van vragen: " + e.getMessage());
        }

        // random vraag
        Collections.shuffle(vragen);

        return vragen;
    }
}
