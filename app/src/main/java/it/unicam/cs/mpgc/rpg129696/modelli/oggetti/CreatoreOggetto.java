package it.unicam.cs.mpgc.rpg129696.modelli.oggetti;

import it.unicam.cs.mpgc.rpg129696.modelli.enumerati.TipoOggetto;

import java.util.HashMap;
import java.util.Map;

public class CreatoreOggetto {
    private static final Map<TipoOggetto, EffettoOggetto> effettoOggettoMap = new HashMap<>();


    public static void registraEffetto(TipoOggetto tipo, EffettoOggetto effetto) {
        effettoOggettoMap.put(tipo, effetto);
    }

    public static EffettoOggetto creaEffetto(TipoOggetto tipo) {
        EffettoOggetto effetto = effettoOggettoMap.get(tipo);
        if (effetto == null) throw new IllegalArgumentException("Effetto non registrato per: " + tipo);
        return effetto;
    }
}
