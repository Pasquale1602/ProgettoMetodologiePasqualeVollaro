package it.unicam.cs.mpgc.rpg129696.modelli.abilita;

import it.unicam.cs.mpgc.rpg129696.modelli.enumerati.TipoAbilita;
import it.unicam.cs.mpgc.rpg129696.modelli.modificatori.*;
import it.unicam.cs.mpgc.rpg129696.persistenza.dto.AbilitaDTO;

/**
 *Classe responsabile della creazione delle abilita a partire dai dati letti dal JSON
 * Ogni abilita viene costruita in base al suo {@link TipoAbilita}.
 */
public class CreatoreAbilita {

    private CreatoreAbilita() {
    }
    /**
     * Crea un'abilità a partire dai dati contenuti nel DTO.
     * L'abilità viene costruita in base al tipo specificato nel DTO.
     *
     * @param dto dati necessari per la creazione dell'abilità
     * @return l'abilità corrispondente al tipo indicato
     * @throws IllegalArgumentException se il DTO è null,
     *         se il tipo dell'abilità è vuoto o non valido
     */
    public static Abilita crea(AbilitaDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("Il DTO dell'abilita non puo essere null");
        }
        if (dto.tipo == null || dto.tipo.isBlank()) {
            throw new IllegalArgumentException("Il tipo dell'abilita non puo essere vuoto");
        }

        TipoAbilita tipo = TipoAbilita.valueOf(dto.tipo.toUpperCase());

        return switch (tipo) {
            case FURIA -> creaFuria(dto);
            case CHIAMATA_ALLE_ARMI -> creaChiamataAlleArmi(dto);
            case VAPORI_SOFFOCANTI -> creaVaporiSoffocanti(dto);
            case COLPO_ALLE_SPALLE -> creaColpoAlleSpalle(dto);
        };
    }

    private static Abilita creaFuria(AbilitaDTO dto) {
        return (utilizzatore, bersaglio) -> {
            utilizzatore.aggiungiModificatore(
                    new ModificatoreTemporaneo(new ModificatoreAttacco(dto.valoreAttacco), dto.turni));
            utilizzatore.aggiungiModificatore(
                    new ModificatoreTemporaneo(new ModificatoreDifesa(dto.valoreDifesa), dto.turni));
        };
    }

    private static Abilita creaChiamataAlleArmi(AbilitaDTO dto) {
        return (utilizzatore, bersaglio) -> {
            utilizzatore.aggiungiModificatore(
                    new ModificatoreTemporaneo(new ModificatoreCura(dto.valoreCura), dto.turni));
            utilizzatore.aggiungiModificatore(
                    new ModificatoreTemporaneo(new ModificatoreDifesa(dto.valoreDifesa), dto.turni));
        };
    }

    private static Abilita creaVaporiSoffocanti(AbilitaDTO dto) {
        return (utilizzatore, bersaglio) ->
                bersaglio.aggiungiModificatore(
                        new ModificatoreTemporaneo(new ModificatoreVeleno(dto.dannoVeleno), dto.turni));
    }

    private static Abilita creaColpoAlleSpalle(AbilitaDTO dto) {
        return (utilizzatore, bersaglio) ->
                bersaglio.prendiDanno(
                        (int)(utilizzatore.getStatisticheAttuali().getAttacco() * dto.moltiplicatoreCritico));
    }
}
