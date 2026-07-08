package it.unicam.cs.mpgc.rpg129696.view;

import it.unicam.cs.mpgc.rpg129696.controlli.CombatManager;
import it.unicam.cs.mpgc.rpg129696.controlli.ConfigurazioneGioco;
import it.unicam.cs.mpgc.rpg129696.controlli.GestorePartita;
import it.unicam.cs.mpgc.rpg129696.controlli.StatoCombattimento;
import it.unicam.cs.mpgc.rpg129696.modelli.enumerati.SlotSalvataggio;
import it.unicam.cs.mpgc.rpg129696.modelli.oggetti.Contenuto;
import it.unicam.cs.mpgc.rpg129696.modelli.partita.Partita;
import it.unicam.cs.mpgc.rpg129696.modelli.personaggio.PersonaggioGiocabile;
import it.unicam.cs.mpgc.rpg129696.persistenza.GestoreSalvataggi;
import it.unicam.cs.mpgc.rpg129696.controlli.GestoreIncontri;

import java.util.List;
import java.util.Scanner;

/**
 * Client testuale del gioco.
 *
 * Orchestra {@link CombatManager} esattamente come fa {@code CombattimentoController}
 * per la GUI, ma leggendo le scelte dell'utente da console tramite {@link ConsoleUI}
 * invece che da bottoni. Dimostra che il motore di gioco (package {@code controlli})
 * è indipendente dal tipo di interfaccia utilizzata.
 */
public class GiocoConsole {


    private final ConsoleUI ui = new ConsoleUI();
    private final Scanner scanner = new Scanner(System.in);
    private final ConfigurazioneGioco configurazione = new ConfigurazioneGioco();
    private final GestorePartita gestorePartita = new GestorePartita();
    private final GestoreSalvataggi gestoreSalvataggi = new GestoreSalvataggi();
    private final GestoreIncontri gestoreIncontri = new GestoreIncontri();

    public void avvia() {
        ui.mostraMessaggio("=== TOXIC RAIN (console) ===");
        Partita partita = creaOCaricaPartita();
        if (partita == null) {
            ui.mostraMessaggio("Uscita dal gioco.");
            return;
        }

        if (partita.getNemicoCorrente() == null) {
            assegnaNuovoNemico(partita);
        }

        boolean continuaGioco = true;
        while (continuaGioco) {
            continuaGioco = eseguiCombattimento(partita);
        }

        ui.mostraMessaggio("Grazie per aver giocato!");
    }

    private Partita creaOCaricaPartita() {
        ui.mostraMessaggio("1. Nuova partita");
        ui.mostraMessaggio("2. Carica partita");
        ui.mostraMessaggio("3. Esci");
        ui.mostraMessaggio("> ");

        String scelta = scanner.nextLine();

        return switch (scelta) {
            case "1" -> creaNuovaPartita();
            case "2" -> caricaPartita();
            default -> null;
        };
    }

    private Partita creaNuovaPartita() {
        ui.mostraMessaggio("Inserisci il tuo nome:");
        String nome = scanner.nextLine();

        List<PersonaggioGiocabile> personaggi = configurazione.getPersonaggi();
        ui.mostraMessaggio("Scegli il tuo personaggio:");
        for (PersonaggioGiocabile personaggio : personaggi) {
            ui.mostraMessaggio("- " + personaggio.getNome());
        }
        String nomePersonaggio = scanner.nextLine();

        try {
            return gestorePartita.creaNuovaPartita(nome, nomePersonaggio, personaggi);
        } catch (IllegalArgumentException ex) {
            ui.mostraMessaggio("Errore: " + ex.getMessage());
            return creaNuovaPartita();
        }
    }

    private Partita caricaPartita() {
        try {
            return gestoreSalvataggi.caricaPartita(
                    SlotSalvataggio.SLOT_1,
                    configurazione.getPersonaggi(),
                    configurazione.getOggetti(),
                    configurazione.getTuttiNemici());
        } catch (RuntimeException ex) {
            ui.mostraMessaggio("Impossibile caricare la partita: " + ex.getMessage());
            return null;
        }
    }

    /**
     * Esegue un intero combattimento contro il nemico corrente della partita.
     *
     * @return {@code true} se la partita deve proseguire con un nuovo nemico,
     *         {@code false} se il gioco deve terminare (sconfitta o uscita)
     */
    private boolean eseguiCombattimento(Partita partita) {
        CombatManager combatManager = new CombatManager(
                partita,
                ui,
                configurazione.getOggetti());

        combatManager.avviaScontro();

        while (combatManager.isCombattimentoInCorso()) {
            eseguiAzioneUtente(combatManager);
        }

        StatoCombattimento stato = combatManager.getStatoCombattimento();
        if (stato.getHpEroe() <= 0) {
            return false;
        }

        return chiediSeContinuare(partita);
    }

    private void eseguiAzioneUtente(CombatManager combatManager) {
        int scelta = ui.richiediSceltaAzionePrincipale();

        switch (scelta) {
            case 1 -> combatManager.eseguiAttaccoBase();
            case 2 -> combatManager.eseguiAbilita();
            case 3 -> usaOggettoDaInventario(combatManager);
            default -> ui.mostraMessaggio("Scelta non valida.");
        }
    }

    private void usaOggettoDaInventario(CombatManager combatManager) {
        List<Contenuto> inventario = combatManager.getGiocatore()
                .getPersonaggio().getInventario().getContenuto();
        int indice = ui.richiediSceltaOggetto(inventario);
        if (indice >= 0) {
            combatManager.usaOggetto(indice);
        }
    }

    private boolean chiediSeContinuare(Partita partita) {
        ui.mostraMessaggio("1. Prossimo nemico");
        ui.mostraMessaggio("2. Salva ed esci");
        ui.mostraMessaggio("> ");

        String scelta = scanner.nextLine();
        if ("2".equals(scelta)) {
            gestoreSalvataggi.salva(partita, SlotSalvataggio.SLOT_1);
            ui.mostraMessaggio("Partita salvata.");
            return false;
        }

        assegnaNuovoNemico(partita);
        return true;
    }

    private void assegnaNuovoNemico(Partita partita) {
        gestoreIncontri.assegnaNemicoCasuale(partita, configurazione.getNemici());
    }
}
