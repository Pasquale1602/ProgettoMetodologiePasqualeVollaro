package it.unicam.cs.mpgc.rpg129696.modelli.partita;

import it.unicam.cs.mpgc.rpg129696.modelli.personaggio.Giocatore;
import it.unicam.cs.mpgc.rpg129696.modelli.personaggio.Nemico;

import java.time.LocalDateTime;

/**
 * Rappresenta lo stato principale di una partita in corso.
 *
 * Questa classe raccoglie le informazioni che descrivono la sessione
 * corrente: il giocatore, il nemico affrontato e l'avanzamento della partita.
 */
public class Partita {

    private final Giocatore giocatore;
    private Nemico nemicoCorrente;
    private int combattimentiVinti;
    private LocalDateTime ultimoSalvataggio;

    /**
     * Crea una nuova partita associata al giocatore indicato.
     *
     * @param giocatore il giocatore della partita
     * @throws IllegalArgumentException se il giocatore è null
     */
    public Partita(Giocatore giocatore) {
        if (giocatore == null) {
            throw new IllegalArgumentException("Il giocatore non puo essere null");
        }

        this.giocatore = giocatore;
        this.combattimentiVinti = 0;
        this.ultimoSalvataggio = null;
    }

    public Giocatore getGiocatore() {
        return giocatore;
    }

    public Nemico getNemicoCorrente() {
        return nemicoCorrente;
    }

    /**
     * Imposta il nemico attualmente affrontato nella partita.
     *
     * @param nemicoCorrente il nemico corrente
     */
    public void setNemicoCorrente(Nemico nemicoCorrente) {
        this.nemicoCorrente = nemicoCorrente;
    }

    public int getCombattimentiVinti() {
        return combattimentiVinti;
    }

    /**
     * Registra la vittoria di un combattimento.
     */
    public void registraVittoria() {
        combattimentiVinti++;
        nemicoCorrente = null;
    }

    public LocalDateTime getUltimoSalvataggio() {
        return ultimoSalvataggio;
    }

    /**
     * Aggiorna la data dell'ultimo salvataggio della partita.
     */
    public void aggiornaUltimoSalvataggio() {
        ultimoSalvataggio = LocalDateTime.now();
    }

    /**
     * Ripristina il numero di combattimenti vinti da un salvataggio.
     *
     * @param combattimentiVinti numero di combattimenti vinti salvati
     * @throws IllegalArgumentException se il valore e' negativo
     */
    public void ripristinaCombattimentiVinti(int combattimentiVinti) {
        if (combattimentiVinti < 0) {
            throw new IllegalArgumentException("Il numero di combattimenti vinti non puo essere negativo");
        }

        this.combattimentiVinti = combattimentiVinti;
    }

    /**
     * Ripristina la data dell'ultimo salvataggio.
     *
     * @param ultimoSalvataggio data dell'ultimo salvataggio, puo essere null
     */
    public void ripristinaUltimoSalvataggio(LocalDateTime ultimoSalvataggio) {
        this.ultimoSalvataggio = ultimoSalvataggio;
    }
}
