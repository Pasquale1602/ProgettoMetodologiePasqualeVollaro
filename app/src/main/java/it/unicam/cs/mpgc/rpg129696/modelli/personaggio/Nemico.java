package it.unicam.cs.mpgc.rpg129696.modelli.personaggio;

import it.unicam.cs.mpgc.rpg129696.modelli.comportamenti.ComportamentoNemico;
import it.unicam.cs.mpgc.rpg129696.modelli.enumerati.TipoComportamento;

/**
 * Rappresenta un nemico affrontabile durante il combattimento.
 *
 * Ogni nemico possiede statistiche, una ricompensa in esperienza
 * e un comportamento che determina le azioni durante il turno.
 */
public class Nemico extends PersonaggioBase {

    private final int ricompensaEsperienza;
    private final ComportamentoNemico comportamento;
    private final TipoComportamento tipoComportamento;

    /**
     * Crea un nuovo nemico.
     *
     * @param nome nome del nemico
     * @param statisticheBase statistiche iniziali del nemico
     * @param ricompensaEsperienza esperienza assegnata alla sconfitta del nemico
     * @param comportamentoNemico comportamento utilizzato durante il combattimento
     * @param tipoComportamento tipo di comportamento del nemico
     */
    public Nemico(String nome, Statistiche statisticheBase, int ricompensaEsperienza,
                  ComportamentoNemico comportamentoNemico, TipoComportamento tipoComportamento) {
        super(nome, statisticheBase);
        this.ricompensaEsperienza = ricompensaEsperienza;
        this.comportamento = comportamentoNemico;
        this.tipoComportamento = tipoComportamento;
    }
    /**
     * Esegue l'azione del nemico durante il proprio turno di combattimento.
     *
     * @param bersaglio personaggio contro cui eseguire l'azione
     */
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
