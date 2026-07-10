package it.unicam.cs.mpgc.rpg129696.view;

/**
 * Definisce l'unico contratto che il motore di gioco richiede a una UI:
 * la capacità di mostrare un messaggio prodotto dal gioco.
 *
 * Le scelte dell'utente (attacco, abilita, oggetto...) non passano da questa
 * interfaccia: ogni client (GUI, console, futuri client mobile/web) chiama
 * direttamente i metodi pubblici di {@link it.unicam.cs.mpgc.rpg129696.controlli.CombatManager},
 * nel modo più naturale per la propria piattaforma (bottoni, menu testuale, richieste HTTP...).
 */
public interface InterfacciaUtente {

    /**
     * Mostra un messaggio prodotto dal gioco all'utente.
     *
     * @param messaggio testo da visualizzare
     */
    void mostraMessaggio(String messaggio);
}