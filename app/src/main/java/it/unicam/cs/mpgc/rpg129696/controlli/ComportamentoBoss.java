package it.unicam.cs.mpgc.rpg129696.controlli;

import it.unicam.cs.mpgc.rpg129696.modelli.modificatori.ModificatoreAttacco;
import it.unicam.cs.mpgc.rpg129696.modelli.modificatori.ModificatoreTemporaneo;
import it.unicam.cs.mpgc.rpg129696.modelli.personaggio.PersonaggioBase;

public class ComportamentoBoss implements ComportamentoNemico{
    private boolean potenziato = false;

    /**
     * Il comportamento del boss calcola gli hpAttuali del nemico(Boss).
     * Se gli hpAttuali sono scesi sotto la metà di quelli massimi,
     * allora il boss si potenzia con un buff di attacco da 20, per 3 turni
     * @param nemico il boss
     * @param bersaglio il personaggio da attaccare
     */
    @Override
    public void eseguiTurno(PersonaggioBase nemico, PersonaggioBase bersaglio) {
        if (nemico.getHpAttuali() < nemico.getStatisticheAttuali().getHpMassimi()/2
             && !potenziato){
            ModificatoreTemporaneo potenzia = new ModificatoreTemporaneo (new ModificatoreAttacco(20),
                    3);
            nemico.aggiungiModificatore(potenzia);
            potenziato = true;
        }
        nemico.attacca(bersaglio);
    }
}
