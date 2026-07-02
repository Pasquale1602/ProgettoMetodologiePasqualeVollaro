package it.unicam.cs.mpgc.rpg129696.modelli.nemico;

import it.unicam.cs.mpgc.rpg129696.controlli.ComportamentoAggressivo;

public class Infetto extends Nemico {

    private static final String nome = "Infetto";
    private static final int hpBase = 40;
    private static final int attaccoBase = 8;
    private static final int difesaBase = 3;

    public Infetto(){
        super (nome, hpBase, hpBase, attaccoBase, difesaBase, new ComportamentoAggressivo());
    }
}