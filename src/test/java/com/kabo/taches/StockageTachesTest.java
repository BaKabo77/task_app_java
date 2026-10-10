package com.kabo.taches;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class StockageTachesTest {

    // JUnit crée un dossier temporaire, supprimé après chaque test
    @TempDir
    Path dossier;

    private Path fichier;
    private StockageTaches stockage;

    @BeforeEach
    void setUp() {
        fichier = dossier.resolve("taches.txt");
        stockage = new StockageTaches(fichier);
    }

    @Test
    void chargerSansFichierRenvoieUneListeVide() throws IOException {
        assertTrue(stockage.charger().isEmpty());
    }

    @Test
    void sauvegarderPuisChargerRetrouveLesMemesTaches() throws IOException {
        stockage.sauvegarder(List.of(
                new Tache(1, "Faire les courses", true),
                new Tache(2, "Apprendre Java", false)));

        List<Tache> taches = stockage.charger();

        assertEquals(2, taches.size());
        assertEquals(1, taches.get(0).getId());
        assertEquals("Faire les courses", taches.get(0).getTitre());
        assertTrue(taches.get(0).estTerminee());
        assertEquals(2, taches.get(1).getId());
        assertEquals("Apprendre Java", taches.get(1).getTitre());
        assertFalse(taches.get(1).estTerminee());
    }

    @Test
    void unTitreAvecPointVirguleEstConserve() throws IOException {
        stockage.sauvegarder(List.of(new Tache(1, "Acheter : pain; lait; oeufs")));

        assertEquals("Acheter : pain; lait; oeufs", stockage.charger().get(0).getTitre());
    }

    @Test
    void uneLigneInvalideProvoqueUneErreur() throws IOException {
        Files.writeString(fichier, "pas une tâche\n");

        assertThrows(IOException.class, () -> stockage.charger());
    }
}
