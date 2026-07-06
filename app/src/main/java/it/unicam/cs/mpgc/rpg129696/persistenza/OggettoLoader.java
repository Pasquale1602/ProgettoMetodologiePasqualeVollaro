package it.unicam.cs.mpgc.rpg129696.persistenza;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import it.unicam.cs.mpgc.rpg129696.modelli.oggetti.Oggetto;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Carica gli oggetti dal file JSON e li converte in oggetti {@link Oggetto}.
 */
public class OggettoLoader {

    private final Gson gson = new Gson();

    /**
     * Carica la lista di oggetti dal file JSON specificato.
     *
     * @param percorso il percorso del file JSON nelle risorse
     * @return lista di oggetti disponibili
     * @throws RuntimeException se il file non viene trovato o è malformato
     */
    public List<Oggetto> carica(String percorso) {

        InputStream is = getClass().getResourceAsStream(percorso);

        if (is == null) {
            throw new RuntimeException("Errore: Il file JSON non è stato trovato nel percorso: " + percorso);
        }

        try (InputStreamReader reader = new InputStreamReader(is)) {
            Type listType = new TypeToken<List<Oggetto>>(){}.getType();
            return gson.fromJson(reader, listType);

        } catch (Exception e) {
            throw new RuntimeException("Errore nel caricamento degli oggetti da: " + percorso);
        }
    }

    /**
     * Carica gli oggetti dal percorso di default.
     *
     * @return lista di oggetti disponibili
     */
    public List<Oggetto> caricaOggetti() {
        return carica("/resources/oggetti.json");
    }
}
