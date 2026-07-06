package it.unicam.cs.mpgc.rpg129696.modelli.oggetti;

import it.unicam.cs.mpgc.rpg129696.modelli.enumerati.TipoOggetto;

import java.util.HashMap;
import java.util.Map;

/**
 * Classe responsabile della registrazione e del recupero degli effetti degli oggetti.
 */
public class CreatoreOggetto {

    private static final Map<TipoOggetto, EffettoOggetto> effettiRegistrati = new HashMap<>();

    private CreatoreOggetto() {
    }

    public static void registraEffetto(TipoOggetto tipo, EffettoOggetto effetto) {
        if (tipo == null) {
            throw new IllegalArgumentException("Il tipo oggetto non puo essere null");
        }
        if (effetto == null) {
            throw new IllegalArgumentException("L'effetto oggetto non puo essere null");
        }

        effettiRegistrati.put(tipo, effetto);
    }

    public static EffettoOggetto creaEffetto(TipoOggetto tipo) {
        if (tipo == null) {
            throw new IllegalArgumentException("Il tipo oggetto non puo essere null");
        }

        EffettoOggetto effetto = effettiRegistrati.get(tipo);

        if (effetto == null) {
            throw new IllegalArgumentException("Effetto non registrato per: " + tipo);
        }

        return effetto;
    }
}
