package it.unicam.cs.mpgc.rpg129696.modelli.abilita;

import it.unicam.cs.mpgc.rpg129696.modelli.enumerati.TipoAbilita;
import it.unicam.cs.mpgc.rpg129696.modelli.modificatori.*;
import it.unicam.cs.mpgc.rpg129696.persistenza.dto.AbilitaDTO;

/**
 * Factory che crea istanze di {@link Abilita} a partire dai dati letti dal JSON.
 * Ogni abilità viene costruita come espressione lambda in base al {@link TipoAbilita}.
 *
 * <p>Per aggiungere una nuova abilità è sufficiente:
 * <ol>
 *   <li>Aggiungere il valore corrispondente in {@link TipoAbilita}</li>
 *   <li>Aggiungere il caso nello switch di questo metodo</li>
 *   <li>Aggiungere la voce nel file {@code abilita.json}</li>
 * </ol>
 * Nessuna altra classe deve essere modificata.</p>
 */

public class AbilitaFactory{

    /**
     * Crea un'istanza di {@link Abilita} a partire dal DTO letto dal JSON.
     *
     * @param dto i dati dell'abilità letti dal JSON
     * @return l'abilità pronta per essere usata in combattimento
     * @throws IllegalArgumentException se il tipo di abilità non è riconosciuto
     */

    public static Abilita crea(AbilitaDTO dto) {
        return switch (TipoAbilita.valueOf(dto.tipo)) {
            case FURIA -> (utilizzatore, bersaglio) -> {
                utilizzatore.aggiungiModificatore(
                        new ModificatoreTemporaneo(new ModificatoreAttacco(dto.valoreAttacco), dto.turni));
                utilizzatore.aggiungiModificatore(
                        new ModificatoreTemporaneo(new ModificatoreDifesa(dto.valoreDifesa), dto.turni));
            };
            case CHIAMATA_ALLE_ARMI -> (utilizzatore, bersaglio) -> {
                utilizzatore.aggiungiModificatore(
                        new ModificatoreTemporaneo(new ModificatoreCura(dto.valoreCura), dto.turni));
                utilizzatore.aggiungiModificatore(
                        new ModificatoreTemporaneo(new ModificatoreDifesa(dto.valoreDifesa), dto.turni));
            };
            case VAPORI_SOFFOCANTI -> (utilizzatore, bersaglio) ->
                bersaglio.aggiungiModificatore(
                        new ModificatoreTemporaneo(new ModificatoreVeleno(dto.dannoVeleno), dto.turni));

            case COLPO_ALLE_SPALLE -> (utilizzatore, bersaglio) ->
                    bersaglio.prendiDanno(utilizzatore.getStatisticheAttuali().getAttacco() * dto.moltiplicatoreCritico);
            //in questo caso invoco direttamente il metodo prendiDanno per permettere all'abilità di infliggere danni "puri"
            //ovvero che ignorino la difesa;
        };
    }
}
