package it.unicam.cs.mpgc.rpg129696.controlli;
import it.unicam.cs.mpgc.rpg129696.modelli.modificatori.ModificatoreAttacco;
import it.unicam.cs.mpgc.rpg129696.modelli.personaggio.PersonaggioBase;

public class ComportamentoBoss implements ComportamentoNemico {

    private boolean potenziato = false;

    @Override
    public void eseguiTurno(PersonaggioBase nemico, PersonaggioBase bersaglio) {
        if (nemico.getHpAttuali()<= nemico.getStatisticheBase().getHpMassimi()/2) {
            if (!potenziato) {
                new ModificatoreAttacco(3).applica(nemico);
                potenziato = true;
            }
        }
    }
}
