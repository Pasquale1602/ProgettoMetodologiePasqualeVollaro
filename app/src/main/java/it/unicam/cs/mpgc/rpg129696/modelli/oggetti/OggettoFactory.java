package it.unicam.cs.mpgc.rpg129696.modelli.oggetti;

import it.unicam.cs.mpgc.rpg129696.modelli.enumerati.TipoOggetto;
import it.unicam.cs.mpgc.rpg129696.modelli.modificatori.ModificatoreAttacco;
import it.unicam.cs.mpgc.rpg129696.modelli.modificatori.ModificatoreTemporaneo;

public class OggettoFactory {

    public static EffettoOggetto creaEffetto(TipoOggetto tipo) {
        return switch (tipo) {
            case POZIONE -> (personaggio, oggetto) ->
                    personaggio.cura(oggetto.getValoreCura());

            case ANTIDOTO -> (personaggio, oggetto) ->
                    personaggio.rimuoviVeleno();

            case POZIONE_FURIA -> (personaggio, oggetto) ->
                    personaggio.aggiungiModificatore (new ModificatoreTemporaneo(
                            new ModificatoreAttacco(oggetto.getValoreAttacco()),
                            oggetto.getTurni()
                    ));
        };
    }
}
