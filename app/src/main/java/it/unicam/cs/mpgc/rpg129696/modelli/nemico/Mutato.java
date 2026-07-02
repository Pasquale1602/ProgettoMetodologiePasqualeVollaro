package it.unicam.cs.mpgc.rpg129696.modelli.nemico;

import it.unicam.cs.mpgc.rpg129696.controlli.ComportamentoAggressivo;

public class Mutato extends Nemico {

    private static final String nome = "Mutato";
    private static final int hpBase = 120;
    private static final int attaccoBase = 18;
    private static final int difesaBase = 8;

    public Mutato() {
        super (nome, hpBase, hpBase, attaccoBase, difesaBase, new ComportamentoAggressivo());
    }
}
