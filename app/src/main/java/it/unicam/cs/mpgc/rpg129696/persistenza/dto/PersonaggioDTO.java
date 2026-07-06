package it.unicam.cs.mpgc.rpg129696.persistenza.dto;


import it.unicam.cs.mpgc.rpg129696.modelli.personaggio.Statistiche;

/**
 * Classe DTO (Data Transfer Object) che rappresenta la struttura
 * di un personaggio nel file JSON. Serve esclusivamente per il parsing.
 */
public class PersonaggioDTO {
    public int id;
    public String nome;
    public String descrizione;

    public Statistiche statistiche;

    public String tipoAbilita;
}