package it.unicam.cs.mpgc.rpg129696.persistenza;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.unicam.cs.mpgc.rpg129696.modelli.enumerati.SlotSalvataggio;
import it.unicam.cs.mpgc.rpg129696.modelli.oggetti.Oggetto;
import it.unicam.cs.mpgc.rpg129696.modelli.partita.Partita;
import it.unicam.cs.mpgc.rpg129696.modelli.personaggio.PersonaggioGiocabile;
import it.unicam.cs.mpgc.rpg129696.persistenza.dto.PartitaDTO;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Gestisce il salvataggio e il caricamento delle partite su file JSON.
 */
public class GestoreSalvataggi {

    private final Gson gson;

    public GestoreSalvataggi() {
        this.gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
    }

    /**
     * Converte e salva una partita del dominio in un file JSON.
     *
     * @param partita la partita da salvare
     * @param percorso il percorso del file di salvataggio
     */
    public void salva(Partita partita, String percorso) {
        if (partita == null) {
            throw new IllegalArgumentException("La partita da salvare non puo essere null");
        }

        partita.aggiornaUltimoSalvataggio();
        PartitaDTO dto = ConvertitorePartita.toDTO(partita);
        salva(dto, percorso);
    }

    /**
     * Salva una partita nello slot indicato.
     *
     * @param partita la partita da salvare
     * @param slot lo slot di salvataggio
     */
    public void salva(Partita partita, SlotSalvataggio slot) {
        if (slot == null) {
            throw new IllegalArgumentException("Lo slot di salvataggio non puo essere null");
        }

        salva(partita, slot.getPercorso());
    }

    /**
     * Salva una partita in un file JSON.
     *
     * @param partita la partita da salvare
     * @param percorso il percorso del file di salvataggio
     * @throws IllegalArgumentException se la partita o il percorso non sono validi
     * @throws RuntimeException se si verifica un errore durante la scrittura del file
     */
    public void salva(PartitaDTO partita, String percorso) {
        if (partita == null) {
            throw new IllegalArgumentException("La partita da salvare non puo essere null");
        }
        if (percorso == null || percorso.isBlank()) {
            throw new IllegalArgumentException("Il percorso del salvataggio non puo essere vuoto");
        }

        try (FileWriter writer = new FileWriter(percorso)) {
            gson.toJson(partita, writer);
        } catch (IOException e) {
            throw new RuntimeException("Errore durante il salvataggio della partita in: " + percorso, e);
        }
    }

    /**
     * Carica una partita da un file JSON.
     *
     * @param percorso il percorso del file di salvataggio
     * @return i dati della partita caricata
     * @throws IllegalArgumentException se il percorso non e' valido
     * @throws RuntimeException se si verifica un errore durante la lettura del file
     */
    public PartitaDTO carica(String percorso) {
        if (percorso == null || percorso.isBlank()) {
            throw new IllegalArgumentException("Il percorso del salvataggio non puo essere vuoto");
        }

        try (FileReader reader = new FileReader(percorso)) {
            return gson.fromJson(reader, PartitaDTO.class);
        } catch (IOException e) {
            throw new RuntimeException("Errore durante il caricamento della partita da: " + percorso, e);
        }
    }

    /**
     * Carica una partita completa a partire da un file JSON.
     *
     * @param percorso il percorso del file di salvataggio
     * @param personaggiDisponibili i personaggi disponibili caricati dal JSON
     * @param oggettiDisponibili gli oggetti disponibili caricati dal JSON
     * @return la partita ricostruita
     */
    public Partita caricaPartita(
            String percorso,
            List<PersonaggioGiocabile> personaggiDisponibili,
            List<Oggetto> oggettiDisponibili) {

        PartitaDTO dto = carica(percorso);
        return ConvertitorePartita.fromDTO(dto, personaggiDisponibili, oggettiDisponibili);
    }

    /**
     * Carica una partita dallo slot indicato.
     *
     * @param slot lo slot di salvataggio
     * @param personaggiDisponibili i personaggi disponibili caricati dal JSON
     * @param oggettiDisponibili gli oggetti disponibili caricati dal JSON
     * @return la partita ricostruita
     */
    public Partita caricaPartita(
            SlotSalvataggio slot,
            List<PersonaggioGiocabile> personaggiDisponibili,
            List<Oggetto> oggettiDisponibili) {

        if (slot == null) {
            throw new IllegalArgumentException("Lo slot di salvataggio non puo essere null");
        }

        return caricaPartita(slot.getPercorso(), personaggiDisponibili, oggettiDisponibili);
    }
}
