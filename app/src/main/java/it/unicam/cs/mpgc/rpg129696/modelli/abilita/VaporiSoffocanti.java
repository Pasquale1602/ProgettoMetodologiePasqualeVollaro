package it.unicam.cs.mpgc.rpg129696.modelli.abilita;

import it.unicam.cs.mpgc.rpg129696.modelli.personaggio.PersonaggioBase;

public class VaporiSoffocanti implements Abilita{

    @Override
    public void eseguiAbilita(PersonaggioBase utilizzatore, PersonaggioBase bersaglio) {

        new VelenoProgressivo(4, 8).applica(bersaglio);
    }
}

