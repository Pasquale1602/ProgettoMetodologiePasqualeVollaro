package it.unicam.cs.mpgc.rpg129696.persistenza;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import it.unicam.cs.mpgc.rpg129696.modelli.abilita.Abilita;
import it.unicam.cs.mpgc.rpg129696.modelli.abilita.AbilitaFactory;
import it.unicam.cs.mpgc.rpg129696.modelli.enumerati.TipoAbilita;
import it.unicam.cs.mpgc.rpg129696.persistenza.dto.AbilitaDTO;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Carica le abilità dal file JSON e le converte in istanze di {@link Abilita}
 * tramite {@link AbilitaFactory}.
 * Restituisce una mappa indicizzata per {@link TipoAbilita} per un accesso rapido.
 */
public class AbilitaLoader {

    private final Gson gson = new Gson();

    /**
     * Carica tutte le abilità dal file JSON specificato.
     *
     * @param percorso il percorso del file JSON nelle risorse
     * @return mappa da {@link TipoAbilita} a {@link Abilita}
     * @throws RuntimeException se il file non viene trovato o è malformato
     */
    public Map<TipoAbilita, Abilita> carica(String percorso) {
        InputStream is = getClass().getResourceAsStream(percorso);

        if (is == null) {
            throw new RuntimeException("Errore: Il file json non è stato trovato nel percorso"+percorso);
        }
             try( InputStreamReader reader = new InputStreamReader(is)) {

            Type listType = new TypeToken<List<AbilitaDTO>>(){}.getType();
            List<AbilitaDTO> dtos = gson.fromJson(reader, listType);

            Map<TipoAbilita, Abilita> abilita = new HashMap<>();
            for (AbilitaDTO dto : dtos) {
                TipoAbilita tipo = TipoAbilita.valueOf(dto.tipo);
                abilita.put(tipo, AbilitaFactory.crea(dto));
            }
            return abilita;

        } catch (Exception e) {
            throw new RuntimeException("Errore nel caricamento delle abilità da: " + percorso, e);
        }
    }

    /**
     * Carica le abilità dal percorso di default.
     *
     * @return mappa da {@link TipoAbilita} a {@link Abilita}
     */
    public Map<TipoAbilita, Abilita> caricaAbilita() {
        return carica("/resources/abilita.json");
    }
}
