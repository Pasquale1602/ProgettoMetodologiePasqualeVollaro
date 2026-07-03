package it.unicam.cs.mpgc.rpg129696.modelli.modificatori;

import it.unicam.cs.mpgc.rpg129696.modelli.personaggio.PersonaggioBase;
import it.unicam.cs.mpgc.rpg129696.modelli.personaggio.Statistica;

public class ModificatoreVeleno implements Modificatore {

    private final int dannoVeleno;

    public ModificatoreVeleno (int dannoVeleno) {
        if (dannoVeleno <= 0) throw new IllegalArgumentException("il danno da veleno è <= 0");
        this.dannoVeleno = dannoVeleno;
    }


    @Override
    public void applica(Statistica statistica) {

    }

    @Override
    public void applicaSuPersonaggio (PersonaggioBase personaggio) {
        personaggio.prendiDanno(dannoVeleno);
    }
}
