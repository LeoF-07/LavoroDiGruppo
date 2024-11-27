public enum TipoProdotto {

    LIBRO (0, "Libro"),
    CD (1, "CD"),
    DVD (2, "DVD");

    int numeroTipo;
    String descrizioneTipo;

    TipoProdotto(int numeroTipo, String descrizioneTipo){
        this.numeroTipo = numeroTipo;
        this.descrizioneTipo = descrizioneTipo;
    }

}
