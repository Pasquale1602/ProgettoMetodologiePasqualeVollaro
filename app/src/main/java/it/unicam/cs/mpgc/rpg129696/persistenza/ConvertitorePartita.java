package it.unicam.cs.mpgc.rpg129696.persistenza;

import it.unicam.cs.mpgc.rpg129696.modelli.oggetti.Contenuto;
import it.unicam.cs.mpgc.rpg129696.modelli.oggetti.Oggetto;
import it.unicam.cs.mpgc.rpg129696.modelli.partita.Partita;
import it.unicam.cs.mpgc.rpg129696.modelli.personaggio.Giocatore;
import it.unicam.cs.mpgc.rpg129696.modelli.personaggio.Nemico;
import it.unicam.cs.mpgc.rpg129696.modelli.personaggio.PersonaggioGiocabile;
import it.unicam.cs.mpgc.rpg129696.modelli.personaggio.Progressione;
import it.unicam.cs.mpgc.rpg129696.modelli.personaggio.Statistiche;
import it.unicam.cs.mpgc.rpg129696.persistenza.dto.OggettoSalvatoDTO;
import it.unicam.cs.mpgc.rpg129696.persistenza.dto.PartitaDTO;

import java.util.List;
import java.time.LocalDateTime;

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

        Nemico nemicoCorrente = partita.getNemicoCorrente();
        if (nemicoCorrente != null) {
            dto.nomeNemico = nemicoCorrente.getNome();
            dto.hpNemico = nemicoCorrente.getHpAttuali();
            dto.comportamentoNemico = nemicoCorrente.getTipoComportamento();
        }

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
            List<Oggetto> oggettiDisponibili,
            List<Nemico> nemiciDisponibili) {

        if (dto == null) {
            throw new IllegalArgumentException("Il DTO della partita non puo essere null");
        }
        if (personaggiDisponibili == null) {
            throw new IllegalArgumentException("La lista dei personaggi disponibili non puo essere null");
        }
        if (oggettiDisponibili == null) {
            throw new IllegalArgumentException("La lista degli oggetti disponibili non puo essere null");
        }
        if (nemiciDisponibili == null) {
            throw new IllegalArgumentException("La lista dei nemici disponibili non puo essere null");
        }

        PersonaggioGiocabile personaggio = trovaPersonaggio(dto.nomePersonaggio, personaggiDisponibili);

        int livelliDaApplicare = dto.livello - 1; // livello 1 = nessun bonus da applicare
        if (livelliDaApplicare > 0) {
            personaggio.riceviBonusLevelUp(livelliDaApplicare);
        }

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
        partita.ripristinaCombattimentiVinti(dto.combattimentiVinti);

        if (dto.ultimoSalvataggio != null && !dto.ultimoSalvataggio.isBlank()) {
            partita.ripristinaUltimoSalvataggio(LocalDateTime.parse(dto.ultimoSalvataggio));
        }

        if (dto.nomeNemico != null && !dto.nomeNemico.isBlank()) {
            Nemico nemicoRicostruito = ricostruisciNemico(dto, nemiciDisponibili);
            partita.setNemicoCorrente(nemicoRicostruito);
        }

        return partita;
    }
    /**
     * Ricostruisce un nemico utilizzando il modello presente tra i nemici disponibili
     *
     * @param dto dati del nemico salvato
     * @param nemiciDisponibili lista dei nemici disponibili
     * @return il nemico ricostruito
     * @throws IllegalArgumentException se il nemico salvato non viene trovato
     */
    private static Nemico ricostruisciNemico(PartitaDTO dto, List<Nemico> nemiciDisponibili) {
        Nemico modello = nemiciDisponibili.stream()
                .filter(n -> n.getNome().equals(dto.nomeNemico)
                        && n.getTipoComportamento() == dto.comportamentoNemico)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "Nemico non trovato nel salvataggio: " + dto.nomeNemico));

        Nemico nemico = new Nemico(
                modello.getNome(),
                new Statistiche(modello.getStatisticheBase()),
                modello.getRicompensaEsperienza(),
                modello.getComportamentoNemico().nuovaIstanza(),  
                modello.getTipoComportamento());

        nemico.ripristinaHp(dto.hpNemico);
        return nemico;
    }
    /**
     * Cerca un personaggio tra quelli disponibili e crea una nuova istanza
     * con i dati del modello trovato.
     *
     * @param nomePersonaggio nome del personaggio da cercare
     * @param personaggiDisponibili lista dei personaggi disponibili
     * @return il personaggio ricostruito
     * @throws IllegalArgumentException se il nome del personaggio è vuoto
     *         o se il personaggio non viene trovato
     */
    private static PersonaggioGiocabile trovaPersonaggio(
            String nomePersonaggio,
            List<PersonaggioGiocabile> personaggiDisponibili) {

        if (nomePersonaggio == null || nomePersonaggio.isBlank()) {
            throw new IllegalArgumentException("Il nome del personaggio salvato non puo essere vuoto");
        }

        PersonaggioGiocabile modello = personaggiDisponibili.stream()
                .filter(personaggio -> personaggio.getNome().equals(nomePersonaggio))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "Personaggio non trovato nel salvataggio: " + nomePersonaggio));

        return new PersonaggioGiocabile(
                modello.getNome(),
                modello.getStatisticheBase(),
                modello.getAbilita());
    }
    /**
     * Cerca un oggetto nella lista degli oggetti disponibili tramite il suo identificativo.
     *
     * @param idOggetto identificativo dell'oggetto da cercare
     * @param oggettiDisponibili lista degli oggetti disponibili
     * @return l'oggetto corrispondente all'identificativo fornito
     * @throws IllegalArgumentException se l'oggetto non viene trovato
     */
    private static Oggetto trovaOggetto(int idOggetto, List<Oggetto> oggettiDisponibili) {
        return oggettiDisponibili.stream()
                .filter(oggetto -> oggetto.getId() == idOggetto)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "Oggetto non trovato nel salvataggio con id: " + idOggetto));
    }
}
