package it.unicam.cs.mpgc.rpg129696.modelli.modificatori;

import it.unicam.cs.mpgc.rpg129696.modelli.personaggio.Statistiche;

/**
 * Rappresenta un modificatore che aumenta o riduce il valore della difesa
 * di un personaggio.
 */
public class ModificatoreDifesa implements Modificatore {

    private final int valore;

    /**
     * Crea un modificatore di difesa.
     *
     * @param valore variazione applicata alla difesa. Può essere positiva o negativa.
     */
    public ModificatoreDifesa(int valore) {
        this.valore = valore;
    }

    @Override
    public Statistiche applica (Statistiche statistiche) {

        int nuovaDifesa = statistiche.getDifesa() + valore;
        return statistiche.conDifesa(nuovaDifesa);
    }
}
