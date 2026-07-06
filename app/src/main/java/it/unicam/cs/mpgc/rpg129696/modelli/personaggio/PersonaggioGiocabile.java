package it.unicam.cs.mpgc.rpg129696.modelli.personaggio;

import it.unicam.cs.mpgc.rpg129696.modelli.abilita.Abilita;

public class PersonaggioGiocabile extends PersonaggioBase{

    private final Abilita abilita;


    public PersonaggioGiocabile(String nome, Statistiche statisticheBase, Abilita abilita) {
        super(nome, statisticheBase);
        this.abilita = abilita;
    }


    public void usaAbilita(PersonaggioBase bersaglio) {
        abilita.eseguiAbilita(this, bersaglio);
    }


    public Abilita getAbilita() {
        return abilita;
    }
}
