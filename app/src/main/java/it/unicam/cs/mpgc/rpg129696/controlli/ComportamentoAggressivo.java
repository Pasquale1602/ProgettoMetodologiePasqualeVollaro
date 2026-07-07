package it.unicam.cs.mpgc.rpg129696.controlli;

import it.unicam.cs.mpgc.rpg129696.modelli.personaggio.PersonaggioBase;

public class ComportamentoAggressivo implements ComportamentoNemico {
    @Override
    public void eseguiTurno(PersonaggioBase nemico, PersonaggioBase bersaglio) {
        nemico.attacca(bersaglio);
    }
}
