package it.unicam.cs.mpgc.rpg129696.controlli;

import it.unicam.cs.mpgc.rpg129696.modelli.oggetti.Contenuto;
import it.unicam.cs.mpgc.rpg129696.modelli.oggetti.Oggetto;
import it.unicam.cs.mpgc.rpg129696.modelli.partita.Partita;
import it.unicam.cs.mpgc.rpg129696.modelli.personaggio.Giocatore;
import it.unicam.cs.mpgc.rpg129696.modelli.personaggio.Nemico;
import it.unicam.cs.mpgc.rpg129696.modelli.personaggio.PersonaggioGiocabile;
import it.unicam.cs.mpgc.rpg129696.view.InterfacciaUtente;

import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 * Gestisce il combattimento a turni tra il giocatore e il nemico corrente
 * della partita.
 *
 * La classe non legge input direttamente: espone metodi pubblici che possono
 * essere chiamati da una interfaccia grafica, da una console o da test.
 */
public class CombatManager {

    private static final int PROBABILITA_DROP_PERCENTO = 50;

    private final Partita partita;
    private final InterfacciaUtente ui;
    private final List<Oggetto> oggettiDisponibili;
    private final GestoreRicompense gestoreRicompense;
    private final Random random;

    private boolean combattimentoInCorso;
    private int numeroTurno;

    public CombatManager(Partita partita, InterfacciaUtente ui) {
        this(partita, ui, List.of());
    }

    public CombatManager(Partita partita, InterfacciaUtente ui, List<Oggetto> oggettiDisponibili) {
        this(partita, ui, oggettiDisponibili, new GestoreRicompense(), new Random());
    }

    /**
     * Costruttore completo che permette di iniettare le dipendenze
     * responsabili della casualita'.
     *
     * @param partita la partita corrente
     * @param ui l'interfaccia utente usata per mostrare i messaggi
     * @param oggettiDisponibili gli oggetti che possono essere ottenuti come ricompensa
     * @param gestoreRicompense gestore responsabile dell'estrazione della ricompensa
     * @param random generatore casuale
     */
    public CombatManager(
            Partita partita,
            InterfacciaUtente ui,
            List<Oggetto> oggettiDisponibili,
            GestoreRicompense gestoreRicompense,
            Random random) {

        this.partita = Objects.requireNonNull(partita, "La partita non puo essere null");

        if (partita.getGiocatore() == null) {
            throw new IllegalArgumentException("La partita deve avere un giocatore");
        }
        if (partita.getGiocatore().getPersonaggio() == null) {
            throw new IllegalArgumentException("La partita deve avere un personaggio giocabile");
        }
        if (partita.getNemicoCorrente() == null) {
            throw new IllegalArgumentException("La partita deve avere un nemico corrente");
        }

        this.ui = Objects.requireNonNull(ui, "L'interfaccia utente non puo essere null");
        this.oggettiDisponibili = oggettiDisponibili == null ? List.of() : oggettiDisponibili;
        this.gestoreRicompense = Objects.requireNonNull(
                gestoreRicompense,
                "Il gestore ricompense non puo essere null"
        );
        this.random = Objects.requireNonNull(random, "Il generatore casuale non puo essere null");

        this.combattimentoInCorso = true;
        this.numeroTurno = 1;
    }

    /**
     * Inizializza lo scontro e mostra lo stato iniziale.
     */
    public void avviaScontro() {
        PersonaggioGiocabile eroe = getEroe();
        Nemico nemico = getNemicoCorrente();

        ui.mostraMessaggio("Lo scontro ha inizio! " + eroe.getNome() + " VS " + nemico.getNome());
        mostraStatoCombattimento();
    }

    /**
     * Esegue un attacco base del personaggio giocabile.
     */
    public void eseguiAttaccoBase() {
        if (!combattimentoInCorso) {
            return;
        }

        PersonaggioGiocabile eroe = getEroe();

        ui.mostraMessaggio(eroe.getNome() + " lancia un attacco base!");
        eroe.attacca(getNemicoCorrente());

        completaTurnoGiocatore();
    }

    /**
     * Esegue l'abilita speciale del personaggio giocabile.
     */
    public void eseguiAbilita() {
        if (!combattimentoInCorso) {
            return;
        }

        PersonaggioGiocabile eroe = getEroe();

        ui.mostraMessaggio(eroe.getNome() + " usa la sua abilita speciale!");
        eroe.usaAbilita(getNemicoCorrente());

        completaTurnoGiocatore();
    }

    /**
     * Usa un oggetto dell'inventario del personaggio giocabile.
     *
     * @param indiceOggetto indice dell'oggetto scelto nell'inventario
     */
    public void usaOggetto(int indiceOggetto) {
        if (!combattimentoInCorso) {
            return;
        }

        PersonaggioGiocabile eroe = getEroe();
        List<Contenuto> inventario = eroe.getInventario().getContenuto();

        if (indiceOggetto < 0 || indiceOggetto >= inventario.size()) {
            ui.mostraMessaggio("Oggetto non valido.");
            return;
        }

        Oggetto oggetto = inventario.get(indiceOggetto).getOggetto();

        ui.mostraMessaggio(eroe.getNome() + " usa " + oggetto.getNome() + "!");
        oggetto.usa(eroe);
        eroe.getInventario().rimuoviOggetto(oggetto);

        completaTurnoGiocatore();
    }

    private void completaTurnoGiocatore() {
        if (controllaFineScontro()) {
            return;
        }

        eseguiTurnoNemico();

        if (controllaFineScontro()) {
            return;
        }

        aggiornaModificatoriFineTurno();

        if (controllaFineScontro()) {
            return;
        }

        numeroTurno++;
        mostraStatoCombattimento();
    }

    private void eseguiTurnoNemico() {
        Nemico nemico = getNemicoCorrente();

        ui.mostraMessaggio("E' il turno di " + nemico.getNome() + "...");
        nemico.eseguiAzioneDiTurno(getEroe());
    }

    private void aggiornaModificatoriFineTurno() {
        getEroe().aggiornaTurnoModificatori();
        getNemicoCorrente().aggiornaTurnoModificatori();
    }

    private void mostraStatoCombattimento() {
        PersonaggioGiocabile eroe = getEroe();
        Nemico nemico = getNemicoCorrente();

        ui.mostraMessaggio("--- TURNO " + numeroTurno + " ---");
        ui.mostraMessaggio(eroe.getNome() + " HP: "
                + eroe.getHpAttuali() + "/" + eroe.getStatisticheAttuali().getHpMassimi());
        ui.mostraMessaggio(nemico.getNome() + " HP: "
                + nemico.getHpAttuali() + "/" + nemico.getStatisticheAttuali().getHpMassimi());
    }

    private boolean controllaFineScontro() {
        PersonaggioGiocabile eroe = getEroe();
        Nemico nemico = getNemicoCorrente();

        if (!eroe.isVivo()) {
            gestisciSconfitta();
            return true;
        }

        if (!nemico.isVivo()) {
            gestisciVittoria(eroe);
            return true;
        }

        return false;
    }

    private void gestisciSconfitta() {
        ui.mostraMessaggio("Sei stato sconfitto... GAME OVER");
        combattimentoInCorso = false;
    }

    private void gestisciVittoria(PersonaggioGiocabile eroe) {
        Nemico nemico = getNemicoCorrente();

        ui.mostraMessaggio("Vittoria! " + nemico.getNome() + " e' stato abbattuto!");
        ui.mostraMessaggio("Hai guadagnato " + nemico.getRicompensaEsperienza() + " punti esperienza!");

        int livelliGuadagnati = getGiocatoreCorrente()
                .aggiungiEsperienza(nemico.getRicompensaEsperienza());
        mostraAvanzamentoLivello(eroe, livelliGuadagnati);

        assegnaRicompensaOggetto(eroe);
        partita.registraVittoria();

        combattimentoInCorso = false;
    }

    private void assegnaRicompensaOggetto(PersonaggioGiocabile eroe) {
        if (oggettiDisponibili.isEmpty()) {
            return;
        }

        if (random.nextInt(100) >= PROBABILITA_DROP_PERCENTO) {
            return;
        }

        Oggetto oggettoTrovato = gestoreRicompense.estraiOggetto(oggettiDisponibili);
        if (oggettoTrovato == null) {
            return;
        }

        boolean aggiunto = eroe.getInventario().aggiungiOggetto(oggettoTrovato, 1);
        if (aggiunto) {
            ui.mostraMessaggio(getNemicoCorrente().getNome()
                    + " ha lasciato cadere: " + oggettoTrovato.getNome() + "!");
        } else {
            ui.mostraMessaggio("Hai trovato "
                    + oggettoTrovato.getNome() + ", ma l'inventario e' pieno!");
        }
    }

    private void mostraAvanzamentoLivello(PersonaggioGiocabile eroe, int livelliGuadagnati) {
        if (livelliGuadagnati == 1) {
            ui.mostraMessaggio(eroe.getNome() + " e' salito di livello!");
        } else if (livelliGuadagnati > 1) {
            ui.mostraMessaggio(eroe.getNome() + " e' salito di " + livelliGuadagnati + " livelli!");
        }
    }

    /**
     * Restituisce lo stato corrente del combattimento.
     *
     * @return stato aggiornato dello scontro
     */
    public StatoCombattimento getStatoCombattimento() {
        PersonaggioGiocabile eroe = getEroe();
        Nemico nemico = getNemicoCorrente();

        return new StatoCombattimento(
                eroe.getNome(),
                eroe.getHpAttuali(),
                eroe.getStatisticheAttuali().getHpMassimi(),
                nemico.getNome(),
                nemico.getHpAttuali(),
                nemico.getStatisticheAttuali().getHpMassimi(),
                numeroTurno,
                combattimentoInCorso
        );
    }

    public boolean isCombattimentoInCorso() {
        return combattimentoInCorso;
    }

    public Giocatore getGiocatore() {
        return getGiocatoreCorrente();
    }

    public Nemico getNemico() {
        return getNemicoCorrente();
    }

    private Giocatore getGiocatoreCorrente() {
        return partita.getGiocatore();
    }

    private PersonaggioGiocabile getEroe() {
        return partita.getGiocatore().getPersonaggio();
    }

    private Nemico getNemicoCorrente() {
        return partita.getNemicoCorrente();
    }
}