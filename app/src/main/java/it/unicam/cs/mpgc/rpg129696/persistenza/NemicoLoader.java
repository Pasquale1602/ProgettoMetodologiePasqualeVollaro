package it.unicam.cs.mpgc.rpg129696.persistenza;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import it.unicam.cs.mpgc.rpg129696.controlli.ComportamentoNemico;
import it.unicam.cs.mpgc.rpg129696.controlli.ComportamentoAggressivo;
import it.unicam.cs.mpgc.rpg129696.controlli.ComportamentoDoT;
import it.unicam.cs.mpgc.rpg129696.controlli.ComportamentoDifensivo;
import it.unicam.cs.mpgc.rpg129696.controlli.ComportamentoBoss;
import it.unicam.cs.mpgc.rpg129696.modelli.enumerati.TipoComportamento;
import it.unicam.cs.mpgc.rpg129696.modelli.personaggio.Nemico;
import it.unicam.cs.mpgc.rpg129696.modelli.personaggio.Statistiche;
import it.unicam.cs.mpgc.rpg129696.persistenza.dto.NemicoDTO;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Carica i nemici dal file JSON e li converte in oggetti {@link Nemico}.
 */
public class NemicoLoader {

    private final Gson gson = new Gson();

    /**
     * Carica la lista di nemici dal file JSON specificato.
     *
     * @param percorso il percorso del file JSON nelle risorse
     * @return lista di nemici pronti per il combattimento
     * @throws RuntimeException se il file non viene trovato o è malformato
     */
    public List<Nemico> carica(String percorso) {
        try (InputStream is = getClass().getResourceAsStream(percorso);
             InputStreamReader reader = new InputStreamReader(is)) {

            Type listType = new TypeToken<List<NemicoDTO>>(){}.getType();
            List<NemicoDTO> dtos = gson.fromJson(reader, listType);

            List<Nemico> nemici = new ArrayList<>();
            for (NemicoDTO dto : dtos) {
                nemici.add(converti(dto));
            }
            return nemici;

        } catch (Exception e) {
            throw new RuntimeException("Errore nel caricamento dei nemici da: " + percorso, e);
        }
    }

    /**
     * Converte un {@link NemicoDTO} in un oggetto {@link Nemico}.
     *
     * @param dto il DTO da convertire
     * @return il nemico pronto per il combattimento
     */
    private Nemico converti(NemicoDTO dto) {
        Statistiche statistiche = new Statistiche(dto.attaccoBase, dto.difesaBase, dto.hpBase);
        ComportamentoNemico comportamento = creaComportamento(dto.tipoComportamento);
        return new Nemico(dto.nome, statistiche, dto.xp, comportamento);
    }

    /**
     * Crea il comportamento corretto in base al tipo specificato nel JSON.
     *
     * @param tipo il tipo di comportamento come stringa
     * @return il comportamento corretto
     */
    private ComportamentoNemico creaComportamento(String tipo) {
        return switch (TipoComportamento.valueOf(tipo)) {
            case AGGRESSIVO -> new ComportamentoAggressivo();
            case DOT        -> new ComportamentoDoT();
            case DIFENSIVO  -> new ComportamentoDifensivo();
            case BOSS       -> new ComportamentoBoss();
        };
    }

    /**
     * Carica solo i nemici normali (non boss).
     *
     * @return lista di nemici normali
     */
    public List<Nemico> caricaNemici() {
        return carica("/resources/nemici.json");
    }

    /**
     * Carica solo i boss.
     *
     * @return lista di boss
     */
    public List<Nemico> caricaBoss() {
        return carica("/resources/boss.json");
    }
}
