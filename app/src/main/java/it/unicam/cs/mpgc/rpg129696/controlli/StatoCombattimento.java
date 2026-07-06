package it.unicam.cs.mpgc.rpg129696.controlli;

/**
 * Rappresenta una fotografia dello stato corrente del combattimento.
 *
 * Questa classe serve alla GUI per aggiornare etichette, barre vita e bottoni
 * senza dover conoscere direttamente tutta la logica interna del combattimento.
 */
public class StatoCombattimento {

    private final String nomeEroe;
    private final int hpEroe;
    private final int hpMassimiEroe;

    private final String nomeNemico;
    private final int hpNemico;
    private final int hpMassimiNemico;

    private final int numeroTurno;
    private final boolean combattimentoInCorso;

    public StatoCombattimento(
            String nomeEroe,
            int hpEroe,
            int hpMassimiEroe,
            String nomeNemico,
            int hpNemico,
            int hpMassimiNemico,
            int numeroTurno,
            boolean combattimentoInCorso) {

        this.nomeEroe = nomeEroe;
        this.hpEroe = hpEroe;
        this.hpMassimiEroe = hpMassimiEroe;
        this.nomeNemico = nomeNemico;
        this.hpNemico = hpNemico;
        this.hpMassimiNemico = hpMassimiNemico;
        this.numeroTurno = numeroTurno;
        this.combattimentoInCorso = combattimentoInCorso;
    }

    public String getNomeEroe() {
        return nomeEroe;
    }

    public int getHpEroe() {
        return hpEroe;
    }

    public int getHpMassimiEroe() {
        return hpMassimiEroe;
    }

    public String getNomeNemico() {
        return nomeNemico;
    }

    public int getHpNemico() {
        return hpNemico;
    }

    public int getHpMassimiNemico() {
        return hpMassimiNemico;
    }

    public int getNumeroTurno() {
        return numeroTurno;
    }

    public boolean isCombattimentoInCorso() {
        return combattimentoInCorso;
    }
}
