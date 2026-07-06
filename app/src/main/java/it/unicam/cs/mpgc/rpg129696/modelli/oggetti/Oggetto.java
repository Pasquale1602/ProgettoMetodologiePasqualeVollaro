package it.unicam.cs.mpgc.rpg129696.modelli.oggetti;

import it.unicam.cs.mpgc.rpg129696.modelli.enumerati.TipoOggetto;
import it.unicam.cs.mpgc.rpg129696.modelli.personaggio.PersonaggioBase;

import java.util.Objects;

public class Oggetto {
    private int id;
    private String nome;
    private String descrizione;
    private TipoOggetto tipo;
    private int valoreCura;
    private int valoreAttacco;
    private int turni;

    public Oggetto () {
        this.id = 0;
        this.nome = "";
        this.descrizione = "";
        this.tipo = null;
    }
    public void usa (PersonaggioBase personaggio) {
        CreatoreOggetto.creaEffetto(this.tipo).applica(personaggio,this);
        }
    public int getId() {return id;}
    public String getNome() {return nome;}
    public String getDescrizione() {return descrizione;}
    public TipoOggetto getTipo() {return tipo;}
    public int getValoreCura() {return valoreCura;}
    public int getValoreAttacco() {return valoreAttacco;}
    public int getTurni() {return turni;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Oggetto oggetto = (Oggetto) o;
        return id == oggetto.id;
    }
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
