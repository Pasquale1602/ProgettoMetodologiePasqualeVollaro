package it.unicam.cs.mpgc.rpg129696.persistenza;

import it.unicam.cs.mpgc.rpg129696.modelli.oggetti.Contenuto;
import it.unicam.cs.mpgc.rpg129696.modelli.oggetti.Oggetto;
import it.unicam.cs.mpgc.rpg129696.modelli.partita.Partita;
import it.unicam.cs.mpgc.rpg129696.modelli.personaggio.Giocatore;
import it.unicam.cs.mpgc.rpg129696.modelli.personaggio.PersonaggioGiocabile;
import it.unicam.cs.mpgc.rpg129696.modelli.personaggio.Progressione;
import it.unicam.cs.mpgc.rpg129696.persistenza.dto.OggettoSalvatoDTO;
import it.unicam.cs.mpgc.rpg129696.persistenza.dto.PartitaDTO;

import java.util.List;

/**
 * Converte una partita del dominio in un DTO adatto al salvataggio
 * e ricostruisce una partita a partire da un DTO caricato da file.
 */
public class ConvertitorePartita {

    private ConvertitorePartita() {
    }

    /**
     * Converte una {@link Partita} in un {@link PartitaDTO}.
     *
     * @param partita la partita da convertire
     * @return DTO contenente i dati essenziali della partita
     * @throws IllegalArgumentException se la partita non e' valida
     */
    public static PartitaDTO toDTO(Partita partita) {
        if (partita == null) {
            throw new IllegalArgumentException("La partita non puo essere null");
        }

        Giocatore giocatore = partita.getGiocatore();
        if (giocatore == null) {
            throw new IllegalArgumentException("Il giocatore della partita non puo essere null");
        }

        PersonaggioGiocabile personaggio = giocatore.getPersonaggio();
        if (personaggio == null) {
            throw new IllegalArgumentException("Il personaggio del giocatore non puo essere null");
        }

        Progressione progressione = giocatore.getProgressione();
        if (progressione == null) {
            throw new IllegalArgumentException("La progressione del giocatore non puo essere null");
        }

        PartitaDTO dto = new PartitaDTO();
        dto.nomeUtente = giocatore.getNomeUtente();
        dto.nomePersonaggio = personaggio.getNome();
        dto.livello = progressione.getLivello();
        dto.esperienza = progressione.getEsperienza();
        dto.esperienzaPerLivelloSuccessivo = progressione.getEsperienzaPerLivelloSuccessivo();
        dto.hpAttuali = personaggio.getHpAttuali();
        dto.combattimentiVinti = partita.getCombattimentiVinti();
        dto.ultimoSalvataggio = partita.getUltimoSalvataggio() != null
                ? partita.getUltimoSalvataggio().toString()
                : null;

        for (Contenuto contenuto : personaggio.getInventario().getContenuto()) {
            OggettoSalvatoDTO oggettoSalvato = new OggettoSalvatoDTO();
            oggettoSalvato.idOggetto = contenuto.getOggetto().getId();
            oggettoSalvato.quantita = contenuto.getQuantita();

            dto.inventario.add(oggettoSalvato);
        }

        return dto;
    }

    /**
     * Ricostruisce una {@link Partita} a partire da un DTO di salvataggio.
     *
     * @param dto i dati della partita salvata
     * @param personaggiDisponibili i personaggi caricati dal file JSON
     * @param oggettiDisponibili gli oggetti caricati dal file JSON
     * @return la partita ricostruita
     * @throws IllegalArgumentException se i dati del salvataggio non sono validi
     */
    public static Partita fromDTO(
            PartitaDTO dto,
            List<PersonaggioGiocabile> personaggiDisponibili,
            List<Oggetto> oggettiDisponibili) {

        if (dto == null) {
            throw new IllegalArgumentException("Il DTO della partita non puo essere null");
        }
        if (personaggiDisponibili == null) {
            throw new IllegalArgumentException("La lista dei personaggi disponibili non puo essere null");
        }
        if (oggettiDisponibili == null) {
            throw new IllegalArgumentException("La lista degli oggetti disponibili non puo essere null");
        }

        PersonaggioGiocabile personaggio = trovaPersonaggio(dto.nomePersonaggio, personaggiDisponibili);
        personaggio.ripristinaHp(dto.hpAttuali);

        Progressione progressione = new Progressione(
                dto.livello,
                dto.esperienza,
                dto.esperienzaPerLivelloSuccessivo
        );

        Giocatore giocatore = new Giocatore(dto.nomeUtente, personaggio, progressione);

        for (OggettoSalvatoDTO oggettoSalvato : dto.inventario) {
            Oggetto oggetto = trovaOggetto(oggettoSalvato.idOggetto, oggettiDisponibili);
            personaggio.getInventario().aggiungiOggetto(oggetto, oggettoSalvato.quantita);
        }

        Partita partita = new Partita(giocatore);

        for (int i = 0; i < dto.combattimentiVinti; i++) {
            partita.registraVittoria();
        }

        return partita;
    }

    private static PersonaggioGiocabile trovaPersonaggio(
            String nomePersonaggio,
            List<PersonaggioGiocabile> personaggiDisponibili) {

        if (nomePersonaggio == null || nomePersonaggio.isBlank()) {
            throw new IllegalArgumentException("Il nome del personaggio salvato non puo essere vuoto");
        }

        return personaggiDisponibili.stream()
                .filter(personaggio -> personaggio.getNome().equals(nomePersonaggio))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "Personaggio non trovato nel salvataggio: " + nomePersonaggio));
    }

    private static Oggetto trovaOggetto(int idOggetto, List<Oggetto> oggettiDisponibili) {
        return oggettiDisponibili.stream()
                .filter(oggetto -> oggetto.getId() == idOggetto)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "Oggetto non trovato nel salvataggio con id: " + idOggetto));
    }
}
