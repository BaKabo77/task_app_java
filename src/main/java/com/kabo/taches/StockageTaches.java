package com.kabo.taches;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Enregistre les tâches dans un fichier texte, une tâche par ligne :
 * numéro;terminée;titre
 */
public class StockageTaches {
    private final Path chemin;

    public StockageTaches(Path chemin) {
        this.chemin = chemin;
    }

    public void sauvegarder(List<Tache> taches) throws IOException {
        List<String> lignes = new ArrayList<>();
        for (Tache t : taches) {
            lignes.add(t.getId() + ";" + t.estTerminee() + ";" + t.getTitre());
        }
        Files.write(chemin, lignes);
    }

    public List<Tache> charger() throws IOException {
        List<Tache> taches = new ArrayList<>();
        // Au premier lancement, le fichier n'existe pas encore
        if (!Files.exists(chemin)) {
            return taches;
        }
        for (String ligne : Files.readAllLines(chemin)) {
            if (ligne.isBlank()) {
                continue;
            }
            // Limite à 3 morceaux : un titre peut lui-même contenir des ";"
            String[] morceaux = ligne.split(";", 3);
            if (morceaux.length != 3) {
                throw new IOException("Ligne invalide : " + ligne);
            }
            try {
                int id = Integer.parseInt(morceaux[0]);
                boolean terminee = Boolean.parseBoolean(morceaux[1]);
                taches.add(new Tache(id, morceaux[2], terminee));
            } catch (NumberFormatException e) {
                throw new IOException("Numéro invalide : " + ligne, e);
            }
        }
        return taches;
    }
}
