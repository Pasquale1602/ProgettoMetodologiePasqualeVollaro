package it.unicam.cs.mpgc.rpg129696.modelli.personaggio;

import it.unicam.cs.mpgc.rpg129696.modelli.abilita.Abilita;

public abstract class PersonaggioGiocabile extends PersonaggioBase{

    private Abilita abilita;


    protected PersonaggioGiocabile(String nome, Statistiche statisticheBase, Abilita abilita) {
        super(nome, statisticheBase);
    }


    public void usaAbilita(PersonaggioBase bersaglio) {
        abilita.eseguiAbilita(this, bersaglio);
    }


    public Abilita getAbilita() {
        return abilita;
    }
}
