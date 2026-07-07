package it.unicam.cs.mpgc.rpg129696.modelli.personaggio;

public class Giocatore {

    private final String nomeUtente;
    private PersonaggioGiocabile personaggio;
    private Progressione progressione;


    public Giocatore(String nomeUtente, PersonaggioGiocabile personaggio, Progressione progressione) {
        if (nomeUtente == null || nomeUtente.isBlank()) {
            throw new IllegalArgumentException("Il nome utente non puo essere vuoto");
        }
        if (personaggio == null) {
            throw new IllegalArgumentException("Il personaggio non puo essere null");
        }
        if (progressione == null) {
            throw new IllegalArgumentException("La progressione non puo essere null");
        }

        this.nomeUtente = nomeUtente;
        this.personaggio = personaggio;
        this.progressione = progressione;
    }

    public int aggiungiEsperienza(int esperienza) {
        int livelliGuadagnati = progressione.aggiungiEsperienza(esperienza);

        if (livelliGuadagnati > 0) {
            personaggio.riceviBonusLevelUp(livelliGuadagnati);
        }

        return livelliGuadagnati;
    }

    public String getNomeUtente() { return nomeUtente; }
    public PersonaggioGiocabile getPersonaggio() { return personaggio; }
    public Progressione getProgressione() { return progressione; }

    public void setPersonaggio(PersonaggioGiocabile personaggio) {
        if (personaggio == null) {
            throw new IllegalArgumentException("Il personaggio non puo essere null");
        }
        this.personaggio = personaggio;
    }

    public void setProgressione(Progressione progressione) {
        if (progressione == null) {
            throw new IllegalArgumentException("La progressione non puo essere null");
        }
        this.progressione = progressione;
    }
}
