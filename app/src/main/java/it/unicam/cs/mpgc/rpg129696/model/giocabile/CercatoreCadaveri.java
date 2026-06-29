package it.unicam.cs.mpgc.rpg129696.model.giocabile;
import it.unicam.cs.mpgc.rpg129696.model.PersonaggioGiocabile;
import it.unicam.cs.mpgc.rpg129696.model.abilita.ColpoAlleSpalle;

public class CercatoreCadaveri extends PersonaggioGiocabile {

    public CercatoreCadaveri(String nome) {

        super(nome, 65, 65, 30, 5, new ColpoAlleSpalle());
    }

}
