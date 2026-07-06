package it.unicam.cs.mpgc.rpg129696.modelli.oggetti;

public class Contenuto {
    private Oggetto oggetto;
    private int quantita;

    public Contenuto(Oggetto oggetto, int quantita) {
        this.oggetto = oggetto;
        this.quantita = quantita;
    }
    public Oggetto getOggetto() {
        return oggetto;
    }
    public int getQuantita() {
        return quantita;
    }
    public void setOggetto(Oggetto oggetto) {
        this.oggetto = oggetto;
    }
    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }
}
