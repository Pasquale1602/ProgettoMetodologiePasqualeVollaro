package it.unicam.cs.mpgc.rpg129696.modelli.personaggio;

import it.unicam.cs.mpgc.rpg129696.modelli.abilita.Abilita;
import it.unicam.cs.mpgc.rpg129696.modelli.oggetti.Inventario;

public class PersonaggioGiocabile extends PersonaggioBase{

    private final Abilita abilita;
    private final Inventario inventario;


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

        int bonusAttacco = 3 * quantiLivelli;
        int bonusDifesa = 2 * quantiLivelli;
        int bonusHp = 10 * quantiLivelli;

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
