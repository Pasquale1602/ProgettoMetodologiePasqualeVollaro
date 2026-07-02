package it.unicam.cs.mpgc.rpg129696.modelli.nemico;

import it.unicam.cs.mpgc.rpg129696.controlli.ComportamentoBoss;

public class Aberrazione extends Nemico {

    private static final String nome = "Aberrazione";
    private static final int hpBase = 200;
    private static final int attaccoBase = 28;
    private static final int difesaBase = 12;

    public Aberrazione(){
        super (nome, hpBase, hpBase, attaccoBase, difesaBase, new ComportamentoBoss());
    }
}
