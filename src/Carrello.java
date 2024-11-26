import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;

public class Carrello implements Serializable {

    LinkedList<Prodotto> prodotti; // lista di prodotti


    // costruttore
    public Carrello() {
        prodotti = new LinkedList<Prodotto>();
    }

    // aggiunta di un nuovo prodotto al carrello
    public void aggiungiProdotto(Prodotto prodotto) {
        try {
            prodotti.add((Prodotto) prodotto.clone());
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    // eliminazione di un prodotto dal carrello
    public void eliminaProdotto(int codice) {
        Iterator<Prodotto> i = prodotti.iterator();
        Prodotto prodotto;
        while (i.hasNext()) {
            prodotto = i.next();
            if (prodotto.getCodice() == codice)
                i.remove();
        }
    }

    // restituzione di un vettore contenente i prodotti del carrello
    public Prodotto[] elencaProdotti() {
        Prodotto[] tmp = (Prodotto[]) prodotti.toArray();
        return tmp;
    }

    // calcolo del costo totale dei prodotti del carrello
    public double calcolaImporto() {
        Iterator<Prodotto> i = prodotti.iterator();
        double totale = 0.0;
        Prodotto prodotto;
        while (i.hasNext()) {
            prodotto = i.next();
            totale += prodotto.getPrezzo()*prodotto.getQuantita();
        }
        return totale;
    }
}
