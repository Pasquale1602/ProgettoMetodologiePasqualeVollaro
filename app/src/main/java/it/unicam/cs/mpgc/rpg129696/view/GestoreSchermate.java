package it.unicam.cs.mpgc.rpg129696.view;

import it.unicam.cs.mpgc.rpg129696.controlli.ConfigurazioneGioco;
import it.unicam.cs.mpgc.rpg129696.controlli.GestorePartita;
import it.unicam.cs.mpgc.rpg129696.modelli.partita.Partita;
import it.unicam.cs.mpgc.rpg129696.persistenza.GestoreSalvataggi;
import it.unicam.cs.mpgc.rpg129696.controlli.GestoreIncontri;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Gestisce la navigazione tra le schermate dell'applicazione.
 *
 * Carica i layout da file FXML e inietta le dipendenze nei controller,
 * sostituendo la radice della scena per passare da una schermata all'altra
 * senza aprire nuove finestre.
 */
public class GestoreSchermate {

    private static final String FXML_MENU = "/resources/menu.fxml";
    private static final String FXML_COMBATTIMENTO = "/resources/combattimento.fxml";

    private final Stage stage;
    private final ConfigurazioneGioco configurazione;
    private final GestorePartita gestorePartita;
    private final GestoreSalvataggi gestoreSalvataggi;

    private final GestoreIncontri gestoreIncontri;

    public GestoreSchermate(Stage stage) {
        this.stage = stage;
        this.configurazione = new ConfigurazioneGioco();
        this.gestorePartita = new GestorePartita();
        this.gestoreSalvataggi = new GestoreSalvataggi();
        this.gestoreIncontri = new GestoreIncontri();
    }

    /**
     * Inizializza il titolo e mostra la schermata del menu principale.
     */
    public void avviaGioco() {
        stage.setTitle("Toxic Rain");
        mostraMenuPrincipale();
        stage.show();
    }

    /**
     * Carica menu.fxml e mostra il menu principale.
     */
    public void mostraMenuPrincipale() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(FXML_MENU));
            Parent root = loader.load();

            MenuController controller = loader.getController();
            controller.inizializza(this, configurazione, gestorePartita, gestoreSalvataggi, gestoreIncontri);

            impostaSchermata(root);
        } catch (IOException e) {
            throw new RuntimeException("Impossibile caricare menu.fxml", e);
        }
    }

    /**
     * Carica combattimento.fxml e mostra la schermata di combattimento.
     *
     * @param partita la partita corrente
     */
    public void mostraCombattimento(Partita partita) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(FXML_COMBATTIMENTO));
            Parent root = loader.load();

            CombattimentoController controller = loader.getController();
            controller.inizializza(this, partita, configurazione, gestoreSalvataggi, gestoreIncontri);

            impostaSchermata(root);
        } catch (IOException e) {
            throw new RuntimeException("Impossibile caricare combattimento.fxml", e);
        }
    }

    private void impostaSchermata(Parent root) {
        if (stage.getScene() == null) {
            Scene scene = new Scene(root, 900, 620);
            scene.getStylesheets().add(getClass().getResource("/resources/stile.css").toExternalForm());
            stage.setScene(scene);
        } else {
            stage.getScene().setRoot(root);
        }
    }
}
