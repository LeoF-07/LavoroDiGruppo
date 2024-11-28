import java.io.Serializable;

public class CD extends Prodotto implements Serializable {

    private String autore;
    private String esecutore;
    private float durata;

    public CD(int codice, String titolo, String descrizione, int anno, float prezzo, int quantita, String autore, String esecutore, float durata) {
        super(codice, titolo, descrizione, anno, prezzo, quantita);
        this.autore = autore;
        this.esecutore = esecutore;
        this.durata = durata;
    }

    public String getAutore() {
        return autore;
    }

    public void setAutore(String autore) {
        this.autore = autore;
    }

    public String getEsecutore() {
        return esecutore;
    }

    public void setEsecutore(String esecutore) {
        this.esecutore = esecutore;
    }

    public float getDurata() {
        return durata;
    }

    public void setDurata(float durata) {
        this.durata = durata;
    }

    @Override
    public String toString() {
        return "CD:\n" + super.toString() + ", Autore: " + autore + ", Esecutore: " + esecutore + ", Durata: " + durata;
    }

}
