package it.unicam.cs.mpgc.rpg129696.modelli.abilita;
import it.unicam.cs.mpgc.rpg129696.modelli.effetti.BuffDifesa;
import it.unicam.cs.mpgc.rpg129696.modelli.effetti.Cura;
import it.unicam.cs.mpgc.rpg129696.modelli.personaggio.PersonaggioBase;

public class ChiamataAlleArmi implements Abilita {

    @Override
    public void eseguiAbilita(PersonaggioBase utilizzatore, PersonaggioBase bersaglio) {
        new Cura(30).applica(utilizzatore);
        new BuffDifesa(3, 10).applica(utilizzatore);
    }
}
