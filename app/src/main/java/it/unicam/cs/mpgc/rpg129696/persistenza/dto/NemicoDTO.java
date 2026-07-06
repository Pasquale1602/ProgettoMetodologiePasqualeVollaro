package it.unicam.cs.mpgc.rpg129696.persistenza.dto;
/**
 * Classe DTO (Data Transfer Object) che rappresenta la struttura
 * di un nemico nel file JSON. Serve esclusivamente per il parsing.
 */
public class NemicoDTO {
    public String nome;
    public int hpBase;
    public int attaccoBase;
    public int difesaBase;
    public String tipoComportamento;
    public int esperienza;
    public int valoreEffettoComportamento;
    public int durataEffettoComportamento;
}
