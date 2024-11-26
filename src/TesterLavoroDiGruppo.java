import java.io.*;
import java.util.Scanner;

public class TesterLavoroDiGruppo {


    static Catalogo catalogo;
    static Carrello carrello;

    static String path;
    static File fileSer;


    public static void inizializzaVariabili(){
        catalogo = new Catalogo();
        carrello = new Carrello();
        path = "FileSer.ser";
        fileSer = creaFile();
    }


    public static void main(String[] args) {
        stampaDescrizione();
        inizializzaVariabili();
        prelevaInformazioni();

        int scelta;
        do{
            stampaOperazioni();
            scelta = chiediInt("Scegli un'operazione");
            esegui(scelta);
        } while (scelta != Operazione.EXIT.numeroOperazione);

        registraInformazioni();
        System.out.println("Programma terminato");
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
                aggiungiProdottoAlCarrello();
                break;
            case RIMUOVI_PRODOTTO_DAL_CARRELLO:
                rimuoviProdottoDalCarrello();
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


    public static void aggiungiProdottoAlCatalogo(){

    }

    public static void rimuoviProdottoDalCatalogo(){

    }

    public static void aggiungiProdottoAlCarrello(){

    }

    public static void rimuoviProdottoDalCarrello(){

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
            FileInputStream fileInputStream = new FileInputStream(fileSer);
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
            FileOutputStream fileOutputStream = new FileOutputStream(fileSer);
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