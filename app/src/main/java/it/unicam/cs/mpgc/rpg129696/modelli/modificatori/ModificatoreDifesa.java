package it.unicam.cs.mpgc.rpg129696.modelli.modificatori;

import it.unicam.cs.mpgc.rpg129696.modelli.personaggio.Statistiche;

public class ModificatoreDifesa implements Modificatore {

    private final int valore;

    public ModificatoreDifesa(int valore) {
        this.valore = valore;
    }

    @Override
    public Statistiche applica (Statistiche statistiche) {

        int nuovaDifesa = statistiche.getDifesa() + valore;
        return statistiche.conDifesa(nuovaDifesa);
    }
}
