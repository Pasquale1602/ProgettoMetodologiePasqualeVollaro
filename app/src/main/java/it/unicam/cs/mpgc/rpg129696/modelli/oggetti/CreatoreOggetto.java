package it.unicam.cs.mpgc.rpg129696.modelli.oggetti;

import it.unicam.cs.mpgc.rpg129696.modelli.enumerati.TipoOggetto;

import java.util.HashMap;
import java.util.Map;

/**
 * Gestisce la registrazione e il recupero degli effetti associati ai tipi di oggetto.
 * Gli effetti vengono memorizzati in un registro condiviso e recuperati in base al
 * {@link TipoOggetto} associato.
 */
public class CreatoreOggetto {


    private static final Map<TipoOggetto, EffettoOggetto> effettiRegistrati = new HashMap<>();


    private CreatoreOggetto() {
    }
    /**
     * Registra un effetto associandolo a un tipo di oggetto.
     *
     * Se era già presente un effetto associato allo stesso tipo, quello precedente
     * viene sostituito.
     *
     * @param tipo tipo di oggetto a cui associare l'effetto
     * @param effetto effetto da eseguire
     *
     * @throws IllegalArgumentException se il tipo o l'effetto sono null
     */
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
