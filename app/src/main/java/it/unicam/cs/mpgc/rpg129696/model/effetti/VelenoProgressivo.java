package it.unicam.cs.mpgc.rpg129696.model.effetti;

import it.unicam.cs.mpgc.rpg129696.model.Personaggio;

public class VelenoProgressivo implements Effetto{

    private int turniRimanenti;
    private int danniTurno;
    private boolean applicato;

    @Override
    public void applica(Personaggio p) {


    }

    @Override
    public boolean isScaduto() {
        return turniRimanenti <= 0;
    }
}
