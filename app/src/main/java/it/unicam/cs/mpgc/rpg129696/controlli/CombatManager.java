package it.unicam.cs.mpgc.rpg129696.controlli;

import it.unicam.cs.mpgc.rpg129696.modelli.oggetti.Contenuto;
import it.unicam.cs.mpgc.rpg129696.modelli.oggetti.Oggetto;
import it.unicam.cs.mpgc.rpg129696.modelli.personaggio.Giocatore;
import it.unicam.cs.mpgc.rpg129696.modelli.personaggio.Nemico;
import it.unicam.cs.mpgc.rpg129696.modelli.personaggio.PersonaggioGiocabile;
import it.unicam.cs.mpgc.rpg129696.view.InterfacciaUtente;

import java.util.List;
import java.util.Objects;

/**
 * Gestisce il combattimento a turni tra il giocatore e un nemico.
 *
 * La classe non legge input direttamente: espone metodi pubblici che possono
 * essere chiamati da una interfaccia grafica, da una console o da test.
 */
public class CombatManager {

    private final Giocatore giocatore;
    private final Nemico nemico;
    private final InterfacciaUtente ui;

    private boolean combattimentoInCorso;
    private int numeroTurno;

    public CombatManager(Giocatore giocatore, Nemico nemico, InterfacciaUtente ui) {
        this.giocatore = Objects.requireNonNull(giocatore, "Il giocatore non puo essere null");
        this.nemico = Objects.requireNonNull(nemico, "Il nemico non puo essere null");
        this.ui = Objects.requireNonNull(ui, "L'interfaccia utente non puo essere null");
        this.combattimentoInCorso = true;
        this.numeroTurno = 1;
    }

    /**
     * Inizializza lo scontro e mostra lo stato iniziale.
     */
    public void avviaScontro() {
        PersonaggioGiocabile eroe = giocatore.getPersonaggio();

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

        PersonaggioGiocabile eroe = giocatore.getPersonaggio();

        ui.mostraMessaggio(eroe.getNome() + " lancia un attacco base!");
        eroe.attacca(nemico);

        completaTurnoGiocatore();
    }

    /**
     * Esegue l'abilita speciale del personaggio giocabile.
     */
    public void eseguiAbilita() {
        if (!combattimentoInCorso) {
            return;
        }

        PersonaggioGiocabile eroe = giocatore.getPersonaggio();

        ui.mostraMessaggio(eroe.getNome() + " usa la sua abilita speciale!");
        eroe.usaAbilita(nemico);

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

        PersonaggioGiocabile eroe = giocatore.getPersonaggio();
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
        ui.mostraMessaggio("E' il turno di " + nemico.getNome() + "...");
        nemico.eseguiAzioneDiTurno(giocatore.getPersonaggio());
    }

    private void aggiornaModificatoriFineTurno() {
        giocatore.getPersonaggio().aggiornaTurnoModificatori();
        nemico.aggiornaTurnoModificatori();
    }

    private void mostraStatoCombattimento() {
        PersonaggioGiocabile eroe = giocatore.getPersonaggio();

        ui.mostraMessaggio("--- TURNO " + numeroTurno + " ---");
        ui.mostraMessaggio(eroe.getNome() + " HP: "
                + eroe.getHpAttuali() + "/" + eroe.getStatisticheAttuali().getHpMassimi());
        ui.mostraMessaggio(nemico.getNome() + " HP: "
                + nemico.getHpAttuali() + "/" + nemico.getStatisticheAttuali().getHpMassimi());
    }

    private boolean controllaFineScontro() {
        PersonaggioGiocabile eroe = giocatore.getPersonaggio();

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
        ui.mostraMessaggio("Vittoria! " + nemico.getNome() + " e' stato abbattuto!");
        ui.mostraMessaggio("Hai guadagnato " + nemico.getRicompensaEsperienza() + " punti esperienza!");

        int livelliGuadagnati = giocatore.aggiungiEsperienza(nemico.getRicompensaEsperienza());
        mostraAvanzamentoLivello(eroe, livelliGuadagnati);

        combattimentoInCorso = false;
    }

    private void mostraAvanzamentoLivello(PersonaggioGiocabile eroe, int livelliGuadagnati) {
        if (livelliGuadagnati == 1) {
            ui.mostraMessaggio(eroe.getNome() + " e' salito di livello!");
        } else if (livelliGuadagnati > 1) {
            ui.mostraMessaggio(eroe.getNome() + " e' salito di " + livelliGuadagnati + " livelli!");
        }
    }

    public boolean isCombattimentoInCorso() {
        return combattimentoInCorso;
    }

    public Giocatore getGiocatore() {
        return giocatore;
    }

    public Nemico getNemico() {
        return nemico;
    }
    /**
     * Restituisce lo stato corrente del combattimento.
     *
     * @return stato aggiornato dello scontro
     */
    public StatoCombattimento getStatoCombattimento() {
        PersonaggioGiocabile eroe = giocatore.getPersonaggio();

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
}