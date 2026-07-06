package it.unicam.cs.mpgc.rpg129696.modelli.personaggio;

import it.unicam.cs.mpgc.rpg129696.controlli.ComportamentoNemico;
import it.unicam.cs.mpgc.rpg129696.modelli.enumerati.TipoComportamento;

public class Nemico extends PersonaggioBase {

    private final int ricompensaEsperienza;
    private final ComportamentoNemico comportamento;
    private final TipoComportamento tipoComportamento;

    public Nemico(String nome, Statistiche statisticheBase, int ricompensaEsperienza,
                  ComportamentoNemico comportamentoNemico, TipoComportamento tipoComportamento) {
        super(nome, statisticheBase);
        this.ricompensaEsperienza = ricompensaEsperienza;
        this.comportamento = comportamentoNemico;
        this.tipoComportamento = tipoComportamento;
    }

    public void eseguiAzioneDiTurno(PersonaggioBase bersaglio) {
        comportamento.eseguiTurno(this, bersaglio);
    }

    public int getRicompensaEsperienza() {
        return ricompensaEsperienza;
    }

    public  ComportamentoNemico getComportamentoNemico() {
        return comportamento;
    }

    public TipoComportamento getTipoComportamento() {
        return tipoComportamento;
    }
}
