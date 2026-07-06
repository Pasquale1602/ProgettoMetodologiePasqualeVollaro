package it.unicam.cs.mpgc.rpg129696.modelli.modificatori;

import it.unicam.cs.mpgc.rpg129696.modelli.personaggio.Statistiche;

import java.util.List;

/**
 * Calcola le statistiche finali applicando i modificatori temporanei attivi.
 */
public class SistemaModificatori {

    private SistemaModificatori() {
    }

    public static Statistiche calcola(Statistiche statisticheBase, List<ModificatoreTemporaneo> modificatori) {
        Statistiche statisticheFinali = new Statistiche(statisticheBase);

        for (ModificatoreTemporaneo modificatore : modificatori) {
            statisticheFinali = modificatore.applica(statisticheFinali);
        }

        return statisticheFinali;
    }
}
