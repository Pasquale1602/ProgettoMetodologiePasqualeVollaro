package it.unicam.cs.mpgc.rpg129696.model.giocabile;
import it.unicam.cs.mpgc.rpg129696.model.PersonaggioGiocabile;
import it.unicam.cs.mpgc.rpg129696.model.abilita.VaporiSoffocanti;

public class Contaminatore extends PersonaggioGiocabile {

    public Contaminatore(String nome) {

        super(nome, 70, 70, 25, 6, new VaporiSoffocanti());
    }
}
