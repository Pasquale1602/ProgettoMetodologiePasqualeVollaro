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

    /**
     * Aggiunge esperienza e restituisce il numero di livelli guadagnati in questo incremento
     * @param esperienza l'esperienza da aggiungere
     * @return il numero di livelli guadagnati
     */
    public int aggiungiEsperienza(int esperienza) {

        this.esperienza += esperienza;
        int livelliGuadagnati = 0;

        while (this.esperienza >= esperienzaPerLivelloSuccessivo) {
            this.esperienza -= esperienzaPerLivelloSuccessivo;
            this.saliLivello();
            livelliGuadagnati++;
        }
        return livelliGuadagnati;
    }
    public int getLivello() { return this.livello; }
    public int getEsperienza() { return this.esperienza; }
    public int getEsperienzaPerLivelloSuccessivo() {return this.esperienzaPerLivelloSuccessivo;}

    public void saliLivello() {
        this.livello++;
        this.esperienzaPerLivelloSuccessivo = (int) (esperienzaPerLivelloSuccessivo * 1.5);
    }
}
