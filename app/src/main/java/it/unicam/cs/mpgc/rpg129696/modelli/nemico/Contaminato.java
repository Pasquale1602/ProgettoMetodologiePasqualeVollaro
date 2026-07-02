package it.unicam.cs.mpgc.rpg129696.modelli.nemico;

import it.unicam.cs.mpgc.rpg129696.controlli.ComportamentoDoT;

public class Contaminato extends Nemico {

    private static final String nome = "Contaminato";
    private static final int hpBase = 45;
    private static final int attaccoBase = 12;
    private static final int difesaBase = 4;

    public Contaminato(){
        super (nome, hpBase, hpBase, attaccoBase, difesaBase, new ComportamentoDoT());
    }
}