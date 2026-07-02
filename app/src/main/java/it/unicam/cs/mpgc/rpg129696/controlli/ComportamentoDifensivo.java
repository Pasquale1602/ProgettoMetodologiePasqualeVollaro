package it.unicam.cs.mpgc.rpg129696.controlli;

import it.unicam.cs.mpgc.rpg129696.modelli.effetti.BuffDifesa;

public class ComportamentoDifensivo implements ComportamentoNemico{

    private boolean buffAttivo= false;

    @Override
    public void eseguiTurno(Personaggio nemico, Personaggio bersaglio) {

        if (!buffAttivo) {
            new BuffDifesa(3, 7).applica(nemico);
            buffAttivo = true;
        }
        nemico.attacca(bersaglio);
    }
}
