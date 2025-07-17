package com.example;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UtenteDatabase {

public void insert(Utente utente) {
    try {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/world","root","root");

        String insert = "INSERT INTO utenti (nome,email) VALUES(?,?)";

        PreparedStatement preparedStatement = conn.prepareStatement(insert);

        preparedStatement.setString(1, utente.getNome());
        preparedStatement.setString(2, utente.getEmail());
        preparedStatement.executeUpdate();


    }catch(SQLException e ) {
        e.printStackTrace();
    }
  }

  public List<Utente> read() {

        List<Utente> listaUtenti = new ArrayList<>();

    try {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/world","root","root");
        String read ="SELECT id, nome, email FROM utenti";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(read);
        
        while(rs.next()) {
            int id = rs.getInt("id"); 
            String nome = rs.getString("nome");
            String email = rs.getString("email");
            Utente utente = new Utente(id, nome, email);
            listaUtenti.add(utente);
            System.out.println(utente);
        }
        return listaUtenti;
    }catch(SQLException e) {
        e.printStackTrace();
        
    }
    return null;
  }

  // Metodo per aggiornare il nome di un utente dato l'id
    public void update(Utente utente) {

        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/world" ,"root","root");

            String update = "UPDATE `utenti` SET `nome` = ?, `email` =? WHERE `id` = ?";

            PreparedStatement preparedStatement = conn.prepareStatement(update);

            preparedStatement.setString(1, utente.getNome());
            preparedStatement.setString(2,utente.getEmail());
            preparedStatement.setInt(3, utente.getId());
            preparedStatement.executeUpdate();

        }catch(SQLException e) {
            e.printStackTrace();
        }
    }

    // Metodo per cancellare un utente tramite id
    public void delete(int id) {

        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/world","root","root");
            // Query SQL per cancellare un utente tramite id
            String sql = "DELETE FROM utenti WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id); // Imposta il parametro (id dell'utente da cancellare)
            ps.executeUpdate();


        }catch(SQLException e) {
            e.printStackTrace();
        }
    }

}



class Utente {
    
    private int id;
    private String nome;
    private String email;

    public Utente(int id, String nome, String email) {
        this.id = id;
        this.nome = nome;
        this.email = email;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    @Override
    public String toString() {
        return "Utenti [id=" + id + ", nome=" + nome + ", email=" + email + "]";
    }

    
}