package com.kabo.taches;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GestionnaireTachesTest {

    private GestionnaireTaches gestionnaire;

    // Exécuté avant chaque test : on repart d'un gestionnaire vide
    @BeforeEach
    void setUp() {
        gestionnaire = new GestionnaireTaches();
    }

    @Test
    void ajouterDonneDesNumerosQuiSeSuivent() {
        Tache premiere = gestionnaire.ajouter("Faire les courses");
        Tache deuxieme = gestionnaire.ajouter("Apprendre Java");

        assertEquals(1, premiere.getId());
        assertEquals(2, deuxieme.getId());
        assertEquals(2, gestionnaire.lister().size());
    }

    @Test
    void uneNouvelleTacheNestPasTerminee() {
        Tache tache = gestionnaire.ajouter("Faire les courses");

        assertFalse(tache.estTerminee());
    }

    @Test
    void terminerMarqueLaTacheCommeTerminee() {
        Tache tache = gestionnaire.ajouter("Faire les courses");

        assertTrue(gestionnaire.terminer(tache.getId()));
        assertTrue(tache.estTerminee());
    }

    @Test
    void terminerUneTacheInexistanteRenvoieFalse() {
        assertFalse(gestionnaire.terminer(42));
    }

    @Test
    void supprimerRetireLaTache() {
        Tache tache = gestionnaire.ajouter("Faire les courses");

        assertTrue(gestionnaire.supprimer(tache.getId()));
        assertTrue(gestionnaire.lister().isEmpty());
    }

    @Test
    void supprimerUneTacheInexistanteRenvoieFalse() {
        assertFalse(gestionnaire.supprimer(42));
    }
}
