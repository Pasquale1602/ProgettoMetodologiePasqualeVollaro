package it.unicam.cs.mpgc.rpg129696.modelli.enumerati;

/**
 * Rappresenta gli slot disponibili per il salvataggio della partita.
 */
public enum SlotSalvataggio {

    SLOT_1("salvataggi/slot1.json");

    private final String percorso;

    SlotSalvataggio(String percorso) {
        this.percorso = percorso;
    }

    public String getPercorso() {
        return percorso;
    }
}