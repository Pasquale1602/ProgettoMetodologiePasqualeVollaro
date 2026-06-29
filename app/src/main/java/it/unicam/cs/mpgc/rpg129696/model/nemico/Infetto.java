package it.unicam.cs.mpgc.rpg129696.model.nemico;

import it.unicam.cs.mpgc.rpg129696.controller.ComportamentoAggressivo;
import it.unicam.cs.mpgc.rpg129696.model.Nemico;

public class Infetto extends Nemico {

    public Infetto () {
        super("Infetto", 40, 40, 8, 3, new ComportamentoAggressivo());

    }
}
