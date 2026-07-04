package it.unicam.cs.mpgc.rpg129696.modelli.modificatori;

import it.unicam.cs.mpgc.rpg129696.modelli.personaggio.Statistiche;

public class ModificatoreDifesa implements Modificatore {

    private final int valore;

    public ModificatoreDifesa(int valore) {
        this.valore = valore;
    }
    public void applica (Statistiche statistiche) {
        statistiche.setDifesa(statistiche.getDifesa() + valore);
    }
}
