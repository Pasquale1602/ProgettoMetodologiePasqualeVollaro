package it.unicam.cs.mpgc.rpg129696.view;

import java.util.function.Consumer;

/**
 * Implementazione grafica di {@link InterfacciaUtente}.
 *
 * I messaggi prodotti dal gioco (attacchi, danni, vittoria, sconfitta...)
 * Vengono inoltrati a un callback fornito dalla GUI, che li mostra
 * in un log testuale a schermo.
 */
public class GuiInterfacciaUtente implements InterfacciaUtente {

    private final Consumer<String> callbackMessaggio;

    public GuiInterfacciaUtente(Consumer<String> callbackMessaggio) {
        this.callbackMessaggio = callbackMessaggio;
    }

    @Override
    public void mostraMessaggio(String messaggio) {
        if (callbackMessaggio != null) {
            callbackMessaggio.accept(messaggio);
        }
    }
}
