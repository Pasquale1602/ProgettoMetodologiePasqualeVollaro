package it.unicam.cs.mpgc.rpg129696.modelli.comportamenti;

import it.unicam.cs.mpgc.rpg129696.modelli.modificatori.ModificatoreAttacco;
import it.unicam.cs.mpgc.rpg129696.modelli.modificatori.ModificatoreTemporaneo;
import it.unicam.cs.mpgc.rpg129696.modelli.personaggio.PersonaggioBase;

public class ComportamentoBoss implements ComportamentoNemico {
    private final int bonusAttacco;
    private final int durata;
    private boolean potenziato = false;

    public ComportamentoBoss(int bonusAttacco, int durata) {
        this.bonusAttacco = bonusAttacco;
        this.durata = durata;
    }
    /**
     * Il comportamento del boss calcola gli hpAttuali del nemico(Boss).
     * Se gli hpAttuali sono scesi sotto la metà di quelli massimi,
     * allora il boss si potenzia con un buff di attacco dinamico basato sui dati di caricamento
     * @param nemico il boss
     * @param bersaglio il personaggio da attaccare
     */
    @Override
    public void eseguiTurno(PersonaggioBase nemico, PersonaggioBase bersaglio) {
        if (nemico.getHpAttuali() < nemico.getStatisticheAttuali().getHpMassimi()/2
             && !potenziato){
            nemico.aggiungiModificatore(new ModificatoreTemporaneo(
                    new ModificatoreAttacco(bonusAttacco), durata));
            potenziato = true;
        }
        nemico.attacca(bersaglio);
    }
}
