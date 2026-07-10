package it.unicam.cs.mpgc.rpg129696.modelli.modificatori;

import it.unicam.cs.mpgc.rpg129696.modelli.personaggio.Statistiche;

/**
 * Rappresenta un modificatore che aumenta o riduce il valore dell'attacco
 * nelle statistiche di un personaggio.
 */
public class ModificatoreAttacco implements Modificatore {
    private final int valore;

    /**
     * Crea un modificatore di attacco.
     *
     * @param valore variazione applicata al valore di attacco.
     *               Può essere positiva o negativa.
     */
    public ModificatoreAttacco(int valore) {
        this.valore = valore;
    }

    @Override
    public Statistiche applica(Statistiche statistiche) {
        return statistiche.conAttacco(statistiche.getAttacco() + valore);
    }
}
