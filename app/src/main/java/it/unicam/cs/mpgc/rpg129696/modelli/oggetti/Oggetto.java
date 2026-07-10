package it.unicam.cs.mpgc.rpg129696.modelli.oggetti;

import it.unicam.cs.mpgc.rpg129696.modelli.enumerati.TipoOggetto;
import it.unicam.cs.mpgc.rpg129696.modelli.personaggio.PersonaggioBase;

import java.util.Objects;

/**
 * Rappresenta un oggetto utilizzabile nel gioco.
 */
public class Oggetto {

    private final int id;
    private final String nome;
    private final String descrizione;
    private final TipoOggetto tipo;
    private final int valoreCura;
    private final int valoreAttacco;
    private final int turni;

    /**
     * Crea un nuovo oggetto utilizzabile nel gioco.
     *
     * @param id identificativo univoco dell'oggetto
     * @param nome nome dell'oggetto
     * @param descrizione descrizione dell'effetto dell'oggetto
     * @param tipo tipo di oggetto
     * @param valoreCura valore di cura fornito dall'oggetto
     * @param valoreAttacco valore di attacco fornito dall'oggetto
     * @param turni numero di turni necessari o influenzati dall'oggetto
     *
     * @throws IllegalArgumentException se uno dei parametri non rispetta i vincoli previsti
     */
    public Oggetto(int id, String nome, String descrizione, TipoOggetto tipo, int valoreCura,
            int valoreAttacco, int turni) {

        if (id <= 0) {
            throw new IllegalArgumentException("L'id dell'oggetto deve essere positivo");
        }
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Il nome dell'oggetto non puo essere vuoto");
        }
        if (descrizione == null) {
            throw new IllegalArgumentException("La descrizione dell'oggetto non puo essere null");
        }
        if (tipo == null) {
            throw new IllegalArgumentException("Il tipo dell'oggetto non puo essere null");
        }
        if (valoreCura < 0) {
            throw new IllegalArgumentException("Il valore cura non puo essere negativo");
        }
        if (valoreAttacco < 0) {
            throw new IllegalArgumentException("Il valore attacco non puo essere negativo");
        }
        if (turni < 0) {
            throw new IllegalArgumentException("I turni non possono essere negativi");
        }

        this.id = id;
        this.nome = nome;
        this.descrizione = descrizione;
        this.tipo = tipo;
        this.valoreCura = valoreCura;
        this.valoreAttacco = valoreAttacco;
        this.turni = turni;
    }

    /**
     * Applica l'effetto dell'oggetto al personaggio indicato.
     *
     * @param personaggio il personaggio su cui applicare l'effetto
     * @throws IllegalArgumentException se il personaggio e' null
     */
    public void usa(PersonaggioBase personaggio) {
        if (personaggio == null) {
            throw new IllegalArgumentException("Il personaggio non puo essere null");
        }

        CreatoreOggetto.creaEffetto(tipo).applica(personaggio, this);
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public TipoOggetto getTipo() {
        return tipo;
    }

    public int getValoreCura() {
        return valoreCura;
    }

    public int getValoreAttacco() {
        return valoreAttacco;
    }

    public int getTurni() {
        return turni;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Oggetto oggetto)) return false;
        return id == oggetto.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
