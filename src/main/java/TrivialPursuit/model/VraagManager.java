package TrivialPursuit.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class VraagManager {
    private static final String VRAAG_DIR = "data/vragen/";

    public void addQuestion(Vraag vraag) {
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
            System.out.println("Fout bij het opslaan van de vraag: " + e.getMessage());
            e.printStackTrace();
        }
    }


    public List<Vraag> loadQuestions(Kleur category) {
        List<Vraag> questions = new ArrayList<>();
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
                    questions.add(new Vraag(question, answers, correctIndex, category));
                }
            }
        } catch (IOException e) {
            if (!(e instanceof FileNotFoundException)) {
                System.out.println("Fout bij het laden van vragen: " + e.getMessage());
                e.printStackTrace();
            }
        }
        
        return questions;
    }
}
