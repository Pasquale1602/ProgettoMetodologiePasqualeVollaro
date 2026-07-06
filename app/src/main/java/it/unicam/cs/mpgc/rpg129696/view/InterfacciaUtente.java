package it.unicam.cs.mpgc.rpg129696.view;

/**
 * Definisce le operazioni minime che una interfaccia utente deve offrire
 * al resto del gioco.
 *
 * In questo modo i controlli non dipendono da una specifica interfaccia
 * grafica o testuale.
 */
public interface InterfacciaUtente {

    /**
     * Mostra un messaggio prodotto dal gioco.
     *
     * @param messaggio il messaggio da mostrare
     */
    void mostraMessaggio(String messaggio);
}