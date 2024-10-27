package com.biblioteca.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    public static Connection conectar() {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection("jdbc:postgresql://localhost:5432/biblioteca", "postgres", "dbadmin");
        } catch (SQLException err) {
            System.out.println("Erro ao se conectar com o banco de dados: \n" + err.getMessage());
        } catch (Exception err) {
            System.out.println(err.getMessage());
        }

        return null;
    }
}
