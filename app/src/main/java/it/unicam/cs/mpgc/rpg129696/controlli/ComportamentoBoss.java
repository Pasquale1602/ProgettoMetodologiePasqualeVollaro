package it.unicam.cs.mpgc.rpg129696.controlli;

import it.unicam.cs.mpgc.rpg129696.modelli.effetti.BuffAttacco;

public class ComportamentoBoss implements ComportamentoNemico {

    private boolean potenziato = false;

    @Override
    public void eseguiTurno(Personaggio nemico, Personaggio bersaglio) {
        if (nemico.getHp() <= nemico.getMaxHp() / 2) {
            if (!potenziato) {
                new BuffAttacco(3, 20).applica(nemico);
                potenziato = true;
            }
        }
        nemico.attacca(bersaglio);
    }
}
