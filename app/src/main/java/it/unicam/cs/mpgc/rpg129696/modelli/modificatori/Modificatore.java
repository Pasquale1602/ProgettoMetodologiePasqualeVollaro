package it.unicam.cs.mpgc.rpg129696.modelli.modificatori;

import it.unicam.cs.mpgc.rpg129696.modelli.personaggio.PersonaggioBase;
import it.unicam.cs.mpgc.rpg129696.modelli.personaggio.Statistiche;

/**
 * Definisce il contratto per ogni modificatore che può essere applicato
 * Ogni implementazione decide autonomamente quali statistiche alterare e come
 */
public interface Modificatore {

    /**
     * Applica l'effetto di questo modificatore all'Oggetto Statistica
     * @param statistiche le statistiche da modificare
     */
    default void applica (Statistiche statistiche) {
    }

    /**
     * Di default non fa nulla.
     * Solo i modificatori che devono agire direttamente con lo stato del personaggio (come il veleno)
     * faranno override di questo metodo.
     * @param personaggio il personaggio sul quale applicare l'effetto
     */
    default void applicaSuPersonaggio (PersonaggioBase personaggio) {
        //vuoto, non fa nulla
    }
}
