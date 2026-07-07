package it.unicam.cs.mpgc.rpg129696.persistenza;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import it.unicam.cs.mpgc.rpg129696.modelli.enumerati.TipoOggetto;
import it.unicam.cs.mpgc.rpg129696.modelli.oggetti.Oggetto;
import it.unicam.cs.mpgc.rpg129696.persistenza.dto.OggettoDTO;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
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
     * @throws RuntimeException se il file non viene trovato o e' malformato
     */
    public List<Oggetto> carica(String percorso) {
        InputStream is = getClass().getResourceAsStream(percorso);

        if (is == null) {
            throw new RuntimeException("Errore: Il file JSON non e' stato trovato nel percorso: " + percorso);
        }

        try (InputStreamReader reader = new InputStreamReader(is)) {
            Type listType = new TypeToken<List<OggettoDTO>>(){}.getType();
            List<OggettoDTO> dtos = gson.fromJson(reader, listType);

            List<Oggetto> oggetti = new ArrayList<>();
            for (OggettoDTO dto : dtos) {
                oggetti.add(converti(dto));
            }

            return oggetti;

        } catch (Exception e) {
            throw new RuntimeException("Errore nel caricamento degli oggetti da: " + percorso, e);
        }
    }

    private Oggetto converti(OggettoDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("Il DTO dell'oggetto non puo essere null");
        }
        if (dto.tipo == null || dto.tipo.isBlank()) {
            throw new IllegalArgumentException("Il tipo dell'oggetto non puo essere vuoto");
        }

        TipoOggetto tipo = TipoOggetto.valueOf(dto.tipo.toUpperCase());

        return new Oggetto(
                dto.id,
                dto.nome,
                dto.descrizione,
                tipo,
                dto.valoreCura,
                dto.valoreAttacco,
                dto.turni
        );
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
