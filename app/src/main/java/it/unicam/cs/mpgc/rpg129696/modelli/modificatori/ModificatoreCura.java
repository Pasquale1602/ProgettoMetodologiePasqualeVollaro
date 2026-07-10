package it.unicam.cs.mpgc.rpg129696.modelli.modificatori;

import it.unicam.cs.mpgc.rpg129696.modelli.personaggio.PersonaggioBase;

/**
 * Rappresenta un modificatore che applica una cura a un personaggio.
 */
public class ModificatoreCura implements Modificatore{
    private final int valoreCura;

    /**
     * Crea un modificatore di cura con un determinato valore.
     *
     * @param valoreCura quantità di punti vita ripristinati
     * @throws IllegalArgumentException se il valore della cura è minore o uguale a zero
     */
    public ModificatoreCura(int valoreCura) {
        if (valoreCura <= 0) throw new IllegalArgumentException("la cura deve essere >0");
        this.valoreCura = valoreCura;
    }
    @Override
    public void applicaSuPersonaggio(PersonaggioBase personaggio) {
        personaggio.cura(valoreCura);
    }
}
