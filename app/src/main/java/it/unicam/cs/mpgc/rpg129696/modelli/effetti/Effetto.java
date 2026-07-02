package it.unicam.cs.mpgc.rpg129696.modelli.effetti;

public interface Effetto {

    public void applica(Personaggio p);

    public boolean isScaduto();
}
