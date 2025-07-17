import java.sql.*;
import java.util.Scanner;

public class UpdateAndDelete    {
    // Parametri di connessione:
    private static final String URL = "jdbc:mysql://localhost:3306/world";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    public static void main(String[] args) {
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
                        //insert(scanner);
                        break;
                    case 2:
                        //read();
                        break;
                    case 3:
                        update(scanner);
                        break;
                    case 4:
                        delete(scanner);
                        break;
                    case 0:
                        System.out.println("Uscita...");
                        return;
                    default:
                        System.out.println("Opzione non valida.");
                }
            }
        }
    }

    // Metodo per aggiornare il nome di un utente dato l'id
    private static void update(Scanner scanner) {
        // Richiesta all'utente dell'id dell'utente da aggiornare
        System.out.print("Inserisci l'id dell'utente da aggiornare: ");
        int id = Integer.parseInt(scanner.nextLine()); // Lettura e conversione dell'id inserito

        // Richiesta all'utente del nuovo nome
        System.out.print("Nuovo nome: ");
        String nuovoNome = scanner.nextLine(); // Lettura del nuovo nome inserito

        // Query SQL per aggiornare il nome dato un id specifico
        String sql = "UPDATE utenti SET nome = ? WHERE id = ?";

        // Apertura automatica della connessione e del PreparedStatement con try-with-resources
        try (
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); // Connessione al database
            PreparedStatement ps = conn.prepareStatement(sql)                   // Creazione del PreparedStatement con la query
        ) {
            ps.setString(1, nuovoNome); // Imposta il primo parametro (nuovo nome)
            ps.setInt(2, id);           // Imposta il secondo parametro (id dell'utente)
            int rows = ps.executeUpdate(); // Esecuzione della query di aggiornamento (ritorna il numero di righe modificate)

            // Messaggio di conferma all'utente, a seconda del risultato
            System.out.println(rows > 0 ? "Utente aggiornato!" : "Nessun utente trovato con questo id.");
        } catch (SQLException e) {
            // Gestione di eventuali errori SQL
            System.err.println("Errore durante l'aggiornamento: " + e.getMessage());
        }
    }

    // Metodo per cancellare un utente tramite id
    private static void delete(Scanner scanner) {
        // Richiesta all'utente dell'id dell'utente da cancellare
        System.out.print("Inserisci l'id dell'utente da cancellare: ");
        int id = Integer.parseInt(scanner.nextLine()); // Lettura e conversione dell'id inserito

        // Query SQL per cancellare un utente tramite id
        String sql = "DELETE FROM utenti WHERE id = ?";

        // Apertura automatica della connessione e del PreparedStatement con try-with-resources
        try (
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); // Connessione al database
            PreparedStatement ps = conn.prepareStatement(sql)                   // Creazione del PreparedStatement con la query
        ) {
            ps.setInt(1, id); // Imposta il parametro (id dell'utente da cancellare)
            int rows = ps.executeUpdate();  // Esecuzione della query di cancellazione (ritorna il numero di righe eliminate)

            // Messaggio di conferma all'utente, a seconda del risultato
            System.out.println(rows > 0 ? "Utente cancellato!" : "Nessun utente trovato con questo id.");
        } catch (SQLException e) {
            // Gestione di eventuali errori SQL
            System.err.println("Errore durante la cancellazione: " + e.getMessage());
        }
    }
}
