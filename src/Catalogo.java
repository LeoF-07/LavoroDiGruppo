import java.io.Serializable;
import java.util.HashMap;

// eccezioni sollevate dai metodi della classe Catalogo
class TroppiProdotti extends Exception {
    private String msg = "Hai ragggiunto il massimo dei prodotti!";

    public String getMsg() {
        return msg;
    }
}

class ProdottoEsistente extends Exception {
    private String msg = "Prodotto già esistente!";

    public String getMsg() {
        return msg;
    }
}

class ProdottoInesistente extends Exception {
    private String msg = "Prodotto inesistente!";

    public String getMsg() {
        return msg;
    }
}


public class Catalogo implements Serializable {

    final static int NUMERO_MASSIMO_PRODOTTI = 100000;

    HashMap<Integer, Prodotto> prodotti; // tabella hash di prodotti


    // costruttore
    public Catalogo() {
        prodotti = new HashMap<Integer, Prodotto>(NUMERO_MASSIMO_PRODOTTI);
    }


    // aggiunta di un nuovo prodotto al catalogo
    public void aggiungiProdotto(Prodotto prodotto)
            throws TroppiProdotti, ProdottoEsistente {
        if (prodotti.size() >= NUMERO_MASSIMO_PRODOTTI)
            throw new TroppiProdotti();
        if (prodotti.containsKey(prodotto.getCodice()))
            throw new ProdottoEsistente();
        try {
            prodotti.put(prodotto.getCodice(), (Prodotto) prodotto.clone());
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }


    // eliminazione di un prodotto dal catalogo a partire dal codice
    public void eliminaProdotto(int codice) throws ProdottoInesistente {
        if (prodotti.isEmpty() || !prodotti.containsKey(codice))
            throw new ProdottoInesistente();
        prodotti.remove(codice);
    }


    // eliminazione di un prodotto dal catalogo a partire dal titolo
    public void eliminaProdotto(String titolo) throws ProdottoInesistente {
        for (Prodotto prodotto : prodotti.values())
            if (prodotto.getTitolo().equals(titolo))
                eliminaProdotto(prodotto.getCodice());
        throw new ProdottoInesistente();
    }


    // ricerca di un prodotto nel catalogo a partire dal codice
    public Prodotto cercaProdotto(int codice) throws ProdottoInesistente {
        if (prodotti.isEmpty() || !prodotti.containsKey(codice))
            throw new ProdottoInesistente();
        try {
            return (Prodotto) prodotti.get(codice).clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }


    // ricerca di un prodotto nel catalogo a partire dal titolo
    public Prodotto cercaProdotto(String titolo)throws ProdottoInesistente {
        for (Prodotto prodotto : prodotti.values())
            if (prodotto.getTitolo().equals(titolo)) {
                try {
                    return (Prodotto) prodotto.clone();
                } catch (CloneNotSupportedException e) {
                    throw new RuntimeException(e);
                }
            }
        throw new ProdottoInesistente();
    }


    // restituzione di un vettore contenente i prodotti del catalogo
    public Prodotto[] elencaProdotti() {
        Prodotto[] tmp = new Prodotto[prodotti.size()];
        for(int i = 0; i < prodotti.size(); i++){
            tmp[i] = (Prodotto) prodotti.values().toArray()[i];
        }

        return tmp;
    }

    @Override
    public String toString(){
        Prodotto[] prodotti = elencaProdotti();

        String s = "Catalogo:\n";
        if(prodotti.length == 0) return s + "Vuoto";
        for (int i = 0; i < prodotti.length; i++){
            s += (i + 1) + ") " + prodotti[i].toString() + "\n";
        }

        return s;
    }

}