package it.unicam.cs.mpgc.rpg129696.modelli.personaggio;

import it.unicam.cs.mpgc.rpg129696.modelli.modificatori.Modificatore;
import it.unicam.cs.mpgc.rpg129696.modelli.modificatori.ModificatoreTemporaneo;
import it.unicam.cs.mpgc.rpg129696.modelli.modificatori.ModificatoreVeleno;
import it.unicam.cs.mpgc.rpg129696.modelli.modificatori.SistemaModificatori;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class PersonaggioBase implements Danneggiabile {

    private final String nome;
    protected Statistiche statisticheBase;
    private int hpAttuali;
    private final List<ModificatoreTemporaneo> modificatoriTemporanei = new ArrayList<>();


    protected PersonaggioBase(String nome, Statistiche statisticheBase) {

        this.statisticheBase = statisticheBase;
        this.nome = nome;
        this.hpAttuali = statisticheBase.getHpMassimi();
    }

    public Statistiche getStatisticheAttuali() {
        Statistiche calcolate = this.statisticheBase;

        for (ModificatoreTemporaneo mod : modificatoriTemporanei) {
            calcolate = mod.applica(calcolate);
        }
        return calcolate;
    }


    public String getNome() { return nome; }
    public int getHpAttuali() { return hpAttuali; }
    public Statistiche getStatisticheBase () { return statisticheBase; }


    public void attacca (PersonaggioBase bersaglio) {
        int attaccoAttuale = this.getStatisticheAttuali().getAttacco();
        int difesaBersaglio = bersaglio.getStatisticheAttuali().getDifesa();
        int dannoFinale = Math.max(1, attaccoAttuale - difesaBersaglio);

        bersaglio.prendiDanno(dannoFinale);
    }
    public void cura (int quantita) {
        if (quantita <= 0) throw new IllegalArgumentException
                ("la quantità di cura non può essere minore o uguale di 0");
        int hpMassimi = getStatisticheAttuali().getHpMassimi();
        hpAttuali = Math.min(hpAttuali + quantita, hpMassimi);
    }


    @Override
    public void prendiDanno(int danno) {
        if (danno <= 0) return;
        hpAttuali -= danno;
        if (hpAttuali < 0) {
            hpAttuali = 0;
        }
    }
    //rimuove tutti gli effetti di veleno dal giocatore
    public void rimuoviVeleno() {
        modificatoriTemporanei.removeIf(mt -> mt.getModificatore()
                instanceof ModificatoreVeleno);
    }


    @Override
    public boolean isVivo() {
        return hpAttuali > 0;
    }


    public void aggiungiModificatore (ModificatoreTemporaneo modificatore) {
        modificatoriTemporanei.add(modificatore);
    }


    /**
     * <p>Per ogni modificatore temporaneo attivo:
     * <ol>
     * <li> Applica l'effetto del modificatore sul personaggio</li>
     * <li> Decrementa il contatore dei turni rimanenti</li>
     * <li> Rimuove i modificatori scaduti</li>
     *</ol>
     * Al termine normalizza gli HP attuali per gestire i casi in cui
     * gli HpAttuali superino quelli massimi consentiti.</p>
     *</ol>
     * <p> Deve essere chiamato una volta per turno dal gestore del combattimento. </p>
     */
    public void aggiornaTurnoModificatori () {
        modificatoriTemporanei.forEach(mt -> mt.getModificatore().applicaSuPersonaggio(this));
        modificatoriTemporanei.forEach(ModificatoreTemporaneo::tick);
        modificatoriTemporanei.removeIf(ModificatoreTemporaneo::isScaduto);

        //Dopo aver rimosso i modificatori controllo che gli HP siano ancora validi
        normalizzaSalute();
    }

    /**
     * normalizzaSalute() serve nel caso in cui con un potenziamento di hp,
     * il giocatore rimanga con gli hpAttuali > hpMassimi.
     *Questo metodo normalizza allora gli hpAttuali a quelli massimi.
     */
    private void normalizzaSalute() {
        int maxHp = getStatisticheAttuali().getHpMassimi();
        if (this.hpAttuali > maxHp) {
            this.hpAttuali = maxHp;
        }
    }

    /**
     * Rimuove ogni tipo di modificatore temporaneo
     */
    public void rimuoviModificatori() {
        modificatoriTemporanei.clear();
    }
    protected void aggiornaStatisticheBase (Statistiche nuoveStatistiche) {
        if (nuoveStatistiche != null) {
            this.statisticheBase = nuoveStatistiche;
        }
    }

    /**
     * @return Oggetto di tipo Collection contenente tutti i modificatori temporanei
     */
    public List <ModificatoreTemporaneo> getModificatoriTemporanei() {
        return Collections.unmodifiableList(modificatoriTemporanei);
    }
}
