package it.unicam.cs.mpgc.rpg129696.modelli.modificatori;

import it.unicam.cs.mpgc.rpg129696.modelli.personaggio.PersonaggioBase;
import it.unicam.cs.mpgc.rpg129696.modelli.personaggio.Statistica;

/**
 * Definisce il contratto per ogni modificatore che può essere applicato
 * Ogni implementazione decide autonomamente quali statistiche alterare e come
 */
public interface Modificatore {

    /**
     * Applica l'effetto di questo modificatore all'Oggetto Statistica
     * @param statistica le statistiche da modificare
     */
    void applica (Statistica statistica);

    /**
     * Applica l'effetto direttamente sul personaggio.
     * Di default delega ad applica(Statistiche) sulle statistiche attuali.
     * Può essere sovrascritto per effetti che agiscono sugli hp, come il veleno.
     * @param personaggio il personaggio sul quale applicare l'effetto
     */
    default void applicaSuPersonaggio (PersonaggioBase personaggio) {
        applica(personaggio.getStatisticheAttuali());
    }
}
