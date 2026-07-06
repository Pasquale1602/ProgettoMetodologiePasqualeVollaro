package it.unicam.cs.mpgc.rpg129696.modelli.personaggio;

public class Progressione {
    private int livello;
    private int esperienza;
    private int esperienzaPerLivelloSuccessivo;

    public Progressione (int livello, int esperienza, int esperienzaPerLivelloSuccessivo) {
        this.livello = livello;
        this.esperienza = esperienza;
        this.esperienzaPerLivelloSuccessivo = esperienzaPerLivelloSuccessivo;
    }
    public void aggiungiEsperienza(int esperienza) {
        this.esperienza += esperienza;
        while (this.esperienza >= esperienzaPerLivelloSuccessivo) {
            int esperienzaExtra = this.esperienza - esperienzaPerLivelloSuccessivo;
            this.esperienza = esperienzaExtra;
            this.saliLivello();
        }
    }
    public int getLivello() { return this.livello; }
    public int getEsperienza() { return this.esperienza; }
    public int getEsperienzaPerLivelloSuccessivo() {return this.esperienzaPerLivelloSuccessivo;}

    public void saliLivello() {
        this.livello++;
        this.esperienzaPerLivelloSuccessivo = (int) (esperienzaPerLivelloSuccessivo * 1.5);
    }
}
