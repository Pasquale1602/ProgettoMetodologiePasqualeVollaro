package it.unicam.cs.mpgc.rpg129696.modelli.oggetti;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Inventario {
    private final List <Contenuto> contenuto;
    private final int capacitaMassima = 104;

    public Inventario () {
        this.contenuto = new ArrayList<>();
    }

    public boolean aggiungiOggetto(Oggetto oggetto, int quantita) {
        if (oggetto == null) throw new NullPointerException("L'oggetto non può essere nullo");
        if (quantita <= 0) return false;

        return contenuto.stream()
                .filter(slot -> slot.getOggetto().equals(oggetto))
                .findFirst()
                .map(slot -> {

                    slot.setQuantita(slot.getQuantita() + quantita);
                    return true;
                })
                .orElseGet(() -> {

                    if (contenuto.size() >= capacitaMassima) return false;
                    contenuto.add(new Contenuto(oggetto, quantita));
                    return true;
                });
    }

    /**
     * Rimuove un oggetto dall'inventario
     * @param oggetto l'oggetto da rimuovere
     * @return true se l'oggetto si trova nell'inventario, false altrimenti
     */
    public boolean rimuoviOggetto(Oggetto oggetto) {
        if (oggetto == null || contenuto.isEmpty()) return false;

        return contenuto.stream()
                .filter(slot -> slot.getOggetto().equals(oggetto))
                .findFirst()
                .map(slot -> {

                    if (slot.getQuantita() > 1) {
                        slot.setQuantita(slot.getQuantita() - 1);
                    } else {
                        contenuto.remove(slot);
                    }
                    return true;
                })
                .orElse(false);
    }

    public List <Contenuto> getContenuto() {
        return Collections.unmodifiableList(contenuto);
    }
}
