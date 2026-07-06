package it.unicam.cs.mpgc.rpg129696.modelli.personaggio;

/**
 * Rappresenta le statistiche di un personaggio
 * Progettata come
 */
public class Statistiche {

    private final int attacco;
    private final int difesa;
    private final int hpMassimi;

    public Statistiche() {
        this(0,0,0);
    }

    public Statistiche(int attacco, int difesa, int hpMassimi) {
        this.attacco = Math.max(0,attacco);
        this.difesa = Math.max(0,difesa);
        this.hpMassimi = Math.max(0,hpMassimi);
    }

    public Statistiche(Statistiche other) {
        this.attacco = other.attacco;
        this.difesa = other.difesa;
        this.hpMassimi = other.hpMassimi;
    }

    public int getAttacco() { return attacco; }
    public int getDifesa() { return difesa; }
    public int getHpMassimi() { return hpMassimi; }

    /**
     * Somma due blocchi di statistiche restituendone uno nuovo
     * @param altre le statistiche da sommmare a this
     * @return le nuove statistiche sommate
     */
    public Statistiche sommaStatistiche (Statistiche altre) {
        return new Statistiche(
                this.attacco + altre.getAttacco(),
                this.difesa + altre.getDifesa(),
                this.hpMassimi + altre.getHpMassimi());
    }
    /**
     * Restituisce una nuova istanza di Statistiche con l'attacco variato.
     */
    public Statistiche conAttacco(int nuovoAttacco) {
        return new Statistiche(nuovoAttacco, this.difesa, this.hpMassimi);
    }

    /**
     * Restituisce una nuova istanza di Statistiche con la difesa variata.
     */
    public Statistiche conDifesa(int nuovaDifesa) {
        return new Statistiche(this.attacco, nuovaDifesa, this.hpMassimi);
    }

    /**
     * Restituisce una nuova istanza di Statistiche con gli HP massimi variati.
     */
    public Statistiche conHpMassimi(int nuoviHpMassimi) {
        return new Statistiche(this.attacco, this.difesa, nuoviHpMassimi);
    }
}
