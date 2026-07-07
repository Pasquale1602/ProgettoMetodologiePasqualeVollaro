package it.unicam.cs.mpgc.rpg129696.view;

import it.unicam.cs.mpgc.rpg129696.controlli.CombatManager;
import it.unicam.cs.mpgc.rpg129696.controlli.ConfigurazioneGioco;
import it.unicam.cs.mpgc.rpg129696.controlli.StatoCombattimento;
import it.unicam.cs.mpgc.rpg129696.modelli.enumerati.SlotSalvataggio;
import it.unicam.cs.mpgc.rpg129696.modelli.oggetti.Contenuto;
import it.unicam.cs.mpgc.rpg129696.modelli.partita.Partita;
import it.unicam.cs.mpgc.rpg129696.persistenza.GestoreSalvataggi;
import it.unicam.cs.mpgc.rpg129696.controlli.GestoreIncontri;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

import java.util.List;

/**
 * Controller FXML per la schermata di combattimento.
 *
 * Viene istanziato automaticamente da JavaFX quando viene caricato combattimento.fxml.
 * Le dipendenze vengono iniettate tramite {@link #inizializza}.
 */
public class CombattimentoController {


    @FXML private Label labelTurno;
    @FXML private Label labelEroe;
    @FXML private Label labelNemico;
    @FXML private TextArea logCombattimento;
    @FXML private VBox pannelloInventario;
    @FXML private Button attaccoBtn;
    @FXML private Button abilitaBtn;
    @FXML private Button inventarioBtn;
    @FXML private Button salvaBtn;
    @FXML private Button continuaBtn;

    private GestoreSchermate gestoreSchermate;
    private Partita partita;
    private ConfigurazioneGioco configurazione;
    private GestoreSalvataggi gestoreSalvataggi;
    private GestoreIncontri gestoreIncontri;

    private CombatManager combatManager;
    private boolean inventarioVisibile = false;

    /**
     * Inietta le dipendenze e avvia il combattimento.
     * Deve essere chiamato subito dopo il caricamento dell'FXML.
     */
    public void inizializza(GestoreSchermate gestoreSchermate,
                            Partita partita,
                            ConfigurazioneGioco configurazione,
                            GestoreSalvataggi gestoreSalvataggi,
                            GestoreIncontri gestoreIncontri) {
        this.gestoreSchermate = gestoreSchermate;
        this.partita = partita;
        this.configurazione = configurazione;
        this.gestoreSalvataggi = gestoreSalvataggi;
        this.gestoreIncontri = gestoreIncontri;

        InterfacciaUtente uiGrafica = new GuiInterfacciaUtente(this::aggiungiMessaggioLog);
        combatManager = new CombatManager(
                partita,
                uiGrafica,
                configurazione.getOggetti());

        combatManager.avviaScontro();
        aggiornaStatoCombattimento();
    }

    @FXML
    private void onAttacco() {
        combatManager.eseguiAttaccoBase();
        dopoAzione();
    }

    @FXML
    private void onAbilita() {
        combatManager.eseguiAbilita();
        dopoAzione();
    }

    @FXML
    private void onInventario() {
        toggleInventario();
    }

    @FXML
    private void onSalva() {
        gestoreSalvataggi.salva(partita, SlotSalvataggio.SLOT_1);
        aggiungiMessaggioLog("Partita salvata.");
    }

    @FXML
    private void onContinua() {
        StatoCombattimento stato = combatManager.getStatoCombattimento();
        if (stato.getHpEroe() <= 0) {
            gestoreSchermate.mostraMenuPrincipale();
        } else {
            assegnaNuovoNemico();
            gestoreSchermate.mostraCombattimento(partita);
        }
    }

    @FXML
    private void onMenu() {
        gestoreSchermate.mostraMenuPrincipale();
    }

    private void dopoAzione() {
        aggiornaStatoCombattimento();
        if (inventarioVisibile) aggiornaPannelloInventario();
        gestisciFineScontroSeNecessario();
    }

    private void aggiornaStatoCombattimento() {
        StatoCombattimento stato = combatManager.getStatoCombattimento();
        labelTurno.setText("Turno " + stato.getNumeroTurno());
        labelEroe.setText(stato.getNomeEroe() + " — HP: "
                + stato.getHpEroe() + "/" + stato.getHpMassimiEroe());
        labelNemico.setText(stato.getNomeNemico() + " — HP: "
                + stato.getHpNemico() + "/" + stato.getHpMassimiNemico());
    }

    private void aggiungiMessaggioLog(String messaggio) {
        if (logCombattimento != null) {
            logCombattimento.appendText(messaggio + "\n");
        }
    }

    private void gestisciFineScontroSeNecessario() {
        StatoCombattimento stato = combatManager.getStatoCombattimento();
        if (stato.isCombattimentoInCorso()) return;

        attaccoBtn.setDisable(true);
        abilitaBtn.setDisable(true);
        inventarioBtn.setDisable(true);
        salvaBtn.setDisable(true);
        continuaBtn.setDisable(false);

        if (stato.getHpEroe() <= 0) {
            continuaBtn.setText("Torna al Menu");
        } else {
            continuaBtn.setText("Prossimo Nemico");
        }
    }

    private void toggleInventario() {
        inventarioVisibile = !inventarioVisibile;
        pannelloInventario.setVisible(inventarioVisibile);
        pannelloInventario.setManaged(inventarioVisibile);
        if (inventarioVisibile) aggiornaPannelloInventario();
    }

    private void aggiornaPannelloInventario() {
        pannelloInventario.getChildren().clear();

        Label titolo = new Label("Inventario");
        titolo.setStyle("-fx-font-weight: bold; -fx-font-size: 16px;");
        pannelloInventario.getChildren().add(titolo);

        List<Contenuto> contenuto = combatManager.getGiocatore()
                .getPersonaggio()
                .getInventario()
                .getContenuto();

        if (contenuto.isEmpty()) {
            pannelloInventario.getChildren().add(new Label("Vuoto"));
        } else {
            for (int i = 0; i < contenuto.size(); i++) {
                Contenuto c = contenuto.get(i);
                int indice = i;
                Button usaBtn = new Button(
                        "Usa: " + c.getOggetto().getNome() + " (x" + c.getQuantita() + ")");
                usaBtn.setMaxWidth(Double.MAX_VALUE);
                usaBtn.setWrapText(true);
                usaBtn.setOnAction(e -> {
                    combatManager.usaOggetto(indice);
                    dopoAzione();
                });
                pannelloInventario.getChildren().add(usaBtn);
            }
        }

        Button chiudiBtn = new Button("Chiudi");
        chiudiBtn.setMaxWidth(Double.MAX_VALUE);
        chiudiBtn.setOnAction(e -> toggleInventario());
        pannelloInventario.getChildren().add(chiudiBtn);
    }

        private void assegnaNuovoNemico() {
            gestoreIncontri.assegnaNemicoCasuale(partita, configurazione.getNemici());
        }
}
