import java.io.*;
import java.util.Scanner;

public class TesterLavoroDiGruppo {


    static Catalogo catalogo;
    static Carrello carrello;

    static String path;
    static File file;


    public static void inizializzaVariabili(){
        catalogo = new Catalogo();
        carrello = new Carrello();
        path = "FileSer.txt";
        file = creaFile();
    }


    public static void popolaCatalogo(){
        try {
            catalogo.aggiungiProdotto(new Libro(1, "Geronimo Stilton", "Libro1", 2008, 15.50f, 10, "Elisabetta Dami", "Mondadori", 150));
            catalogo.aggiungiProdotto(new CD(2, "Zecchino d'Oro", "CD1", 2005, 10, 5, "Gianfranco Fasano", "Radio", 5.15f));
            catalogo.aggiungiProdotto(new DVD(3, "Spider-Man", "CD1", 2002, 20.19f, 38, "Sam Raimi", "Laura Ziskin", 121));
        } catch (TroppiProdotti e) {
            System.out.println(e.getMsg());
        } catch (ProdottoEsistente e) {
            System.out.println(e.getMsg());
        }
    }


    public static void main(String[] args) {
        stampaDescrizione();
        inizializzaVariabili();

        //popolaCatalogo(); //Ho usato la serializzazione, quindi i prodotti aggiunti nel catalogo su questo metodo sono già salvati

        prelevaInformazioni();

        int scelta;
        do{
            stampaOperazioni();
            scelta = chiediInt("Scegli un'operazione: ");
            esegui(scelta);
            System.out.println();
        } while (scelta != Operazione.EXIT.numeroOperazione);

        registraInformazioni();
        System.out.println("\nProgramma terminato, arrivederci!");
    }

    public static void esegui(int scelta){
        Operazione operazione;

        try{
            operazione = Operazione.values()[scelta];
        } catch (ArrayIndexOutOfBoundsException ex){
            System.out.println("Opzione inesistente");
            return;
        }

        switch (operazione){
            case EXIT:
                break;
            case AGGIUNGI_PRODOTTO_AL_CATALOGO:
                aggiungiProdottoAlCatalogo();
                break;
            case RIMUOVI_PRODOTTO_DAL_CATALOGO:
                rimuoviProdottoDalCatalogo();
                break;
            case AGGIUNGI_PRODOTTO_AL_CARRELLO:
                System.out.println(catalogo.toString());
                String titolo = chiediStringa("Inserisci il titolo del prodotto da aggiungere al carrello: ");
                int quantità = chiediInt("Inserisci la quantità di prodotti da aggiungere al carrello: ");
                aggiungiProdottoAlCarrello(titolo, quantità);
                break;
            case RIMUOVI_PRODOTTO_DAL_CARRELLO:
                rimuoviProdottoDalCarrello();
                break;
            case STAMPA_CATALOGO:
                System.out.println(catalogo.toString());
                break;
            case STAMPA_CARRELLO:
                System.out.println(carrello.toString());
                break;
            case CALCOLO_IMPORTO:
                System.out.println("Importo: " + carrello.calcolaImporto());
                break;
            case CERCA_PRODOTTO:
                int codice = chiediInt("Inserisci il codice del prodotto: ");
                cercaProdotto(codice);
                break;
        }
    }

    public static void stampaDescrizione(){
        System.out.println("Programma per la gestione di un sito web che commercializza libri, CD audio e film in DVD");
    }

    public static void stampaOperazioni(){
        System.out.println("Operazioni:");
        for (Operazione operazione : Operazione.values()) System.out.println(operazione.numeroOperazione + ") " + operazione.descrizioneOperazione);
    }


    public static void stampaTipiProdotti(){
        System.out.printf("Tipi prodotto: ");
        for (TipoProdotto tipoProdotto : TipoProdotto.values()) System.out.println(tipoProdotto.numeroTipo + ") " + tipoProdotto.descrizioneTipo);
    }


    public static TipoProdotto chiediTipoProdotto(String messaggio){
        stampaTipiProdotti();
        int scelta = chiediInt(messaggio);

        TipoProdotto tipoProdotto;
        try{
            tipoProdotto = TipoProdotto.values()[scelta];
            return tipoProdotto;
        } catch (ArrayIndexOutOfBoundsException ex){
            System.out.println("Tipo di prodotto inesistente");
        }

        return null;
    }


    public static Prodotto creaNuovoProdotto(){
        TipoProdotto tipoProdotto = chiediTipoProdotto("Scegli il tipo di prodotto: ");
        if(tipoProdotto == null) return null;

        int codice = chiediInt("Inserisci il codice: ");
        String titolo = chiediStringa("Inserisci il titolo: ");
        String descrizione = chiediStringa("Inserisci la descrizione: ");
        int anno = chiediInt("Inserisci l'anno: ");
        float prezzo = chiediFloat("Inserisci il prezzo: ");
        int quantita = chiediInt("Inserisci la quantità: ");

        String autore;
        float durata;

        switch(tipoProdotto){
            case LIBRO:
                autore = chiediStringa("Inserisci l'autore: ");
                String editore = chiediStringa("Inserisci l'editore: ");
                int pagine = chiediInt("Inserisci le pagine: ");

                Libro libro = new Libro(codice, titolo, descrizione, anno, prezzo, quantita, autore, editore, pagine);
                return libro;

            case CD:
                autore = chiediStringa("Inserisci l'autore: ");
                String esecutore = chiediStringa("Inserisci l'esecutore: ");
                durata = chiediFloat("Inserisci la durata: ");

                CD cd = new CD(codice, titolo, descrizione, anno, prezzo, quantita, autore, esecutore, durata);
                return cd;

            case DVD:
                String regista = chiediStringa("Inserisci il regista: ");
                String produttore = chiediStringa("Inserisci il produttore: ");
                durata = chiediFloat("Inserisci la durata: ");

                DVD dvd = new DVD(codice, titolo, descrizione, anno, prezzo, quantita, regista, produttore, durata);
                return dvd;
        }

        return null;
    }


    public static void aggiungiProdottoAlCatalogo(){
        Prodotto prodotto = creaNuovoProdotto();
        if(prodotto == null) {
            System.out.println("La creazione del prodotto non è andata a buon fine");
            return;
        }

        try {
            catalogo.aggiungiProdotto((Prodotto) prodotto.clone());
        } catch (TroppiProdotti e) {
            System.out.println(e.getMsg());
        } catch (ProdottoEsistente e) {
            System.out.println(e.getMsg());
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void rimuoviProdottoDalCatalogo(){
        int codice = chiediInt("Inserisci il codice: ");
        try {
            catalogo.eliminaProdotto(codice);
        } catch (ProdottoInesistente e) {
            System.out.println(e.getMsg());
        }
    }

    public static void aggiungiProdottoAlCarrello(String titolo, int quantità){
        try {
            Prodotto prodotto = (Prodotto) catalogo.cercaProdotto(titolo).clone();
            if(prodotto.getQuantita() >= quantità) {
                prodotto.setQuantita(quantità);
                carrello.aggiungiProdotto(prodotto);
            }
            else System.out.println("Disponibilità insufficiente");
        } catch (ProdottoInesistente e) {
            System.out.println(e.getMsg());
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void rimuoviProdottoDalCarrello(){
        int codice = chiediInt("Inserisci il codice: ");
        carrello.eliminaProdotto(codice);
    }

    public static void cercaProdotto(int codice){
        try {
            Prodotto prodotto = catalogo.cercaProdotto(codice);
            if(prodotto != null) {
                System.out.println("Prodotto trovato!");
                System.out.println(prodotto.toString());
            }
        } catch (ProdottoInesistente e) {
            System.out.println(e.getMsg());
        }
    }


    public static int chiediInt(String messaggio){
        System.out.print(messaggio);
        return new Scanner(System.in).nextInt();
    }

    public static String chiediStringa(String messaggio){
        System.out.print(messaggio);
        return new Scanner(System.in).nextLine();
    }

    public static float chiediFloat(String messaggio){
        System.out.print(messaggio);
        return new Scanner(System.in).nextFloat();
    }



    public static File creaFile(){
        File fileSer = new File(path);
        try {
            if(fileSer.createNewFile()){
                System.out.println(MessaggioSer.FILE_CREATO.messaggio);
            }else{
                System.out.println(MessaggioSer.FILE_GIA_ESISTENTE.messaggio);
            }
        } catch (IOException ex) {
            System.out.println(MessaggioSer.ERRORE.messaggio);
        }

        return fileSer;
    }


    public static void prelevaInformazioni(){
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            try {
                catalogo = (Catalogo) objectInputStream.readObject();
                carrello = (Carrello) objectInputStream.readObject();
                objectInputStream.close();

                System.out.println(MessaggioSer.PRELEVAMENTO_OK.messaggio);
            } catch (ClassNotFoundException ex) {
                System.out.println(MessaggioSer.PRELEVAMENTO_ERRORE.messaggio);
            }
        } catch (IOException ioe) {
            System.out.println(MessaggioSer.FILE_VUOTO.messaggio);
        }

        System.out.println();
    }

    public static void registraInformazioni(){
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(catalogo);
            objectOutputStream.writeObject(carrello);
            objectOutputStream.flush();
            objectOutputStream.close();

            System.out.println(MessaggioSer.REGISTRAZIONE_OK.messaggio);
        } catch (IOException ioe) {
            System.out.println(MessaggioSer.REGISTRAZIONE_ERRORE.messaggio);
        }
    }

}