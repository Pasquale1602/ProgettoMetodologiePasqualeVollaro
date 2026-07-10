package it.unicam.cs.mpgc.rpg129696.modelli.modificatori;

import it.unicam.cs.mpgc.rpg129696.modelli.personaggio.PersonaggioBase;

/**
 * Rappresenta un modificatore che applica danno da veleno a un personaggio.
 */
public class ModificatoreVeleno implements Modificatore {

    private final int dannoVeleno;

    /**
     * Crea un modificatore veleno con un determinato valore di danno.
     *
     * @param dannoVeleno quantità di danno inflitta dal veleno
     * @throws IllegalArgumentException se il danno è minore o uguale a zero
     */
    public ModificatoreVeleno (int dannoVeleno) {
        if (dannoVeleno <= 0) throw new IllegalArgumentException("il danno da veleno è <= 0");
        this.dannoVeleno = dannoVeleno;
    }

    @Override
    public void applicaSuPersonaggio (PersonaggioBase personaggio) {
        personaggio.prendiDanno(dannoVeleno);
    }
}
