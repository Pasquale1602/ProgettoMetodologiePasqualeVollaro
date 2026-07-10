package it.unicam.cs.mpgc.rpg129696.view;

import it.unicam.cs.mpgc.rpg129696.modelli.oggetti.Contenuto;
import java.util.List;
import java.util.Scanner;

/**
 * Implementazione testuale di {@link InterfacciaUtente}.
 *
 * Oltre al contratto minimo di {@link InterfacciaUtente} (mostrare messaggi),
 * espone i metodi per richiedere scelte bloccanti da input console, usati da
 * {@link GiocoConsole} per pilotare il {@code CombatManager}.
 */
public class ConsoleUI implements InterfacciaUtente {

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void mostraMessaggio(String messaggio) {
        System.out.println(messaggio);
    }

    /**
     * Richiede all'utente la scelta dell'azione principale da eseguire
     * tramite input da console.
     *
     * @return il valore scelto dall'utente:
     *         1 per attacco base, 2 per abilità speciale, 3 per inventario
     */
    public int richiediSceltaAzionePrincipale() {
        int scelta = -1;
        while (scelta < 1 || scelta > 3) {
            System.out.println("\nCosa vuoi fare?");
            System.out.println("1. Attacco Base");
            System.out.println("2. Abilità Speciale");
            System.out.println("3. Inventario");
            System.out.print("> ");

            try {
                scelta = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Per favore, inserisci un numero valido.");
            }
        }
        return scelta;
    }

    /**
     * Richiede all'utente quale oggetto utilizzare dall'inventario.
     *
     * @param inventario lista degli oggetti disponibili
     * @return l'indice dell'oggetto scelto nell'inventario,
     *         oppure -1 se l'inventario è vuoto, la scelta non è valida
     *         o l'utente decide di tornare indietro
     */
    public int richiediSceltaOggetto(List<Contenuto> inventario) {
        if (inventario.isEmpty()) {
            System.out.println("Inventario vuoto.");
            return -1;
        }

        System.out.println("\n--- INVENTARIO ---");
        for (int i = 0; i < inventario.size(); i++) {
            Contenuto c = inventario.get(i);
            System.out.println((i + 1) + ". " + c.getOggetto().getNome() + " (x" + c.getQuantita() + ")");
        }
        System.out.println("0. Torna indietro");
        System.out.print("> ");

        try {
            int scelta = Integer.parseInt(scanner.nextLine());
            if (scelta == 0) return -1;
            return scelta - 1;
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}