package it.unicam.cs.mpgc.rpg129696.modelli.personaggio;

public class Giocatore {

    private final String nomeUtente;
    private PersonaggioGiocabile personaggio;
    private Progressione progressione;

    protected Giocatore () {
        this.nomeUtente = "";
        this.progressione = null;
        this.personaggio = null;
    }

    public Giocatore (String nomeUtente, PersonaggioGiocabile personaggio, Progressione progressione) {
        this.nomeUtente = nomeUtente;
        this.personaggio = personaggio;
        this.progressione = progressione;
    }

    public void aggiungiEsperienza (int esperienza) {
        progressione.aggiungiEsperienza(esperienza);
    }

    public String getNomeUtente() { return nomeUtente; }
    public PersonaggioGiocabile getPersonaggio() { return personaggio; }
    public Progressione getProgressione() { return progressione; }

    public void setPersonaggio(PersonaggioGiocabile personaggio) {  this.personaggio = personaggio; }
    public void setProgressione(Progressione progressione) { this.progressione = progressione; }
}
