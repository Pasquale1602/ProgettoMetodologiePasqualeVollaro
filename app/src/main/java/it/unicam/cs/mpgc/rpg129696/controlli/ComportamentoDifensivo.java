package it.unicam.cs.mpgc.rpg129696.controlli;

import it.unicam.cs.mpgc.rpg129696.modelli.modificatori.ModificatoreDifesa;
import it.unicam.cs.mpgc.rpg129696.modelli.modificatori.ModificatoreTemporaneo;
import it.unicam.cs.mpgc.rpg129696.modelli.personaggio.PersonaggioBase;

public class ComportamentoDifensivo implements ComportamentoNemico{
    private final int bonusDifesa;
    private final int durata;
    private boolean buffAttivo = false;

    public ComportamentoDifensivo(int bonusDifesa, int durata) {
        this.bonusDifesa = bonusDifesa;
        this.durata = durata;
    }

    @Override
    public void eseguiTurno(PersonaggioBase nemico, PersonaggioBase bersaglio) {
        if (nemico.getHpAttuali() < nemico.getStatisticheAttuali().getHpMassimi()/2 && !buffAttivo) {
            ModificatoreTemporaneo modificatore = new ModificatoreTemporaneo(new ModificatoreDifesa(bonusDifesa),
                    durata);
            nemico.aggiungiModificatore(modificatore);
            buffAttivo = true;
        }
        nemico.attacca(bersaglio);
    }
}
