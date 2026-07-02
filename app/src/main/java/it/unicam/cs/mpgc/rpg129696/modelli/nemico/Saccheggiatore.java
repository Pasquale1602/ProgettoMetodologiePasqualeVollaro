package it.unicam.cs.mpgc.rpg129696.modelli.nemico;

import it.unicam.cs.mpgc.rpg129696.controlli.ComportamentoAggressivo;

public class Saccheggiatore extends Nemico {

    private static final String nome = "Saccheggiatore";
    private static final int hpBase = 50;
    private static final int attaccoBase = 22;
    private static final int difesaBase = 3;

    public Saccheggiatore(){
        super (nome, hpBase, hpBase, attaccoBase, difesaBase, new ComportamentoAggressivo());
    }
}
