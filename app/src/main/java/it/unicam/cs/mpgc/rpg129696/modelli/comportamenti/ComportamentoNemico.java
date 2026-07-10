package it.unicam.cs.mpgc.rpg129696.modelli.comportamenti;

import it.unicam.cs.mpgc.rpg129696.modelli.personaggio.PersonaggioBase;

/**
 * Definisce il comportamento adottato da un nemico durante il combattimento.
 * Ogni implementazione rappresenta una strategia diversa di azione durante un turno.
 */
public interface ComportamentoNemico {

    /**
     * Esegue l'azione del nemico durante il proprio turno.
     *
     * @param nemico personaggio che esegue il comportamento
     * @param bersaglio personaggio contro cui viene eseguita l'azione
     */
    void eseguiTurno(PersonaggioBase nemico, PersonaggioBase bersaglio);

    ComportamentoNemico nuovaIstanza ();
}
