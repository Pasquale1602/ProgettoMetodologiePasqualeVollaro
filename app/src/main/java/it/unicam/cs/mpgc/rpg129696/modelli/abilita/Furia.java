package it.unicam.cs.mpgc.rpg129696.modelli.abilita;

import it.unicam.cs.mpgc.rpg129696.modelli.effetti.BuffAttacco;
import it.unicam.cs.mpgc.rpg129696.modelli.effetti.DebuffDifesa;
import it.unicam.cs.mpgc.rpg129696.modelli.personaggio.PersonaggioBase;

public class Furia implements Abilita{

    @Override
    public void eseguiAbilita(PersonaggioBase utilizzatore, PersonaggioBase bersaglio) {
            new BuffAttacco(3,15).applica(utilizzatore);
            new DebuffDifesa(3, 8).applica(utilizzatore);
    }
}

