package it.unicam.cs.mpgc.rpg129696.modelli.abilita;

import it.unicam.cs.mpgc.rpg129696.modelli.personaggio.PersonaggioBase;

/**
 * Rappresenta un'abilità utilizzabile da un personaggio durante il gioco.
 *
 * Un'abilità definisce un'azione che può essere eseguita da un utilizzatore
 * su un bersaglio.
 */
public interface Abilita {

    /**
     * Esegue l'effetto dell'abilità.
     *
     * @param utilizzatore personaggio che utilizza l'abilità
     * @param bersaglio personaggio su cui viene applicato l'effetto dell'abilità
     */
    void eseguiAbilita(PersonaggioBase utilizzatore, PersonaggioBase bersaglio);
}
