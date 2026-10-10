package com.kabo;

import java.util.List;
import java.util.Scanner;

import com.kabo.taches.GestionnaireTaches;
import com.kabo.taches.Tache;

public class App {
    public static void main(String[] args) {
        GestionnaireTaches gestionnaire = new GestionnaireTaches();
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Gestionnaire de tâches ===");

        boolean continuer = true;
        while (continuer) {
            afficherMenu();
            String choix = scanner.nextLine().trim();

            switch (choix) {
                case "1" -> {
                    System.out.print("Titre de la tâche : ");
                    String titre = scanner.nextLine().trim();
                    if (titre.isEmpty()) {
                        System.out.println("Le titre ne peut pas être vide.");
                    } else {
                        Tache tache = gestionnaire.ajouter(titre);
                        System.out.println("Tâche ajoutée : " + tache);
                    }
                }
                case "2" -> {
                    List<Tache> taches = gestionnaire.lister();
                    if (taches.isEmpty()) {
                        System.out.println("Aucune tâche.");
                    } else {
                        taches.forEach(System.out::println);
                    }
                }
                case "3" -> {
                    Integer id = lireId(scanner);
                    if (id != null) {
                        System.out.println(gestionnaire.terminer(id)
                                ? "Tâche terminée."
                                : "Tâche introuvable.");
                    }
                }
                case "4" -> {
                    Integer id = lireId(scanner);
                    if (id != null) {
                        System.out.println(gestionnaire.supprimer(id)
                                ? "Tâche supprimée."
                                : "Tâche introuvable.");
                    }
                }
                case "0" -> continuer = false;
                default -> System.out.println("Choix invalide.");
            }
        }

        System.out.println("Au revoir !");
        scanner.close();
    }
    

    private static void afficherMenu() {
        System.out.println();
        System.out.println("1. Ajouter une tâche");
        System.out.println("2. Lister les tâches");
        System.out.println("3. Terminer une tâche");
        System.out.println("4. Supprimer une tâche");
        System.out.println("0. Quitter");
        System.out.print("Votre choix : ");
    }

    private static Integer lireId(Scanner scanner) {
        System.out.print("Numéro de la tâche : ");
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Numéro invalide.");
            return null;
        }
    }
}
