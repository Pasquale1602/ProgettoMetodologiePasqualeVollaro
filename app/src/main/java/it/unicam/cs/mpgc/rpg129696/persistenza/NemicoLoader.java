package it.unicam.cs.mpgc.rpg129696.persistenza;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import it.unicam.cs.mpgc.rpg129696.modelli.comportamenti.ComportamentoAggressivo;
import it.unicam.cs.mpgc.rpg129696.modelli.comportamenti.ComportamentoBoss;
import it.unicam.cs.mpgc.rpg129696.modelli.comportamenti.ComportamentoDifensivo;
import it.unicam.cs.mpgc.rpg129696.modelli.comportamenti.ComportamentoDoT;
import it.unicam.cs.mpgc.rpg129696.modelli.comportamenti.ComportamentoNemico;
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
     * Carica la lista completa di nemici dal file JSON specificato.
     *
     * @param percorso il percorso del file JSON nelle risorse
     * @return lista di nemici pronti per il combattimento
     * @throws RuntimeException se il file non viene trovato o è malformato
     */
    public List<Nemico> carica(String percorso) {
        InputStream is = getClass().getResourceAsStream(percorso);

        if (is == null) {
            throw new RuntimeException("Errore: Il file JSON non è stato trovato nel percorso: " + percorso);
        }
        try (InputStreamReader reader = new InputStreamReader(is)) {
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

        TipoComportamento tipoComportamento =
                TipoComportamento.valueOf(dto.tipoComportamento.toUpperCase());

        ComportamentoNemico comportamento = creaComportamento(
                tipoComportamento,
                dto.valoreEffettoComportamento,
                dto.durataEffettoComportamento
        );

        return new Nemico(dto.nome, statistiche, dto.esperienza, comportamento, tipoComportamento);
    }

    /**
     * Crea il comportamento corretto in base al tipo specificato nel JSON.
     *
     * @param tipo il tipo di comportamento del nemico
     * @param valore valore associato al comportamento
     * @param durata durata dell'effetto del comportamento
     * @return il comportamento corretto
     */
    private ComportamentoNemico creaComportamento(TipoComportamento tipo, int valore, int durata) {
        return switch (tipo) {
            case AGGRESSIVO -> new ComportamentoAggressivo();
            case DOT        -> new ComportamentoDoT(valore, durata);
            case DIFENSIVO  -> new ComportamentoDifensivo(valore,durata);
            case BOSS       -> new ComportamentoBoss(valore, durata);
        };
    }

    /**
     * Carica solo i nemici normali dal file JSON.
     *
     * @return lista di nemici normali
     */
    public List<Nemico> caricaNemici() {
        return carica("/resources/nemici.json")
                .stream()
                .filter(n -> n.getTipoComportamento() != TipoComportamento.BOSS)
                .toList();
    }

    /**
     * Carica solo i boss dal file JSON.
     *
     * @return lista di boss
     */
    public List<Nemico> caricaBoss() {
        return carica("/resources/nemici.json")
                .stream()
                .filter(n -> n.getTipoComportamento() == TipoComportamento.BOSS)
                .toList();
    }
}
