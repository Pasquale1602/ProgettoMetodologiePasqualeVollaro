package it.unicam.cs.mpgc.rpg129696.modelli.personaggio;

import it.unicam.cs.mpgc.rpg129696.controlli.ComportamentoNemico;

public class Nemico extends PersonaggioBase {

    private final int ricompensaEsperienza;
    private final ComportamentoNemico comportamento;

    protected Nemico() {
        super("Sconosciuto", new Statistica(1,1,1));
        this.ricompensaEsperienza = 1;
        this.comportamento = null;
    }


    public Nemico (String nome, Statistica statisticaBase, int ricompensaEsperienza,
                   ComportamentoNemico comportamentoNemico) {
        super (nome, statisticaBase);
        this.ricompensaEsperienza = ricompensaEsperienza;
        this.comportamento = comportamentoNemico;
    }


    public void eseguiTurno(PersonaggioBase bersaglio) {
        comportamento.eseguiTurno(this, bersaglio);
    }


    public int getRicompensaEsperienza() {
        return ricompensaEsperienza;
    }
}
