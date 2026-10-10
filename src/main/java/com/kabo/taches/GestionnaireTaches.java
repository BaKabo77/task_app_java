package com.kabo.taches;

import java.util.ArrayList;
import java.util.List;

public class GestionnaireTaches {
    private final List<Tache> taches = new ArrayList<>();
    private int prochainId = 1;

    public GestionnaireTaches() {
    }

    // Démarre avec des tâches existantes (par exemple chargées depuis un fichier)
    public GestionnaireTaches(List<Tache> tachesInitiales) {
        for (Tache t : tachesInitiales) {
            taches.add(t);
            // La prochaine tâche doit avoir un numéro plus grand que tous ceux existants
            prochainId = Math.max(prochainId, t.getId() + 1);
        }
    }

    public Tache ajouter(String titre) {
        Tache tache = new Tache(prochainId++, titre);
        taches.add(tache);
        return tache;
    }

    public List<Tache> lister() {
        return List.copyOf(taches);
    }

    public boolean terminer(int id) {
        Tache tache = trouver(id);
        if (tache == null) {
            return false;
        }
        tache.terminer();
        return true;
    }

    public boolean supprimer(int id) {
        return taches.removeIf(t -> t.getId() == id);
    }

    private Tache trouver(int id) {
        for (Tache t : taches) {
            if (t.getId() == id) {
                return t;
            }
        }
        return null;
    }
}
