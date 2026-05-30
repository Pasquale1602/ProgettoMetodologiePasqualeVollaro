package it.unicam.cs.mpgc.rpg129696.model;

import it.unicam.cs.mpgc.rpg129696.model.abilita.Abilita;

public abstract class Personaggio {

    private String nome;
    private int hp;
    private int maxHp;
    private int attacco;
    private int difesa;
    private boolean vivo;

    public Personaggio (String nome, int hp, int maxHp, int attacco, int difesa)  {
        this.nome = nome;
        this.hp = hp;
        this.maxHp = maxHp;
        this.attacco = attacco;
        this.difesa = difesa;
        this.vivo = true;
    }
    public String getNome() {return nome;}
    public int getHp() {return hp;}
    public int getMaxHp() {return maxHp;}
    public int getAttacco() {return attacco;}
    public int getDifesa() {return  difesa;}
    public boolean isVivo() {return vivo;}


}
