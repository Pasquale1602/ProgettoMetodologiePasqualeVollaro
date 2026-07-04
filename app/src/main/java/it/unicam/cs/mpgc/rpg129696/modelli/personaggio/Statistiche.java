package it.unicam.cs.mpgc.rpg129696.modelli.personaggio;

public class Statistiche {

    private int attacco;
    private int difesa;
    private int hpMassimi;

    public Statistiche() {
        this.attacco = 0;
        this.difesa = 0;
        this.hpMassimi = 0;
    }

    public Statistiche(int attacco, int difesa, int hpMassimi) {
        this.attacco = attacco;
        this.difesa = difesa;
        this.hpMassimi = hpMassimi;
    }

    public Statistiche(Statistiche other) {
        this.attacco = other.attacco;
        this.difesa = other.difesa;
        this.hpMassimi = other.hpMassimi;
    }

    public int getAttacco() { return attacco; }
    public int getDifesa() { return difesa; }
    public int getHpMassimi() { return hpMassimi; }

    public void setAttacco(int attacco) { this.attacco = attacco; }
    public void setDifesa(int difesa) { this.difesa = difesa; }
    public void setHpMassimi(int hpMassimi) { this.hpMassimi = hpMassimi; }

}
