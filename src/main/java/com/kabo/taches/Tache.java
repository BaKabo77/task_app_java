package com.kabo.taches;

public class Tache {
    private final int id;
    private final String titre;
    private boolean terminee;

    public Tache(int id, String titre) {
        this.id = id;
        this.titre = titre;
        this.terminee = false;
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
