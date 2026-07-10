package it.unicam.cs.mpgc.rpg129696.modelli.oggetti;

import it.unicam.cs.mpgc.rpg129696.modelli.personaggio.PersonaggioBase;
/**
 * Definisce l'effetto applicabile da un oggetto a un personaggio.
 *
 * Le implementazioni specificano il comportamento eseguito quando un oggetto
 * viene utilizzato su un personaggio.
 */
public interface EffettoOggetto {
    /**
     * Applica l'effetto dell'oggetto al personaggio indicato.
     *
     * @param personaggio personaggio a cui applicare l'effetto
     * @param oggetto oggetto che genera l'effetto
     * @throws IllegalArgumentException se uno dei parametri è null
     */
        void applica (PersonaggioBase personaggio, Oggetto oggetto);
    }

