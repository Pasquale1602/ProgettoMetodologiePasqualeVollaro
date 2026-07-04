package it.unicam.cs.mpgc.rpg129696.controlli;

import it.unicam.cs.mpgc.rpg129696.modelli.modificatori.ModificatoreTemporaneo;
import it.unicam.cs.mpgc.rpg129696.modelli.modificatori.ModificatoreVeleno;
import it.unicam.cs.mpgc.rpg129696.modelli.personaggio.PersonaggioBase;

public class ComportamentoDoT implements ComportamentoNemico {

    private boolean isAvvelenato = false;

    @Override
    public void eseguiTurno(PersonaggioBase nemico, PersonaggioBase bersaglio) {

        if (!isAvvelenato) {
            ModificatoreTemporaneo modificatoreVeleno = new ModificatoreTemporaneo(new ModificatoreVeleno(6),
                    3);
            bersaglio.aggiungiModificatore(modificatoreVeleno);
            isAvvelenato = true;
        }
        nemico.attacca(bersaglio);
    }
}
