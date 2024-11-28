public enum Operazione {

    EXIT (0, "Esci dal programma"),
    AGGIUNGI_PRODOTTO_AL_CATALOGO (1, "Aggiungi prodotto al catalogo"),
    RIMUOVI_PRODOTTO_DAL_CATALOGO (2, "Rimuovi prodotto dal catalogo"),
    AGGIUNGI_PRODOTTO_AL_CARRELLO (3, "Aggiungi prodotto al carrello"),
    RIMUOVI_PRODOTTO_DAL_CARRELLO (4, "Rimuovi prodotto dal carrello"),
    STAMPA_CATALOGO (5, "Stampa il catalogo"),
    STAMPA_CARRELLO(6, "Stampa il carrello"),
    CALCOLO_IMPORTO(7, "Calcola l'importo del carrello"),
    CERCA_PRODOTTO(8, "Cerca un prodotto a tua scelta");

    int numeroOperazione;
    String descrizioneOperazione;

    Operazione(int numeroOperazione, String descrizioneOperazione){
        this.numeroOperazione = numeroOperazione;
        this.descrizioneOperazione = descrizioneOperazione;
    }

}
