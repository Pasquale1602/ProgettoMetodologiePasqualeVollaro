package it.unicam.cs.mpgc.rpg129696.persistenza;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import it.unicam.cs.mpgc.rpg129696.modelli.abilita.Abilita;
import it.unicam.cs.mpgc.rpg129696.modelli.enumerati.TipoAbilita;
import it.unicam.cs.mpgc.rpg129696.modelli.personaggio.PersonaggioGiocabile;
import it.unicam.cs.mpgc.rpg129696.modelli.personaggio.Statistiche;
import it.unicam.cs.mpgc.rpg129696.persistenza.dto.PersonaggioDTO;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Carica i personaggi giocabili dal file JSON e li converte
 * in oggetti {@link PersonaggioGiocabile}.
 */
public class PersonaggioLoader {

    private final Gson gson = new Gson();
    private final AbilitaLoader abilitaLoader;

    public PersonaggioLoader(AbilitaLoader abilitaLoader) {
        this.abilitaLoader = abilitaLoader;
    }

    /**
     * Carica la lista di personaggi dal file JSON specificato.
     *
     * @param percorso il percorso del file JSON nelle risorse
     * @return lista di personaggi giocabili pronti per la selezione
     * @throws RuntimeException se il file non viene trovato o è malformato
     */
    public List<PersonaggioGiocabile> carica(String percorso) {
        InputStream is = getClass().getResourceAsStream(percorso);

        if (is == null) {
            throw new RuntimeException("Errore: Il file JSON non è stato trovato nel percorso: " + percorso);
        }

        try (InputStreamReader reader = new InputStreamReader(is)) {
            Type listType = new TypeToken<List<PersonaggioDTO>>(){}.getType();
            List<PersonaggioDTO> dtos = gson.fromJson(reader, listType);

            Map<TipoAbilita, Abilita> abilita = abilitaLoader.caricaAbilita();

            List<PersonaggioGiocabile> personaggi = new ArrayList<>();
            for (PersonaggioDTO dto : dtos) {
                personaggi.add(converti(dto, abilita));
            }
            return personaggi;

        } catch (Exception e) {
            throw new RuntimeException("Errore nel caricamento dei personaggi da: " + percorso, e);
        }
    }

    /**
     * Converte un {@link PersonaggioDTO} in un oggetto {@link PersonaggioGiocabile}.
     *
     * @param dto il DTO da convertire
     * @param abilita la mappa delle abilità disponibili
     * @return il personaggio pronto per il gioco
     */
    private PersonaggioGiocabile converti(PersonaggioDTO dto, Map<TipoAbilita, Abilita> abilita) {
        Statistiche statistiche = new Statistiche(
                dto.statistiche
        );
        TipoAbilita tipoAbilita = TipoAbilita.valueOf(dto.tipoAbilita);
        Abilita abilitaPersonaggio = abilita.get(tipoAbilita);
        return new PersonaggioGiocabile(dto.nome, statistiche, abilitaPersonaggio);
    }

    /**
     * Carica i personaggi dal percorso di default.
     *
     * @return lista di personaggi giocabili
     */
    public List<PersonaggioGiocabile> caricaPersonaggi() {
        return carica("/resources/personaggi.json");
    }
}