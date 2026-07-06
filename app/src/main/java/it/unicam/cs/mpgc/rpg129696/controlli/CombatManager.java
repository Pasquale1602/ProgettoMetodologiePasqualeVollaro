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
 * Gestisce il flusso di un combattimento a turni tra un giocatore e un nemico.
 */
public class CombatManager {

    private final Giocatore giocatore;
    private final Nemico nemico;
    private final InterfacciaUtente ui;
    private boolean combattimentoInCorso;

    public CombatManager(Giocatore giocatore, Nemico nemico, InterfacciaUtente ui) {
        this.giocatore = Objects.requireNonNull(giocatore, "Il giocatore non puo essere null");
        this.nemico = Objects.requireNonNull(nemico, "Il nemico non puo essere null");
        this.ui = Objects.requireNonNull(ui, "L'interfaccia utente non puo essere null");
        this.combattimentoInCorso = true;
    }

    /**
     * Avvia il combattimento e gestisce l'alternanza dei turni.
     */
    public void avviaScontro() {
        PersonaggioGiocabile eroe = giocatore.getPersonaggio();
        ui.mostraMessaggio("Lo scontro ha inizio! " + eroe.getNome() + " VS " + nemico.getNome() + "\n");

        int numeroTurno = 1;

        while (combattimentoInCorso) {
            mostraStatoCombattimento(eroe, numeroTurno);

            eseguiTurnoGiocatore();
            if (controllaFineScontro()) {
                break;
            }

            eseguiTurnoNemico();
            if (controllaFineScontro()) {
                break;
            }

            aggiornaModificatoriFineTurno(eroe);
            if (controllaFineScontro()) {
                break;
            }

            numeroTurno++;
            ui.mostraMessaggio("");
        }
    }

    private void mostraStatoCombattimento(PersonaggioGiocabile eroe, int numeroTurno) {
        ui.mostraMessaggio("--- TURNO " + numeroTurno + " ---");
        ui.mostraMessaggio(eroe.getNome() + " HP: "
                + eroe.getHpAttuali() + "/" + eroe.getStatisticheAttuali().getHpMassimi());
        ui.mostraMessaggio(nemico.getNome() + " HP: "
                + nemico.getHpAttuali() + "/" + nemico.getStatisticheAttuali().getHpMassimi() + "\n");
    }

    private void eseguiTurnoGiocatore() {
        PersonaggioGiocabile eroe = giocatore.getPersonaggio();
        ui.mostraMessaggio("Tocca a te, " + eroe.getNome() + "!");

        boolean azioneEseguita = false;

        while (!azioneEseguita) {
            int scelta = ui.richiediSceltaAzionePrincipale();

            switch (scelta) {
                case 1 -> {
                    ui.mostraMessaggio(eroe.getNome() + " lancia un attacco base!");
                    eroe.attacca(nemico);
                    azioneEseguita = true;
                }
                case 2 -> {
                    ui.mostraMessaggio(eroe.getNome() + " usa la sua abilita speciale!");
                    eroe.usaAbilita(nemico);
                    azioneEseguita = true;
                }
                case 3 -> azioneEseguita = gestisciUsoOggetto();
                default -> ui.mostraMessaggio("Scelta non valida!");
            }
        }
    }

    private boolean gestisciUsoOggetto() {
        PersonaggioGiocabile eroe = giocatore.getPersonaggio();
        List<Contenuto> inventario = eroe.getInventario().getContenuto();

        if (inventario.isEmpty()) {
            ui.mostraMessaggio("Il tuo inventario e' vuoto! Scegli un'altra azione.");
            return false;
        }

        int indiceScelto = ui.richiediSceltaOggetto(inventario);

        if (indiceScelto < 0 || indiceScelto >= inventario.size()) {
            ui.mostraMessaggio("Azione annullata.");
            return false;
        }

        Oggetto oggetto = inventario.get(indiceScelto).getOggetto();

        ui.mostraMessaggio(eroe.getNome() + " usa " + oggetto.getNome() + "!");
        oggetto.usa(eroe);
        eroe.getInventario().rimuoviOggetto(oggetto);

        return true;
    }

    private void eseguiTurnoNemico() {
        ui.mostraMessaggio("E' il turno di " + nemico.getNome() + "...");
        nemico.eseguiAzioneDiTurno(giocatore.getPersonaggio());
    }

    private void aggiornaModificatoriFineTurno(PersonaggioGiocabile eroe) {
        eroe.aggiornaTurnoModificatori();
        nemico.aggiornaTurnoModificatori();
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
}
