package it.unicam.cs.mpgc.rpg129696.model.effetti;

import it.unicam.cs.mpgc.rpg129696.model.Personaggio;

public interface Effetto {

    public void applica(Personaggio p);

    public boolean isScaduto();
}
