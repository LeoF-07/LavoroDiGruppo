public enum Operazione {

    EXIT (0, "Esci dal programma"),
    AGGIUNGI_PRODOTTO_AL_CATALOGO (1, "Aggiungi prodotto al catalogo"),
    RIMUOVI_PRODOTTO_DAL_CATALOGO (2, "Rimuovi prodotto dal catalogo"),
    AGGIUNGI_PRODOTTO_AL_CARRELLO (3, "Aggiungi prodotto al carrello"),
    RIMUOVI_PRODOTTO_DAL_CARRELLO (4, "Rimuovi prodotto dal carrello");

    int numeroOperazione;
    String descrizioneOperazione;

    Operazione(int numeroOperazione, String descrizioneOperazione){
        this.numeroOperazione = numeroOperazione;
        this.descrizioneOperazione = descrizioneOperazione;
    }

}
