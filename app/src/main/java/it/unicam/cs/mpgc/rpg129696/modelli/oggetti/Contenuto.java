package it.unicam.cs.mpgc.rpg129696.modelli.oggetti;
/**
 * Rappresenta uno slot dell'inventario contenente un oggetto e la relativa quantità.
 */
public class Contenuto {
    private final Oggetto oggetto;
    private int quantita;

    /**
     * Crea uno slot contenente un oggetto con una determinata quantità.
     *
     * @param oggetto oggetto contenuto nello slot
     * @param quantita quantità iniziale dell'oggetto
     *
     * @throws IllegalArgumentException se l'oggetto è null o la quantità non è positiva
     */
    public Contenuto(Oggetto oggetto, int quantita) {
        if (oggetto == null) {
            throw new IllegalArgumentException("L'oggetto non puo essere null");
        }

        if (quantita <= 0) {
            throw new IllegalArgumentException("La quantita deve essere positiva");
        }
        this.oggetto = oggetto;
        this.quantita = quantita;
    }


    public Oggetto getOggetto() {
        return oggetto;
    }


    public int getQuantita() {
        return quantita;
    }

    /**
     * Aggiorna la quantità presente nello slot.
     *
     * @param quantita nuova quantità
     * @throws IllegalArgumentException se la quantità non è positiva
     */
    public void setQuantita(int quantita) {
        if (quantita <= 0) {
            throw new IllegalArgumentException("La quantita deve essere positiva");
        }

        this.quantita = quantita;
    }
}
