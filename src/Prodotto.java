import java.io.Serializable;

public class Prodotto implements Cloneable, Serializable {

    private int codice;
    private String titolo;
    private String descrizione;
    private int anno;
    private float prezzo;
    private int quantita;


    public Prodotto(int codice, String titolo, String descrizione, int anno, float prezzo, int quantita) {
        this.codice = codice;
        this.titolo = titolo;
        this.descrizione = descrizione;
        this.anno = anno;
        this.prezzo = prezzo;
        this.quantita = quantita;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public int getCodice() {
        return codice;
    }

    public void setCodice(int codice) {
        this.codice = codice;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public int getAnno() {
        return anno;
    }

    public void setAnno(int anno) {
        this.anno = anno;
    }

    public float getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(float prezzo) {
        this.prezzo = prezzo;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    @Override
    public String toString() {
        return "Codice: " + codice + ", Titolo: " + titolo + ", Descrizione: " + descrizione +
                ", Anno: " + anno + ", Prezzo: " + prezzo + ", Quantità: " + quantita;
    }

}
