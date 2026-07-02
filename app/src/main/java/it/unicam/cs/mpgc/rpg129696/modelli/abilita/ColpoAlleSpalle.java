package it.unicam.cs.mpgc.rpg129696.modelli.abilita;

import it.unicam.cs.mpgc.rpg129696.modelli.personaggio.PersonaggioBase;

public class ColpoAlleSpalle implements Abilita{

    @Override
    public void eseguiAbilita(@org.jetbrains.annotations.UnknownNullability PersonaggioBase utilizzatore, @org.jetbrains.annotations.UnknownNullability PersonaggioBase bersaglio) {
        bersaglio.subisciDanno(utilizzatore.getAttacco()*2);
    }
}

