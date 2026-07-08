# ToxicRain

Gioco di ruolo a turni con interfaccia grafica JavaFX,
ambientato in un mondo post-apocalittico, dove un virus
ha infettato le persone, e sarà compito degli eroi
presenti nel gioco sconfiggere gli abomini che sono 
stati creati...

## Come eseguire il progetto

### Prerequisiti
- Java 25 (o versione LTS compatibile),
se non presente sulla macchina, Gradle lo scarica 
automaticamente tramite il toolchain resolver.
- Nessuna installazione manuale di Gradle richiesta (è incluso il wrapper)


### Istruzioni
```bash
git clone https://github.com/Pasquale1602/ProgettoMetodologiePasqualeVollaro.git
cd ProgettoMetodologiePasqualeVollaro
```

Build del progetto 
```bash
./gradlew build
```
Esecuzione (interfaccia grafica JavaFX)

```bash
./gradlew run
```
I dati statici di gioco(personaggi, nemici, oggetti, abilità) 
vengono caricati da file JSON inclusi nelle risorse.
I salvataggi delle partite vengono scritti in `app/salvataggi/` in 
formato JSON.

 È disponibile anche una modalità testuale da console (`AppConsole`),
 pensata per dimostrare che il motore di gioco è indipendente dall'interfaccia 
 grafica.

## Uso di strumenti di AI
Utilizzato Claude (Anthropic) come supporto durante 
lo sviluppo per:
- comprensione e risoluzione di errori a runtime
- supporto nella creazione dell'interfaccia grafica JavaFX
- suggerimenti per strutturare e organizzare il codice
- autocompletamento di codice semplice e ripetitivo (getter,
setter ecc...)
- supporto nel bilanciamento del gioco (combattimento, ricompense, progressione)

Comunque sia:

- ogni porzione di codice generata o proposta dall'IA è stata valutata 
e compresa personalmente prima di essere poi integrata all'interno del progetto
- l'architettura e la struttura del progetto (package, classi, interfacce) sono state decise
autonomamente
- L'IA è stata usata soprattutto come supporto per individuare e chiarire
problemi durante lo sviluppo, non come sostituto della progettazione

Per una descrizione più dettagliata di funzionalità, responsabilità
delle classi e organizzazione dei dati, consultare la Wiki
del repository GitHub.

