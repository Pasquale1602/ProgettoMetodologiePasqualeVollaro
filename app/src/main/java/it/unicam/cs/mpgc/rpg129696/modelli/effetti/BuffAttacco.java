package it.unicam.cs.mpgc.rpg129696.modelli.effetti;

public class BuffAttacco implements Effetto {

    private int turniRimanenti;
    private final int valoreBuff;
    private boolean applicato; //per applicare bonus solo prima volta

    public  BuffAttacco(int turniRimanenti, int valoreBuff) {
        this.turniRimanenti = turniRimanenti;
        this.valoreBuff = valoreBuff;
    }

    @Override
    public void applica(Personaggio p) {
        if (!applicato) {
            p.setAttacco(p.getAttacco()+valoreBuff); //metto buff
            applicato = true;
        }
        turniRimanenti--;
        if (turniRimanenti <= 0) {
            p.setAttacco(p.getAttacco()-valoreBuff);//tolgo buff alla fine dei turni
        }
    }

    @Override
    public boolean isScaduto() {
        return turniRimanenti <= 0;
    }
}
