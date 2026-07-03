package it.unicam.cs.mpgc.rpg129696.modelli.modificatori;

/**
 * Aggiunge una durata temporanea a qualsiasi {@link Modificatore}.
 * A ogni turno deve essere chiamato {@link #tick()} per decrementare il contatore.
 * Quando {@link #isScaduto()} restituisce {@code true}, il modificatore
 * deve essere rimosso dal personaggio.
 */
public class ModificatoreTemporaneo {

    private final Modificatore modificatore;
    private int turniRimanenti;
    /**
     * Crea un nuovo modificatore temporaneo.
     *
     * @param modificatore il modificatore da applicare, non deve essere null
     * @param turniRimanenti il numero di turni per cui il modificatore è attivo, deve essere maggiore di zero
     * @throws NullPointerException se il modificatore è null
     * @throws IllegalArgumentException se i turni rimanenti sono minori o uguali a zero
     */
    public ModificatoreTemporaneo(Modificatore modificatore, int turniRimanenti) {
        if (modificatore == null) throw new NullPointerException("Il modificatore non può essere null");
        if (turniRimanenti <= 0) throw new IllegalArgumentException("I turni rimanenti devono essere maggiori di zero");
        this.modificatore = modificatore;
        this.turniRimanenti = turniRimanenti;
    }
    public Modificatore getModificatore() {
        return modificatore;
    }

    public int getTurniRimanenti() {
        return turniRimanenti;
    }

    public boolean isScaduto() {
        return turniRimanenti <= 0;
    }

    public void tick() {
        turniRimanenti--;
    }
}
