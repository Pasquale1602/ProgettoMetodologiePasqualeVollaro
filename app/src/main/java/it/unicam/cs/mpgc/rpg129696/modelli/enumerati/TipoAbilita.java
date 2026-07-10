package it.unicam.cs.mpgc.rpg129696.modelli.enumerati;
    /**
     * Definisce i tipi di abilità disponibili nel gioco.
     * Ogni valore corrisponde a un'abilità specifica di una classe di personaggio
     * e viene utilizzato da {@code CreatoreAbilita} per creare l'istanza corretta.
     */
    public enum TipoAbilita {

        /** Aumenta l'attacco e riduce la difesa per alcuni turni. Abilità del Macellaio. */
        FURIA,

        /** Cura il personaggio e aumenta la difesa per alcuni turni. Abilità del Crociato. */
        CHIAMATA_ALLE_ARMI,

        /** Applica veleno al nemico per alcuni turni. Abilità del Contaminatore. */
        VAPORI_SOFFOCANTI,

        /** Infligge danno critico istantaneo al nemico. Abilità del Cercatore di Cadaveri. */
        COLPO_ALLE_SPALLE
    }

