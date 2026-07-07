package it.unicam.cs.mpgc.rpg129696;

import it.unicam.cs.mpgc.rpg129696.view.GestoreSchermate;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Punto di ingresso dell'applicazione.
 *
 * Si limita ad avviare JavaFX e delegare la gestione di tutte le
 * schermate a {@link GestoreSchermate}.
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        new GestoreSchermate(stage).avviaGioco();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
