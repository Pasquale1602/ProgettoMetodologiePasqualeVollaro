package it.unicam.cs.mpgc.rpg129696.modelli.enumerati;

/**
 * Definisce i tipi di oggetti disponibili nell'inventario.
 * Ogni valore corrisponde a un effetto specifico applicabile
 * al personaggio tramite {@code OggettoFactory}.
 */
public enum TipoOggetto {

    /** Ripristina una quantità di HP al personaggio. */
    POZIONE,

    /** Rimuove tutti gli effetti veleno attivi sul personaggio. */
    ANTIDOTO,

    /** Applica un buff temporaneo all'attacco per alcuni turni. */
    POZIONE_FURIA
}