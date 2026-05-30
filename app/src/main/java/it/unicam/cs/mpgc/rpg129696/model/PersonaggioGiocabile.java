package it.unicam.cs.mpgc.rpg129696.model;
import it.unicam.cs.mpgc.rpg129696.model.abilita.Abilita;

public abstract class PersonaggioGiocabile extends Personaggio {

    private Abilita abilita;

    public PersonaggioGiocabile(String nome, int hp, int maxHp, int attacco, int difesa, Abilita abilita) {
        super(nome, hp, maxHp, attacco, difesa);
        this.abilita = abilita;
    }

    public Abilita getAbilita() {
        return abilita;
    }

    public void usaAbilita(Personaggio bersaglio) {
        abilita.eseguiAbilita(bersaglio);
    }
}
