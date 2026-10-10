package com.kabo.taches;

public class Tache {
    private final int id;
    private final String titre;
    private boolean terminee;

    public Tache(int id, String titre) {
        this(id, titre, false);
    }

    // Utilisé au chargement pour recréer une tâche avec son état réel
    public Tache(int id, String titre, boolean terminee) {
        this.id = id;
        this.titre = titre;
        this.terminee = terminee;
    }

    public int getId() {
        return id;
    }

    public String getTitre() {
        return titre;
    }

    public boolean estTerminee() {
        return terminee;
    }

    public void terminer() {
        this.terminee = true;
    }

    @Override
    public String toString() {
        return String.format("%d. [%s] %s", id, terminee ? "x" : " ", titre);
    }
}
