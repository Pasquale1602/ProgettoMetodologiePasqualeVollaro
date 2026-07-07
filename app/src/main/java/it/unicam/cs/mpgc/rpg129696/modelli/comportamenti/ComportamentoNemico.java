package it.unicam.cs.mpgc.rpg129696.modelli.comportamenti;

import it.unicam.cs.mpgc.rpg129696.modelli.personaggio.PersonaggioBase;

/**
 * Interfaccia che definisce il contratto per il comportamento dei nemici
 * Ogni strategia di combattimento deve implementare questo metodo
 */
public interface ComportamentoNemico {

    void eseguiTurno (PersonaggioBase nemico, PersonaggioBase bersaglio);
}
