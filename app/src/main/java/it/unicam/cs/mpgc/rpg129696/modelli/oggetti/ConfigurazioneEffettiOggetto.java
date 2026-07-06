package it.unicam.cs.mpgc.rpg129696.modelli.oggetti;

import it.unicam.cs.mpgc.rpg129696.modelli.enumerati.TipoOggetto;
import it.unicam.cs.mpgc.rpg129696.modelli.modificatori.ModificatoreAttacco;
import it.unicam.cs.mpgc.rpg129696.modelli.modificatori.ModificatoreTemporaneo;

/**
 * Configura gli effetti associati ai diversi tipi di oggetto presenti nel gioco.
 *
 * Questa classe separa la registrazione degli effetti dalla classe
 * {@link CreatoreOggetto}, che si occupa solo di salvarli e recuperarli.
 * In questo modo, aggiungere un nuovo effetto richiede di modificare solo
 * questa configurazione, senza cambiare la logica di {@link Oggetto}.
 *
 * <p>Il metodo {@link #inizializza()} deve essere chiamato una sola volta
 * all'avvio del gioco, prima che gli oggetti vengano utilizzati.</p>
 */
public class ConfigurazioneEffettiOggetto {

    private static boolean inizializzato = false;

    private ConfigurazioneEffettiOggetto() {
    }

    /**
     * Registra gli effetti base degli oggetti disponibili nel gioco.
     *
     * <p>Se il metodo viene chiamato piu volte, la registrazione viene eseguita
     * solo alla prima chiamata.</p>
     */
    public static void inizializza() {
        if (inizializzato) {
            return;
        }

        CreatoreOggetto.registraEffetto(
                TipoOggetto.POZIONE,
                (personaggio, oggetto) -> personaggio.cura(oggetto.getValoreCura())
        );

        CreatoreOggetto.registraEffetto(
                TipoOggetto.ANTIDOTO,
                (personaggio, oggetto) -> personaggio.rimuoviVeleno()
        );

        CreatoreOggetto.registraEffetto(
                TipoOggetto.POZIONE_FURIA,
                (personaggio, oggetto) -> personaggio.aggiungiModificatore(
                        new ModificatoreTemporaneo(
                                new ModificatoreAttacco(oggetto.getValoreAttacco()),
                                oggetto.getTurni()
                        )
                )
        );

        inizializzato = true;
    }
}
