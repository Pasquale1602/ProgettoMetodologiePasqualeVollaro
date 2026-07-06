package it.unicam.cs.mpgc.rpg129696.modelli.modificatori;

import it.unicam.cs.mpgc.rpg129696.modelli.personaggio.PersonaggioBase;
import it.unicam.cs.mpgc.rpg129696.modelli.personaggio.Statistiche;

/**
 * Definisce il contratto per ogni modificatore che può essere applicato.
 * Ogni implementazione decide autonomamente quali statistiche alterare e come.
 */
public interface Modificatore {

    /**
     * Accetta le statistiche attuali e restituisce una nuova istanza di Statistiche
     * con l'effetto applicato
     *
     * @param statistiche le statistiche di partenza
     * @return le nuove statistiche modificate, o le stesse se non alterate di default
     */
    default Statistiche applica(Statistiche statistiche) {
        return statistiche; // Di default restituisce l'oggetto intatto
    }

    /**
     * Di default non fa nulla.
     * Solo i modificatori che devono agire direttamente con lo stato del personaggio (come il veleno)
     * faranno override di questo metodo.
     * @param personaggio il personaggio sul quale applicare l'effetto
     */
    default void applicaSuPersonaggio(PersonaggioBase personaggio) {
        //vuoto, non fa nulla
    }
}
