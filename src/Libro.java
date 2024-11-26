import java.io.Serializable;

public class Libro extends Prodotto implements Serializable {

    private String autore;
    private String editore;
    private int pagine;

    public Libro(int codice, String titolo, String descrizione, int anno, float prezzo, int quantita, String autore, String editore, int pagine) {
        super(codice, titolo, descrizione, anno, prezzo, quantita);
        this.autore = autore;
        this.editore = editore;
        this.pagine = pagine;
    }

    public String getAutore() {
        return autore;
    }

    public void setAutore(String autore) {
        this.autore = autore;
    }

    public String getEditore() {
        return editore;
    }

    public void setEditore(String editore) {
        this.editore = editore;
    }

    public int getPagine() {
        return pagine;
    }

    public void setPagine(int pagine) {
        this.pagine = pagine;
    }

}
