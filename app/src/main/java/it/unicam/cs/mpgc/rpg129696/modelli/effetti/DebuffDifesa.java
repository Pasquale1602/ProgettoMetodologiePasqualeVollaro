package it.unicam.cs.mpgc.rpg129696.modelli.effetti;

public class DebuffDifesa implements Effetto{

    private int turniRimanenti;
    private final int valoreBuff;
    private boolean applicato; //per applicare bonus solo prima volta

    public  DebuffDifesa(int turniRimanenti, int valoreBuff) {
        this.turniRimanenti = turniRimanenti;
        this.valoreBuff = valoreBuff;
    }

    @Override
    public void applica(Personaggio p) {
        if (!applicato) {
            p.setDifesa(p.getDifesa()-valoreBuff); //metto buff
            applicato = true;
        }
        turniRimanenti--;
        if (turniRimanenti <= 0) {
            p.setDifesa(p.getDifesa()+valoreBuff);//tolgo buff alla fine dei turni
        }
    }

    @Override
    public boolean isScaduto() {
        return turniRimanenti <= 0;
    }
}
