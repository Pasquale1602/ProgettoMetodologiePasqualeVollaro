package it.unicam.cs.mpgc.rpg129696.modelli.nemico;

import it.unicam.cs.mpgc.rpg129696.controlli.ComportamentoDifensivo;

public class Fanatico extends Nemico {

    private static final String nome = "Saccheggiatore";
    private static final int hpBase = 80;
    private static final int attaccoBase = 15;
    private static final int difesaBase = 18;

    public Fanatico(){
        super (nome, hpBase, hpBase, attaccoBase, difesaBase, new ComportamentoDifensivo());
    }
}
