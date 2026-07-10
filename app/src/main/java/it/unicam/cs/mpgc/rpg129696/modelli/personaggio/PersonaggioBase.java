package it.unicam.cs.mpgc.rpg129696.modelli.personaggio;

import it.unicam.cs.mpgc.rpg129696.modelli.modificatori.ModificatoreTemporaneo;
import it.unicam.cs.mpgc.rpg129696.modelli.modificatori.ModificatoreVeleno;
import it.unicam.cs.mpgc.rpg129696.modelli.modificatori.SistemaModificatori;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Classe base per tutti i personaggi del gioco.
 *
 * Gestisce nome, statistiche, punti vita e modificatori temporanei.
 * Le sottoclassi definiscono comportamenti specifici del personaggio.
 */
public abstract class PersonaggioBase implements Danneggiabile {

    private final String nome;
    private Statistiche statisticheBase;
    private int hpAttuali;
    private final List<ModificatoreTemporaneo> modificatoriTemporanei = new ArrayList<>();

    /**
     * Crea un personaggio con le statistiche iniziali indicate.
     *
     * @param nome nome del personaggio
     * @param statisticheBase statistiche iniziali
     * @throws IllegalArgumentException se nome o statistiche sono null
     */
    protected PersonaggioBase(String nome, Statistiche statisticheBase) {
        if (nome == null) {throw new IllegalArgumentException("il nome non puo essere null");}
        if (statisticheBase == null) {throw new IllegalArgumentException("le statisticheBase" +
                " non possono essere null");}

        this.statisticheBase = statisticheBase;
        this.nome = nome;
        this.hpAttuali = statisticheBase.getHpMassimi();
    }

    public Statistiche getStatisticheAttuali() {
        return SistemaModificatori.calcola(statisticheBase, modificatoriTemporanei);
    }


    public String getNome() { return nome; }
    public int getHpAttuali() { return hpAttuali; }
    public Statistiche getStatisticheBase () { return statisticheBase; }

    /**
     * Attacca un altro personaggio calcolando il danno in base ad attacco e difesa.
     *
     * @param bersaglio personaggio che riceve il danno
     */
    public void attacca (PersonaggioBase bersaglio) {
        int attaccoAttuale = this.getStatisticheAttuali().getAttacco();
        int difesaBersaglio = bersaglio.getStatisticheAttuali().getDifesa();
        int dannoFinale = Math.max(1, attaccoAttuale - difesaBersaglio);

        bersaglio.prendiDanno(dannoFinale);
    }

    /**
     * Cura il personaggio aumentando gli HP attuali senza superare gli HP massimi.
     *
     * @param quantita quantità di punti vita da recuperare
     * @throws IllegalArgumentException se la quantità è minore o uguale a zero
     */
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

    /**
     * Aggiunge un modificatore temporaneo al personaggio.
     *
     * @param modificatore modificatore da applicare
     */
    public void aggiungiModificatore (ModificatoreTemporaneo modificatore) {
        modificatoriTemporanei.add(modificatore);
    }


    /**
     * <p>Per ogni modificatore temporaneo attivo:
     * <ol>
     * <li>Applica l'effetto del modificatore sul personaggio</li>
     * <li>Decrementa il contatore dei turni rimanenti</li>
     * <li>Rimuove i modificatori scaduti</li>
     * </ol>
     * Al termine normalizza gli HP attuali per gestire i casi in cui
     * gli HpAttuali superino quelli massimi consentiti.</p>
     * <p>Deve essere chiamato una volta per turno dal gestore del combattimento.</p>
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
     * Ripristina gli HP attuali del personaggio, rispettando i limiti validi.
     *
     * <p>Il valore viene limitato tra 0 e gli HP massimi attuali.</p>
     *
     * @param hp gli HP da ripristinare
     */
    public void ripristinaHp(int hp) {
        int hpMassimi = getStatisticheAttuali().getHpMassimi();

        if (hp < 0) {
            this.hpAttuali = 0;
        } else {
            this.hpAttuali = Math.min(hp, hpMassimi);
        }
    }

    /**
     * Rimuove ogni tipo di modificatore temporaneo
     */
    public void rimuoviModificatori() {
        modificatoriTemporanei.clear();
    }

    /**
     * Aggiorna le statistiche base del personaggio.
     *
     * @param nuoveStatistiche nuove statistiche da assegnare
     */
    protected void aggiornaStatisticheBase (Statistiche nuoveStatistiche) {
        if (nuoveStatistiche != null) {
            this.statisticheBase = nuoveStatistiche;
        }
    }

    /**
     * @return lista non modificabile dei modificatori temporanei attivi
     */
    public List <ModificatoreTemporaneo> getModificatoriTemporanei() {
        return Collections.unmodifiableList(modificatoriTemporanei);
    }
}
