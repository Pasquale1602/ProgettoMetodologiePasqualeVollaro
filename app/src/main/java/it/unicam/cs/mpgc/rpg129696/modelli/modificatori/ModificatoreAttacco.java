package it.unicam.cs.mpgc.rpg129696.modelli.modificatori;

import it.unicam.cs.mpgc.rpg129696.modelli.personaggio.Statistiche;

public class ModificatoreAttacco implements Modificatore {
    private final int valore;

    public ModificatoreAttacco(int valore) {
        this.valore = valore;
    }
    public void applica(Statistiche statistiche) {
        statistiche.setAttacco(statistiche.getAttacco() + valore);
    }
}
