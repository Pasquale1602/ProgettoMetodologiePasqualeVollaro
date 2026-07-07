package it.unicam.cs.mpgc.rpg129696;

import it.unicam.cs.mpgc.rpg129696.view.GiocoConsole;

/**
 * Punto di ingresso alternativo, testuale, dell'applicazione.
 *
 * Dimostra che il motore di gioco (package {@code controlli}), in particolare
 * {@link it.unicam.cs.mpgc.rpg129696.controlli.CombatManager}, e' indipendente
 * dal tipo di interfaccia: qui viene pilotato da input/output di console
 * invece che da JavaFX.
 */
public class AppConsole {

    public static void main(String[] args) {
        new GiocoConsole().avvia();
    }
}
