package it.unicam.cs.mpgc.rpg129696.modelli.enumerati;
/**
 * Definisce i tipi di comportamento disponibili per i nemici.
 * Ogni valore corrisponde a una strategia di combattimento specifica
 * e viene utilizzato per istanziare il {@code ComportamentoNemico} corretto.
 */
public enum TipoComportamento {

    /** Attacca sempre il bersaglio. Usato da Infetto, Mutato e Saccheggiatore. */
    AGGRESSIVO,

    /** Applica veleno al bersaglio e attacca. Usato dal Contaminato. */
    DOT,

    /** Si buffa la difesa e attacca. Usato dal Fanatico. */
    DIFENSIVO,

    /** Si potenzia quando gli HP scendono sotto il 50%. Usato dall'Aberrazione. */
    BOSS
}
