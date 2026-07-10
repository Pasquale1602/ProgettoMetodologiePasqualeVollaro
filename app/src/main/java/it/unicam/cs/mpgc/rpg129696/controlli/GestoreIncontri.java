package it.unicam.cs.mpgc.rpg129696.controlli;

import it.unicam.cs.mpgc.rpg129696.modelli.partita.Partita;
import it.unicam.cs.mpgc.rpg129696.modelli.personaggio.Nemico;
import it.unicam.cs.mpgc.rpg129696.modelli.personaggio.Statistiche;

import java.util.List;
import java.util.Random;

/**
 * Gestisce la scelta e l'assegnazione degli incontri della partita.
 *
 * Questa classe evita che la GUI debba conoscere come vengono scelti
 * o creati i nemici da affrontare.
 */
public class GestoreIncontri {

    private final Random random;

    public GestoreIncontri() {
        this(new Random());
    }

    public GestoreIncontri(Random random) {
        if (random == null) {
            throw new IllegalArgumentException("Il generatore casuale non puo essere null");
        }

        this.random = random;
    }

    /**
     * Sceglie casualmente un nemico dalla lista e lo assegna alla partita.
     *
     * @param partita la partita a cui assegnare il nemico
     * @param nemiciDisponibili lista dei nemici disponibili
     */
    public void assegnaNemicoCasuale(Partita partita, List<Nemico> nemiciDisponibili) {
        if (partita == null) {
            throw new IllegalArgumentException("La partita non puo essere null");
        }
        if (nemiciDisponibili == null || nemiciDisponibili.isEmpty()) {
            throw new IllegalArgumentException("La lista dei nemici disponibili non puo essere vuota");
        }

        Nemico modello = nemiciDisponibili.get(random.nextInt(nemiciDisponibili.size()));
        partita.setNemicoCorrente(copiaNemico(modello));
    }

    private Nemico copiaNemico(Nemico modello) {
        return new Nemico(
                modello.getNome(),
                new Statistiche(modello.getStatisticheBase()),
                modello.getRicompensaEsperienza(),
                modello.getComportamentoNemico().nuovaIstanza(),
                modello.getTipoComportamento()
        );
    }
}
