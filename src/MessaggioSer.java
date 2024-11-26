public enum MessaggioSer {

    FILE_CREATO("File per la serializzazione creato e pronto all'uso"),
    FILE_VUOTO("Il file per la serializzazione è vuoto"),
    FILE_GIA_ESISTENTE("File per la serializzazione già esistente e pronto all'uso"),
    PRELEVAMENTO_OK("Informazioni prelevate con successo"),
    PRELEVAMENTO_ERRORE("Il prelevamento delle informazioni non è andato a buon fine"),
    REGISTRAZIONE_OK("La serializzazione è andata a buon fine"),
    REGISTRAZIONE_ERRORE("La serializzazione non è andata a buon fine"),
    ERRORE("Errore");

    public String messaggio;

    private MessaggioSer(String messaggio){
        this.messaggio = messaggio;
    }

}