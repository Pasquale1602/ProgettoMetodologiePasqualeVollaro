package it.unicam.cs.mpgc.rpg129696.modelli.personaggio;

/**
 * Definisce il comportamento comune degli oggetti che possono subire danni
 * durante il combattimento.
 */
public interface Danneggiabile {

    /**
     * Applica un danno all'oggetto.
     *
     * @param danno quantità di danno ricevuta
     */
    void prendiDanno(int danno);

    /**
     * Verifica se l'oggetto è ancora in vita.
     *
     * @return true se i punti vita sono maggiori di zero, false altrimenti
     */
    boolean isVivo();
}
