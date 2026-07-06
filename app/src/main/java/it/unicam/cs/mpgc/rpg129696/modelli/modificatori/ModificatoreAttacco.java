package it.unicam.cs.mpgc.rpg129696.modelli.modificatori;

import it.unicam.cs.mpgc.rpg129696.modelli.personaggio.Statistiche;

public class ModificatoreAttacco implements Modificatore {
    private final int valore;

    public ModificatoreAttacco(int valore) {
        this.valore = valore;
    }

    @Override
    public Statistiche applica(Statistiche statistiche) {
        return statistiche.conAttacco(statistiche.getAttacco() + valore);
    }
}
