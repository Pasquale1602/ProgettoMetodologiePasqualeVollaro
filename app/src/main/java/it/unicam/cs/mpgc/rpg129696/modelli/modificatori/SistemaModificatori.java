package it.unicam.cs.mpgc.rpg129696.modelli.modificatori;

import it.unicam.cs.mpgc.rpg129696.modelli.personaggio.Statistiche;

import java.util.List;

/**
 * Calcola le statistiche finali applicando una sequenza di modificatori temporanei.
 *
 * La statistica originale non viene modificata: viene creata una copia sulla quale
 * vengono applicati i modificatori nell'ordine fornito.
 */
public class SistemaModificatori {

    private SistemaModificatori() {
    }
    /**
     * Calcola le statistiche finali applicando i modificatori indicati.
     *
     * @param statisticheBase statistiche iniziali del personaggio
     * @param modificatori modificatori temporanei da applicare
     * @return statistiche risultanti dopo l'applicazione dei modificatori
     * @throws IllegalArgumentException se le statistiche base o la lista dei modificatori sono null
     */
    public static Statistiche calcola(Statistiche statisticheBase, List<ModificatoreTemporaneo> modificatori) {
        Statistiche statisticheFinali = new Statistiche(statisticheBase);

        for (ModificatoreTemporaneo modificatore : modificatori) {
            statisticheFinali = modificatore.applica(statisticheFinali);
        }

        return statisticheFinali;
    }
}
