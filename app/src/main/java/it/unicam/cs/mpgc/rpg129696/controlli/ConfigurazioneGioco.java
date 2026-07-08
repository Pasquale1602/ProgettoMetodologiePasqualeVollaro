package it.unicam.cs.mpgc.rpg129696.controlli;

import it.unicam.cs.mpgc.rpg129696.modelli.oggetti.ConfigurazioneEffettiOggetto;
import it.unicam.cs.mpgc.rpg129696.modelli.oggetti.Oggetto;
import it.unicam.cs.mpgc.rpg129696.modelli.personaggio.Nemico;
import it.unicam.cs.mpgc.rpg129696.modelli.personaggio.PersonaggioGiocabile;
import it.unicam.cs.mpgc.rpg129696.persistenza.AbilitaLoader;
import it.unicam.cs.mpgc.rpg129696.persistenza.NemicoLoader;
import it.unicam.cs.mpgc.rpg129696.persistenza.OggettoLoader;
import it.unicam.cs.mpgc.rpg129696.persistenza.PersonaggioLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Prepara i dati principali necessari all'avvio del gioco.
 */
public class ConfigurazioneGioco {

    private final List<PersonaggioGiocabile> personaggi;
    private final List<Nemico> nemici;
    private final List<Nemico> boss;
    private final List<Oggetto> oggetti;

    public ConfigurazioneGioco() {
        ConfigurazioneEffettiOggetto.inizializza();

        AbilitaLoader abilitaLoader = new AbilitaLoader();
        PersonaggioLoader personaggioLoader = new PersonaggioLoader(abilitaLoader);
        NemicoLoader nemicoLoader = new NemicoLoader();
        OggettoLoader oggettoLoader = new OggettoLoader();

        this.personaggi = personaggioLoader.caricaPersonaggi();
        this.nemici = nemicoLoader.caricaNemici();
        this.boss = nemicoLoader.caricaBoss();
        this.oggetti = oggettoLoader.caricaOggetti();
    }

    public List<PersonaggioGiocabile> getPersonaggi() {
        return personaggi;
    }

    public List<Nemico> getNemici() {
        return nemici;
    }

    public List<Nemico> getBoss() {
        return boss;
    }

    /**
     * Restituisce nemici normali e boss uniti in un'unica lista.
     * Utile per ricostruire il nemico corrente durante il caricamento
     * di una partita salvata, quando non si sa a priori se il nemico
     * salvato fosse un boss o un nemico normale.
     */
    public List<Nemico> getTuttiNemici() {
        List<Nemico> tutti = new ArrayList<>(nemici);
        tutti.addAll(boss);
        return tutti;
    }

    public List<Oggetto> getOggetti() {
        return oggetti;
    }
}
