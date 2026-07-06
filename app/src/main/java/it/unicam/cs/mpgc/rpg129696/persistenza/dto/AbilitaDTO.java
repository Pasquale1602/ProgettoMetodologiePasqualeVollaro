package it.unicam.cs.mpgc.rpg129696.persistenza.dto;

/**
 * Classe DTO (Data Transfer Object) che rappresenta la struttura
 * di un abilita nel file JSON. Serve esclusivamente per il parsing.
 */
public class AbilitaDTO {
    public int id;
    public String nome;
    public String tipo;
    public String descrizione;
    public int valoreAttacco;
    public int valoreDifesa;
    public int valoreCura;
    public int dannoVeleno;
    public int turni;
    public int moltiplicatoreCritico;
}
