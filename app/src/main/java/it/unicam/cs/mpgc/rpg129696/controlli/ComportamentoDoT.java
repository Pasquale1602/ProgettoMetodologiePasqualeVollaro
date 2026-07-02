package it.unicam.cs.mpgc.rpg129696.controlli;

import it.unicam.cs.mpgc.rpg129696.modelli.effetti.VelenoProgressivo;

public class ComportamentoDoT implements ComportamentoNemico {

    private boolean velenoAttivo = false;

    @Override
    public void eseguiTurno(Personaggio nemico, Personaggio bersaglio) {

        if (!(velenoAttivo)) {
            new VelenoProgressivo(3, 5).applica(bersaglio);
            velenoAttivo = true;
        }
        nemico.attacca(bersaglio);
    }
}
