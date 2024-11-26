import java.io.Serializable;

public class DVD extends Prodotto implements Serializable {

    private String regista;
    private String produttore;
    private float durata;

    public DVD(int codice, String titolo, String descrizione, int anno, float prezzo, int quantita, String regista, String produttore, float durata) {
        super(codice, titolo, descrizione, anno, prezzo, quantita);
        this.regista = regista;
        this.produttore = produttore;
        this.durata = durata;
    }

    public String getRegista() {
        return regista;
    }

    public void setRegista(String regista) {
        this.regista = regista;
    }

    public String getProduttore() {
        return produttore;
    }

    public void setProduttore(String produttore) {
        this.produttore = produttore;
    }

    public float getDurata() {
        return durata;
    }

    public void setDurata(float durata) {
        this.durata = durata;
    }

}
