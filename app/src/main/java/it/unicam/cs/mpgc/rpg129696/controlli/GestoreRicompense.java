package it.unicam.cs.mpgc.rpg129696.controlli;

import it.unicam.cs.mpgc.rpg129696.modelli.oggetti.Oggetto;

import java.util.List;
import java.util.Random;

/**
 * Gestisce le ricompense ottenute dopo un combattimento.
 */
public class GestoreRicompense {

    private final Random random;

    public GestoreRicompense() {
        this.random = new Random();
    }

    /**
     * Estrae casualmente un oggetto come ricompensa.
     *
     * @param oggettiDisponibili lista degli oggetti disponibili
     * @return oggetto estratto, oppure {@code null} se non ci sono oggetti disponibili
     */
    public Oggetto estraiOggetto(List<Oggetto> oggettiDisponibili) {
        if (oggettiDisponibili == null || oggettiDisponibili.isEmpty()) {
            return null;
        }

        int indice = random.nextInt(oggettiDisponibili.size());
        return oggettiDisponibili.get(indice);
    }
}
