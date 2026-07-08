package it.unicam.cs.mpgc.rpg129696.persistenza.dto;

import it.unicam.cs.mpgc.rpg129696.modelli.enumerati.TipoComportamento;

import java.util.ArrayList;
import java.util.List;

/**
 * DTO che rappresenta i dati essenziali di una partita salvata.
 */
public class PartitaDTO {

    public String nomeUtente;
    public String nomePersonaggio;

    public int livello;
    public int esperienza;
    public int esperienzaPerLivelloSuccessivo;
    public int hpAttuali;

    public int combattimentiVinti;
    public String ultimoSalvataggio;

    public String nomeNemico;
    public int hpNemico;
    public TipoComportamento comportamentoNemico;
    
    public List<OggettoSalvatoDTO> inventario = new ArrayList<>();
}
