package it.unicam.cs.mpgc.rpg129696.modelli.modificatori;

import it.unicam.cs.mpgc.rpg129696.modelli.personaggio.PersonaggioBase;
import it.unicam.cs.mpgc.rpg129696.modelli.personaggio.Statistiche;

public class ModificatoreCura implements Modificatore{
    private final int valoreCura;

    public ModificatoreCura(int valoreCura) {
        if (valoreCura <= 0) throw new IllegalArgumentException("la cura deve essere >0");
        this.valoreCura = valoreCura;
    }
    @Override
    public void applicaSuPersonaggio(PersonaggioBase personaggio) {
        personaggio.cura(valoreCura);
    }
}
