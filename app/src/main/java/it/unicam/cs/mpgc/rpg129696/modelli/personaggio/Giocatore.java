package it.unicam.cs.mpgc.rpg129696.modelli.personaggio;
/**
 * Rappresenta il giocatore della partita.
 *
 * Contiene il nome utente, il personaggio scelto e la progressione
 * con livello ed esperienza.
 */
public class Giocatore {

    private final String nomeUtente;
    private PersonaggioGiocabile personaggio;
    private Progressione progressione;

    /**
     * Crea un nuovo giocatore.
     *
     * @param nomeUtente nome del giocatore
     * @param personaggio personaggio controllato dal giocatore
     * @param progressione progressione del giocatore
     * @throws IllegalArgumentException se uno dei parametri non è valido
     */
    public Giocatore(String nomeUtente, PersonaggioGiocabile personaggio, Progressione progressione) {
        if (nomeUtente == null || nomeUtente.isBlank()) {
            throw new IllegalArgumentException("Il nome utente non puo essere null o vuoto");
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
    /**
     * Aggiunge esperienza al giocatore e applica eventuali bonus
     * derivanti dai livelli ottenuti.
     *
     * @param esperienza esperienza da aggiungere
     * @return numero di livelli guadagnati
     */
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
