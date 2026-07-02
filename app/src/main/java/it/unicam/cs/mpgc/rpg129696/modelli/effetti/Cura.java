package it.unicam.cs.mpgc.rpg129696.modelli.effetti;

public class Cura implements Effetto{

    private final int valoreCura;
    private int turniRimanenti = 1;

    public Cura (int valoreCura) {
        this.valoreCura = valoreCura;
    }

    @Override
    public void applica (Personaggio p){
        p.curati(valoreCura);
        turniRimanenti--;
    }

    @Override
    public boolean isScaduto() {
        return turniRimanenti <= 0;
    }
}
