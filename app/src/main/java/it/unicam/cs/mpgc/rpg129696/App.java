package it.unicam.cs.mpgc.rpg129696;

import it.unicam.cs.mpgc.rpg129696.modelli.personaggio.Nemico;
import it.unicam.cs.mpgc.rpg129696.modelli.personaggio.PersonaggioGiocabile;
import it.unicam.cs.mpgc.rpg129696.persistenza.NemicoLoader;
import it.unicam.cs.mpgc.rpg129696.persistenza.PersonaggioLoader;

import java.util.List;

public class App {
    public static void main(String[] args) {

        // Test NemicoLoader
        NemicoLoader nemicoLoader = new NemicoLoader();
        List<Nemico> nemici = nemicoLoader.caricaNemici();
        List<Nemico> boss = nemicoLoader.caricaBoss();

        System.out.println("=== NEMICI CARICATI ===");
        for (Nemico n : nemici) {
            System.out.println("Nome: " + n.getNome() +
                    " | HP: " + n.getHpAttuali() +
                    " | ATK: " + n.getStatisticheAttuali().getAttacco() +
                    " | DEF: " + n.getStatisticheAttuali().getDifesa() +
                    " | XP: " + n.getRicompensaEsperienza() +
                    " | Boss: " + n.isBoss());
        }

        System.out.println("\n=== BOSS CARICATI ===");
        for (Nemico b : boss) {
            System.out.println("Nome: " + b.getNome() +
                    " | HP: " + b.getHpAttuali() +
                    " | ATK: " + b.getStatisticheAttuali().getAttacco() +
                    " | DEF: " + b.getStatisticheAttuali().getDifesa() +
                    " | XP: " + b.getRicompensaEsperienza());
        }

        // Test PersonaggioLoader
        PersonaggioLoader personaggioLoader = new PersonaggioLoader();
        List<PersonaggioGiocabile> personaggi = personaggioLoader.caricaPersonaggi();

        System.out.println("\n=== PERSONAGGI CARICATI ===");
        for (PersonaggioGiocabile p : personaggi) {
            System.out.println("Nome: " + p.getNome() +
                    " | HP: " + p.getHpAttuali() +
                    " | ATK: " + p.getStatisticheAttuali().getAttacco() +
                    " | DEF: " + p.getStatisticheAttuali().getDifesa() +
                    " | Abilita: " + p.getAbilita());
        }

        // Test modificatori
        System.out.println("\n=== TEST MODIFICATORI ===");
        PersonaggioGiocabile macellaio = personaggi.get(0);
        System.out.println("ATK Macellaio prima di Furia: " +
                macellaio.getStatisticheAttuali().getAttacco());
        macellaio.usaAbilita(nemici.get(0));
        System.out.println("ATK Macellaio dopo Furia: " +
                macellaio.getStatisticheAttuali().getAttacco());
        macellaio.aggiornaTurnoModificatori();
        macellaio.aggiornaTurnoModificatori();
        macellaio.aggiornaTurnoModificatori();
        System.out.println("ATK Macellaio dopo 3 turni (Furia scaduta): " +
                macellaio.getStatisticheAttuali().getAttacco());
    }
}
