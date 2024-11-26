import java.util.HashMap;

// eccezioni sollevate dai metodi della classe Catalogo
class TroppiProdotti extends Exception {
}
class ProdottoEsistente extends Exception {
}
class ProdottoInesistente extends Exception {
}


public class Catalogo {

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
    public void eliminaProdotto(String titolo)throws ProdottoInesistente {
        for (Prodotto prodotto : prodotti.values())
            if (prodotto.getTitolo().equals(titolo))
                eliminaProdotto(prodotto.getCodice());
        throw new ProdottoInesistente();
    }


    // ricerca di un prodotto nel catalogo a partire dal codice
    public Prodotto cercaProdotto(int codice)throws ProdottoInesistente {
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
        // Secondo me devo fare un for che mi mette su tmp uno alla volta le cose castate, perche mi fa strano nche non raggiunge il return
        Prodotto[] tmp = (Prodotto[]) prodotti.values().toArray();
        return tmp;
    }
}