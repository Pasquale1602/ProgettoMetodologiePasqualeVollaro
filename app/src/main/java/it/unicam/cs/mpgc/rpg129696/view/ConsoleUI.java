package it.unicam.cs.mpgc.rpg129696.view;

import it.unicam.cs.mpgc.rpg129696.modelli.oggetti.Contenuto;
import java.util.List;
import java.util.Scanner;

public class ConsoleUI implements InterfacciaUtente {

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void mostraMessaggio(String messaggio) {
        System.out.println(messaggio);
    }

    @Override
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

    @Override
    public int richiediSceltaOggetto(List<Contenuto> inventario) {
        System.out.println("\n--- INVENTARIO ---");
        for (int i = 0; i < inventario.size(); i++) {
            Contenuto c = inventario.get(i);
            System.out.println((i + 1) + ". " + c.getOggetto().getNome() + " (x" + c.getQuantita() + ")");
        }
        System.out.println("0. Torna indietro");
        System.out.print("> ");

        try {
            int scelta = Integer.parseInt(scanner.nextLine());
            // Restituiamo indice 0-based, quindi sottraiamo 1 se l'utente sceglie un oggetto
            if (scelta == 0) return -1;
            return scelta - 1;
        } catch (NumberFormatException e) {
            return -1; // Torna indietro in caso di errore
        }
    }
}