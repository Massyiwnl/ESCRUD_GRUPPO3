package com.example;

import java.util.Scanner;

public class Application    {

    public static void main(String[] args) {
        UtenteDatabase utenteDatabase = new UtenteDatabase();
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("\nMenu Utenti JDBC");
                System.out.println("1. Inserisci nuovo utente");
                System.out.println("2. Visualizza tutti gli utenti");
                System.out.println("3. Aggiorna nome utente");
                System.out.println("4. Cancella utente");
                System.out.println("0. Esci");
                System.out.print("Scegli un'opzione: ");
                int scelta = Integer.parseInt(scanner.nextLine());

                switch (scelta) {
                    case 1:
                            System.out.println("Inserisci il nome dell utente: ");
                            String nome = scanner.nextLine();
                            System.out.println("Inserisci l email:");
                            String email = scanner.nextLine();
                            Utente utente = new Utente(0, nome, email);
                            utenteDatabase.insert(utente);
                        break;
                    case 2:
                            System.out.println("Lista utenti: ");
                            utenteDatabase.read();

                        break;
                     case 3:
                        System.out.println("Inserisci il nuovo nome dell'utente: ");
                        String nome2 = scanner.nextLine();
                        System.out.println("Inserisci la nuova email: ");
                        String email2 = scanner.nextLine();
                        System.out.println("Inserisci l'ID dell'utente da aggiornare: ");
                       
                        String inputIdUpdate = scanner.nextLine();
                        int idUpdate;
                        try {
                            idUpdate = Integer.parseInt(inputIdUpdate);
                        } catch (NumberFormatException e) {
                            System.out.println("ID non valido. Inserisci un numero.");
                            break; 
                        }
                        Utente utente2 = new Utente(idUpdate, nome2, email2);
                        utenteDatabase.update(utente2);
                        System.out.println("Utente aggiornato con successo!"); 
                        break;
                     case 4:
                        System.out.println("Inserisci l'ID dell'utente da eliminare: ");
                        
                        String id = scanner.nextLine();
                        int idDelete;
                        try {
                            idDelete = Integer.parseInt(id);
                        } catch (NumberFormatException e) {
                            System.out.println("ID non valido. Inserisci un numero.");
                            break; 
                        }
                        utenteDatabase.delete(idDelete);
                        System.out.println("Utente eliminato con successo!"); 
                        break;
                    case 0:
                        System.out.println("Uscita...");
                        return;
                    default:
                        System.out.println("Opzione non valida. Riprova.");
                }
            }
        }
    }

}