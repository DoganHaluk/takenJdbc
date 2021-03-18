package be.vdab;

import be.vdab.repositories.BierRepository;

import java.sql.DriverManager;
import java.sql.SQLException;

class Main {
    private static final String URL = "jdbc:mysql://localhost/bieren";
    private static final String USER = "root";
    private static final String PASSWORD = "Dorado.7";

    public static void main(String[] args) {
        try (var connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            System.out.println("Connectie geopend");
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
        }
        var repository = new BierRepository();
        try {
            System.out.print(repository.verwijderBierenMetOnbekendeAlcohol());
            System.out.println(" bieren verwijderd.");
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
        }
    }
}