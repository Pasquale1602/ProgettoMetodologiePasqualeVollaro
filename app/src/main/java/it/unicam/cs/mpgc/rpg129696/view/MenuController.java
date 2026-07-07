package it.unicam.cs.mpgc.rpg129696.view;

import it.unicam.cs.mpgc.rpg129696.controlli.ConfigurazioneGioco;
import it.unicam.cs.mpgc.rpg129696.controlli.GestorePartita;
import it.unicam.cs.mpgc.rpg129696.modelli.enumerati.SlotSalvataggio;
import it.unicam.cs.mpgc.rpg129696.modelli.partita.Partita;
import it.unicam.cs.mpgc.rpg129696.modelli.personaggio.Nemico;
import it.unicam.cs.mpgc.rpg129696.modelli.personaggio.PersonaggioGiocabile;
import it.unicam.cs.mpgc.rpg129696.persistenza.GestoreSalvataggi;
import it.unicam.cs.mpgc.rpg129696.persistenza.NemicoLoader;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.TextInputDialog;

import java.util.List;
import java.util.Optional;
import java.util.Random;

/**
 * Controller FXML per la schermata del menu principale.
 *
 * Viene istanziato automaticamente da JavaFX quando viene caricato menu.fxml.
 * Le dipendenze vengono iniettate tramite {@link #inizializza}.
 */
public class MenuController {

    private static final Random RANDOM = new Random();

    private GestoreSchermate gestoreSchermate;
    private ConfigurazioneGioco configurazione;
    private GestorePartita gestorePartita;
    private GestoreSalvataggi gestoreSalvataggi;
    private final NemicoLoader nemicoLoader = new NemicoLoader();

    /**
     * Inietta le dipendenze necessarie al controller.
     * Deve essere chiamato subito dopo il caricamento dell'FXML.
     */
    public void inizializza(GestoreSchermate gestoreSchermate,
                            ConfigurazioneGioco configurazione,
                            GestorePartita gestorePartita,
                            GestoreSalvataggi gestoreSalvataggi) {
        this.gestoreSchermate = gestoreSchermate;
        this.configurazione = configurazione;
        this.gestorePartita = gestorePartita;
        this.gestoreSalvataggi = gestoreSalvataggi;
    }

    @FXML
    private void onNuovaPartita() {
        TextInputDialog dialogNome = new TextInputDialog("Giocatore");
        dialogNome.setTitle("Nuova Partita");
        dialogNome.setHeaderText(null);
        dialogNome.setContentText("Inserisci il tuo nome:");
        Optional<String> nome = dialogNome.showAndWait();
        if (nome.isEmpty() || nome.get().isBlank()) return;

        List<PersonaggioGiocabile> personaggi = configurazione.getPersonaggi();
        List<String> nomiPersonaggi = personaggi.stream()
                .map(PersonaggioGiocabile::getNome).toList();

        ChoiceDialog<String> dialogPersonaggio = new ChoiceDialog<>(
                nomiPersonaggi.get(0), nomiPersonaggi);
        dialogPersonaggio.setTitle("Nuova Partita");
        dialogPersonaggio.setHeaderText(null);
        dialogPersonaggio.setContentText("Scegli il tuo personaggio:");
        Optional<String> personaggioScelto = dialogPersonaggio.showAndWait();
        if (personaggioScelto.isEmpty()) return;

        try {
            Partita partita = gestorePartita.creaNuovaPartita(
                    nome.get(), personaggioScelto.get(), personaggi);
            assegnaNuovoNemico(partita);
            gestoreSchermate.mostraCombattimento(partita);
        } catch (IllegalArgumentException ex) {
            mostraErrore(ex.getMessage());
        }
    }

    @FXML
    private void onCaricaPartita() {
        try {
            Partita partita = gestoreSalvataggi.caricaPartita(
                    SlotSalvataggio.SLOT_1,
                    configurazione.getPersonaggi(),
                    configurazione.getOggetti());
            assegnaNuovoNemico(partita);
            gestoreSchermate.mostraCombattimento(partita);
        } catch (RuntimeException ex) {
            mostraErrore("Impossibile caricare la partita: " + ex.getMessage());
        }
    }

    @FXML
    private void onEsci() {
        System.exit(0);
    }

    private void assegnaNuovoNemico(Partita partita) {
        List<Nemico> nemici = nemicoLoader.caricaNemici();
        Nemico scelto = nemici.get(RANDOM.nextInt(nemici.size()));
        partita.setNemicoCorrente(scelto);
    }

    private void mostraErrore(String messaggio) {
        Alert alert = new Alert(Alert.AlertType.ERROR, messaggio);
        alert.setHeaderText(null);
        alert.showAndWait();
    }
}
