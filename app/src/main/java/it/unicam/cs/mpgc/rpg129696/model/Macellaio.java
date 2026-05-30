package it.unicam.cs.mpgc.rpg129696.model;
import it.unicam.cs.mpgc.rpg129696.model.abilita.Furia;

public class Macellaio extends PersonaggioGiocabile{

    public Macellaio(String nome) {

        super(nome, 120, 120, 28, 8, new Furia());
    }
}
