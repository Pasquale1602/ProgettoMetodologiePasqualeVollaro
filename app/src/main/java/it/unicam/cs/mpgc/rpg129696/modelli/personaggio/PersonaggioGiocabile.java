package it.unicam.cs.mpgc.rpg129696.modelli.personaggio;

import it.unicam.cs.mpgc.rpg129696.modelli.abilita.Abilita;
import it.unicam.cs.mpgc.rpg129696.modelli.oggetti.Inventario;

public class PersonaggioGiocabile extends PersonaggioBase{

    private final Abilita abilita;
    private final Inventario inventario;
    private static final int BONUS_ATTACCO_PER_LIVELLO = 3;
    private static final int BONUS_DIFESA_PER_LIVELLO = 2;
    private static final int BONUS_HP_PER_LIVELLO = 10;


    public PersonaggioGiocabile(String nome, Statistiche statisticheBase, Abilita abilita) {
        super(nome, statisticheBase);

        if (abilita == null) {
            throw new IllegalArgumentException("L'abilita non puo essere null");
        }

        this.abilita = abilita;
        this.inventario = new Inventario();
    }
    /**
     * Usa l'abilita del personaggio sul bersaglio indicato.
     *
     * @param bersaglio il personaggio su cui applicare l'abilita
     * @throws IllegalArgumentException se il bersaglio è null
     */
    public void usaAbilita(PersonaggioBase bersaglio) {
        if (bersaglio == null) {
            throw new IllegalArgumentException("Il bersaglio non puo essere null");
        }

        abilita.eseguiAbilita(this, bersaglio);
    }

    public void riceviBonusLevelUp(int quantiLivelli) {
        if (quantiLivelli <= 0) return;

        int bonusAttacco = BONUS_ATTACCO_PER_LIVELLO * quantiLivelli;
        int bonusDifesa = BONUS_DIFESA_PER_LIVELLO * quantiLivelli;
        int bonusHp = BONUS_HP_PER_LIVELLO * quantiLivelli;

        Statistiche bonus = new Statistiche (bonusAttacco, bonusDifesa, bonusHp);
        Statistiche nuoveStatistiche = getStatisticheBase().sommaStatistiche(bonus);

        this.aggiornaStatisticheBase(nuoveStatistiche);
    }

    public Abilita getAbilita() {
        return abilita;
    }

    public Inventario getInventario() {
        return inventario;
    }


}
