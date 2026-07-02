package it.unicam.cs.mpgc.rpg129696.controlli;

public class ComportamentoAggressivo implements ComportamentoNemico {

    @Override
    public void eseguiTurno(Personaggio nemico, Personaggio bersaglio) {
        nemico.attacca(bersaglio);
    }
}
