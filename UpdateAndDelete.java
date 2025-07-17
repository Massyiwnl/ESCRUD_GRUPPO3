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
        System.out.print("Inserisci l'id dell'utente da aggiornare: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.print("Nuovo nome: ");
        String nuovoNome = scanner.nextLine();
        String sql = "UPDATE utenti SET nome = ? WHERE id = ?";
        try (
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setString(1, nuovoNome);
            ps.setInt(2, id);
            int rows = ps.executeUpdate();
            System.out.println(rows > 0 ? "Utente aggiornato!" : "Nessun utente trovato con questo id.");
        } catch (SQLException e) {
            System.err.println("Errore durante l'aggiornamento: " + e.getMessage());
        }
    }

    // Metodo per cancellare un utente tramite id
    private static void delete(Scanner scanner) {
        System.out.print("Inserisci l'id dell'utente da cancellare: ");
        int id = Integer.parseInt(scanner.nextLine());
        String sql = "DELETE FROM utenti WHERE id = ?";
        try (
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            System.out.println(rows > 0 ? "Utente cancellato!" : "Nessun utente trovato con questo id.");
        } catch (SQLException e) {
            System.err.println("Errore durante la cancellazione: " + e.getMessage());
        }
    }
}
