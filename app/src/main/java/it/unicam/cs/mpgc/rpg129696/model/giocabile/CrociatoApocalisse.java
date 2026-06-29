package it.unicam.cs.mpgc.rpg129696.model.giocabile;
import it.unicam.cs.mpgc.rpg129696.model.PersonaggioGiocabile;
import it.unicam.cs.mpgc.rpg129696.model.abilita.ChiamataAlleArmi;

public class CrociatoApocalisse extends PersonaggioGiocabile {

    public CrociatoApocalisse(String nome) {

        super(nome, 90, 90, 15, 22, new ChiamataAlleArmi());
    }
}
