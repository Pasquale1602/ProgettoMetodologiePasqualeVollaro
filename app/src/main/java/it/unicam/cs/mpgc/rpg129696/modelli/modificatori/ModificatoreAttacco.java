package it.unicam.cs.mpgc.rpg129696.modelli.modificatori;

import it.unicam.cs.mpgc.rpg129696.modelli.personaggio.Statistica;

public class ModificatoreAttacco implements Modificatore {
    private final int valore;

    public ModificatoreAttacco(int valore) {
        this.valore = valore;
    }
    public void applica(Statistica statistica) {
        statistica.setAttacco(statistica.getAttacco() + valore);
    }
}
