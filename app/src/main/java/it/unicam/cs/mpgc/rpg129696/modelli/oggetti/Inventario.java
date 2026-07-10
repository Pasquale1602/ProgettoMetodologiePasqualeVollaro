package it.unicam.cs.mpgc.rpg129696.modelli.oggetti;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Rappresenta l'inventario di un personaggio
 *
 * L'inventario contiene una lista di slot, ognuno rappresentato da un
 * {@link Contenuto}, che associa un oggetto alla relativa quantita
 */
public class Inventario {
    private final List <Contenuto> contenuto;
    private static final int CAPACITA_MASSIMA = 12;

    /**
     * Crea un inventario vuoto.
     */
    public Inventario () {
        this.contenuto = new ArrayList<>();
    }

    /**
     * Aggiunge una certa quantita di oggetti all'inventario
     * Se l'oggetto è già presente, aumenta la quantità nello slot esistente,
     * Se invece non è presente, crea un nuovo slot, sempre se la capacità massima non è
     * stata raggiunta
     * @param oggetto l'oggetto da aggiungere
     * @param quantita la quantità dell'oggetto da aggiungere
     * @return true se l'aggiunta è andata a buon fine, false altrimenti
     * @throws IllegalArgumentException se l'oggetto passato è null
     */
    public boolean aggiungiOggetto(Oggetto oggetto, int quantita) {
        if (oggetto == null) {
            throw new IllegalArgumentException("L'oggetto non puo essere null");
        }
        if (quantita <= 0) {
            return false;
        }

        return contenuto.stream()
                .filter(slot -> slot.getOggetto().equals(oggetto))
                .findFirst()
                .map(slot -> {
                    slot.setQuantita(slot.getQuantita() + quantita);
                    return true;
                })
                .orElseGet(() -> {
                    if (contenuto.size() >= CAPACITA_MASSIMA) {
                        return false;
                    }

                    contenuto.add(new Contenuto(oggetto, quantita));
                    return true;
                });
    }

    /**
     * Rimuove una singola unità dell'oggetto indicato dall'inventario
     * Se la quantita dello slot è maggiore di uno, viene decrementata.
     * Se la quantita è pari a uno, lo slot viene rimosso dall'inventario.
     * @param oggetto l'oggetto da rimuovere
     * @return true se l'oggetto si trova nell'inventario, false altrimenti
     */
    public boolean rimuoviOggetto(Oggetto oggetto) {
        if (oggetto == null || contenuto.isEmpty()) {
            return false;
        }

        for (int i = 0; i < contenuto.size(); i++) {
            Contenuto slot = contenuto.get(i);

            if (slot.getOggetto().equals(oggetto)) {
                if (slot.getQuantita() > 1) {
                    slot.setQuantita(slot.getQuantita() - 1);
                } else {
                    contenuto.remove(i);
                }

                return true;
            }
        }

        return false;
    }

    /**Restituisce il contenuto dell'inventario
     *
     * @return lista non modificabile degli slot presenti nell'inventario
     */

    public List <Contenuto> getContenuto() {
        return Collections.unmodifiableList(contenuto);
    }
}
