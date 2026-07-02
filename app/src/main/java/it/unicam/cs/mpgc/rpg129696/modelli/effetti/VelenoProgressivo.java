package it.unicam.cs.mpgc.rpg129696.modelli.effetti;

public class VelenoProgressivo implements Effetto{

    private int turniRimanenti;
    private final int danniTurno;

    public VelenoProgressivo(int turniRimanenti, int danniTurno) {
        this.turniRimanenti = turniRimanenti;
        this.danniTurno = danniTurno;
    }
    @Override
    public void applica(Personaggio p) {
        p.subisciDanno(danniTurno);
        turniRimanenti--;
    }
    @Override
    public boolean isScaduto() {
        return turniRimanenti <= 0;
    }
}
