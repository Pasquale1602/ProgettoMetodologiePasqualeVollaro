package it.unicam.cs.mpgc.rpg129696.model;
import it.unicam.cs.mpgc.rpg129696.controller.ComportamentoNemico;

public abstract class Nemico extends Personaggio {

    private ComportamentoNemico comportamentoNemico;

    public Nemico(String nome, int hp, int maxHp, int attacco, int difesa, ComportamentoNemico comportamentoNemico) {

        super(nome, hp, maxHp, attacco, difesa);
        this.comportamentoNemico = comportamentoNemico;
    }
    public ComportamentoNemico getComportamentoNemico() {
        return comportamentoNemico;
    }
    public void eseguiTurno(Personaggio bersaglio) {
        comportamentoNemico.eseguiTurno(this, bersaglio);
    }


}
