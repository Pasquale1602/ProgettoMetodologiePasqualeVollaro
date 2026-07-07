package it.unicam.cs.mpgc.rpg129696.controlli;

import it.unicam.cs.mpgc.rpg129696.modelli.partita.Partita;
import it.unicam.cs.mpgc.rpg129696.modelli.personaggio.Giocatore;
import it.unicam.cs.mpgc.rpg129696.modelli.personaggio.PersonaggioGiocabile;
import it.unicam.cs.mpgc.rpg129696.modelli.personaggio.Progressione;

import java.util.List;

/**
 * Gestisce la creazione e la preparazione delle partite.
 */
public class GestorePartita {

    private static final int LIVELLO_INIZIALE = 1;
    private static final int ESPERIENZA_INIZIALE = 0;
    private static final int ESPERIENZA_PRIMO_LIVELLO = 100;

    /**
     * Crea una nuova partita per l'utente indicato.
     *
     * @param nomeUtente il nome dell'utente
     * @param nomePersonaggio il nome del personaggio scelto
     * @param personaggiDisponibili lista dei personaggi disponibili
     * @return la nuova partita
     * @throws IllegalArgumentException se i dati non sono validi
     */
    public Partita creaNuovaPartita(
            String nomeUtente,
            String nomePersonaggio,
            List<PersonaggioGiocabile> personaggiDisponibili) {

        if (nomeUtente == null || nomeUtente.isBlank()) {
            throw new IllegalArgumentException("Il nome utente non puo essere vuoto");
        }
        if (nomePersonaggio == null || nomePersonaggio.isBlank()) {
            throw new IllegalArgumentException("Il nome del personaggio non puo essere vuoto");
        }
        if (personaggiDisponibili == null || personaggiDisponibili.isEmpty()) {
            throw new IllegalArgumentException("La lista dei personaggi disponibili non puo essere vuota");
        }

        PersonaggioGiocabile personaggioScelto = trovaPersonaggio(nomePersonaggio, personaggiDisponibili);
        Progressione progressione = new Progressione(
                LIVELLO_INIZIALE,
                ESPERIENZA_INIZIALE,
                ESPERIENZA_PRIMO_LIVELLO
        );

        Giocatore giocatore = new Giocatore(nomeUtente, personaggioScelto, progressione);
        return new Partita(giocatore);
    }

    private PersonaggioGiocabile trovaPersonaggio(
            String nomePersonaggio,
            List<PersonaggioGiocabile> personaggiDisponibili) {

        PersonaggioGiocabile modello = personaggiDisponibili.stream()
                .filter(personaggio -> personaggio.getNome().equalsIgnoreCase(nomePersonaggio))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "Personaggio non trovato: " + nomePersonaggio));

        return copiaNuova(modello);
    }

    /**
     * Crea una nuova istanza indipendente del personaggio scelto, a partire
     * dal "modello" caricato una sola volta all'avvio del gioco.
     *
     * Evita che HP, inventario e bonus di livello di una partita precedente
     * rimangano attaccati al personaggio condiviso e si ripresentino
     * in una nuova partita.
     */
    private PersonaggioGiocabile copiaNuova(PersonaggioGiocabile modello) {
        return new PersonaggioGiocabile(
                modello.getNome(),
                modello.getStatisticheBase(),
                modello.getAbilita());
    }
}
