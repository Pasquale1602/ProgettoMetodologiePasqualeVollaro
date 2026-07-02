package it.unicam.cs.mpgc.rpg129696.modelli.modificatori;

import it.unicam.cs.mpgc.rpg129696.modelli.personaggio.Statistica;

public class ModificatoreDifesa implements Modificatore {

    private final int valore;

    public ModificatoreDifesa(int valore) {
        this.valore = valore;
    }
    public void applica (Statistica statistica) {
        statistica.setDifesa(statistica.getDifesa() + valore);
    }
}
