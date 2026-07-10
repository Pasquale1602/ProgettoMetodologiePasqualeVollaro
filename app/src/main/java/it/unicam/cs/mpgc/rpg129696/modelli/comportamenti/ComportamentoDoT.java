package it.unicam.cs.mpgc.rpg129696.modelli.comportamenti;

import it.unicam.cs.mpgc.rpg129696.modelli.modificatori.ModificatoreTemporaneo;
import it.unicam.cs.mpgc.rpg129696.modelli.modificatori.ModificatoreVeleno;
import it.unicam.cs.mpgc.rpg129696.modelli.personaggio.PersonaggioBase;

/**
 * Comportamento nemico che applica un effetto di danno nel tempo
 * al bersaglio prima di effettuare un attacco.
 */
public class ComportamentoDoT implements ComportamentoNemico {
    private final int valoreEffetto;
    private final int durata;
    private boolean isAvvelenato = false;

    public ComportamentoDoT(int valoreEffetto, int durata) {
        this.valoreEffetto = valoreEffetto;
        this.durata = durata;
    }

    @Override
    public void eseguiTurno(PersonaggioBase nemico, PersonaggioBase bersaglio) {

        if (!isAvvelenato) {
            ModificatoreTemporaneo modificatoreVeleno = new ModificatoreTemporaneo(new ModificatoreVeleno(valoreEffetto),
                    durata);
            bersaglio.aggiungiModificatore(modificatoreVeleno);
            isAvvelenato = true;
        }
        nemico.attacca(bersaglio);
    }
    @Override
    public ComportamentoNemico nuovaIstanza() {
        return new ComportamentoDoT(valoreEffetto, durata);
    }
}
