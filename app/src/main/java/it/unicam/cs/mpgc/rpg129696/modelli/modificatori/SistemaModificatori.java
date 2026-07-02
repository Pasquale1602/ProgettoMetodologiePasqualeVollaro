package it.unicam.cs.mpgc.rpg129696.modelli.modificatori;

import it.unicam.cs.mpgc.rpg129696.modelli.personaggio.Statistica;

import java.util.List;


/**
 * Utility class responsible for calculating the final stats of a character
 * by applying a list of modifiers to a copy of the base stats.
 * The base stats are never modified.
 */
public class SistemaModificatori {

    /** Prevents instantiation of this utility class. */
    private SistemaModificatori() {}
    /**
     * Calculates the final stats by applying all modifiers sequentially
     * to a copy of the base stats.
     *
     * @param baseStats the character's base stats, never modified
     * @param modifiers the list of active modifiers to apply
     * @return a new Stats instance with all modifiers applied
     */
    public static Statistica calcola (Statistica baseStats, List<Modificatore> modifiers) {

        Statistica finalStats = new Statistica(baseStats);

        for (Modificatore modifier : modifiers) {
            modifier.applica(finalStats);
        }
        return finalStats;
    }
}
