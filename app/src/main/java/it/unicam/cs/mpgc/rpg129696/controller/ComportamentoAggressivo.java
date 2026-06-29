package it.unicam.cs.mpgc.rpg129696.controller;

import it.unicam.cs.mpgc.rpg129696.model.Personaggio;

public class ComportamentoAggressivo implements ComportamentoNemico {

    @Override
    public void eseguiTurno(Personaggio nemico, Personaggio bersaglio) {
        nemico.attacca(bersaglio);
    }
}
