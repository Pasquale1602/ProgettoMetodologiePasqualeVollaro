package it.unicam.cs.mpgc.rpg129696.modelli.personaggio;

/**
 * Gestisce livello ed esperienza di un personaggio giocabile.
 */
public class Progressione {
    private int livello;
    private int esperienza;
    private int esperienzaPerLivelloSuccessivo;


    public Progressione(int livello, int esperienza, int esperienzaPerLivelloSuccessivo) {
        if (livello <= 0) {
            throw new IllegalArgumentException("Il livello deve essere maggiore di zero");
        }
        if (esperienza < 0) {
            throw new IllegalArgumentException("L'esperienza non puo essere negativa");
        }
        if (esperienzaPerLivelloSuccessivo <= 0) {
            throw new IllegalArgumentException("L'esperienza richiesta per il livello successivo deve essere maggiore di zero");
        }

        this.livello = livello;
        this.esperienza = esperienza;
        this.esperienzaPerLivelloSuccessivo = esperienzaPerLivelloSuccessivo;
    }

    /**
     * Aggiunge esperienza e restituisce il numero di livelli guadagnati.
     *
     * @param esperienza esperienza da aggiungere
     * @return numero di livelli guadagnati dopo l'aggiunta
     */
    public int aggiungiEsperienza(int esperienza) {

        if  (esperienza <= 0) {
            return 0;
        }
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

    /**
     * /**
     *  * Aumenta il livello di uno e aggiorna l'esperienza richiesta
     *  * per il livello successivo.
     *  */
    public void saliLivello() {
        this.livello++;
        this.esperienzaPerLivelloSuccessivo = (int) (esperienzaPerLivelloSuccessivo * 1.5);
    }
}
