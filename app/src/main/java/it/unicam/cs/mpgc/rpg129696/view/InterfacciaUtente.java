package it.unicam.cs.mpgc.rpg129696.view;

import it.unicam.cs.mpgc.rpg129696.modelli.oggetti.Contenuto;
import java.util.List;

public interface InterfacciaUtente {
    void mostraMessaggio(String messaggio);
    int richiediSceltaAzionePrincipale(); // 1: Attacca, 2: Abilità, 3: Inventario
    int richiediSceltaOggetto(List<Contenuto> inventario);
}
