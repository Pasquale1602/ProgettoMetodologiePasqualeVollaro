package it.unicam.cs.mpgc.rpg129696.modelli.personaggio;

import it.unicam.cs.mpgc.rpg129696.modelli.modificatori.Modificatore;
import it.unicam.cs.mpgc.rpg129696.modelli.modificatori.ModificatoreTemporaneo;
import it.unicam.cs.mpgc.rpg129696.modelli.modificatori.SistemaModificatori;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class PersonaggioBase implements Danneggiabile {

    private final String nome;
    private final Statistica statisticaBase;
    private int hpAttuali;
    private final List<ModificatoreTemporaneo> modificatoriTemporanei = new ArrayList<>();


    protected PersonaggioBase(String nome, Statistica statisticaBase) {

        this.statisticaBase = statisticaBase;
        this.nome = nome;
        this.hpAttuali = statisticaBase.getHpMassimi();
    }


    public Statistica getStatisticheAttuali() {
        List<Modificatore> tutti = new ArrayList<>();
        for (ModificatoreTemporaneo mt : modificatoriTemporanei) {
            tutti.add(mt.getModificatore());
        }
        return SistemaModificatori.calcola(statisticaBase, tutti);
    }


    public String getNome() { return nome; }
    public int getHpAttuali() { return hpAttuali; }
    public Statistica getStatisticheBase () { return statisticaBase; }


    public void cura (int quantita) {
        if (quantita <= 0) throw new IllegalArgumentException
                ("la quantità di cura non può essere minore o uguale di 0");
        int hpMassimi = getStatisticheAttuali().getHpMassimi();
        hpAttuali = Math.min(hpAttuali + quantita, hpMassimi);
    }


    @Override
    public void prendiDanno(int danno) {
        hpAttuali -= danno;
        if (hpAttuali < 0) {
            hpAttuali = 0;
        }
    }


    @Override
    public boolean isMorto() {
        return hpAttuali == 0;
    }


    public void aggiungiModificatore (ModificatoreTemporaneo modificatore) {
        modificatoriTemporanei.add(modificatore);
    }


    public void aggiornaTurnoModificatori () {
        modificatoriTemporanei.forEach(ModificatoreTemporaneo::tick);
        modificatoriTemporanei.removeIf(ModificatoreTemporaneo::isScaduto);
    }


    public void rimuoviModificatori() {
        modificatoriTemporanei.clear();
    }


    public List <ModificatoreTemporaneo> getModificatoriTemporanei() {
        return Collections.unmodifiableList(modificatoriTemporanei);
    }
}
