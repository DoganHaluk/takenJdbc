package be.vdab;

import be.vdab.repositories.BierRepository;
import be.vdab.repositories.BrouwerRepository;

import java.sql.DriverManager;
import java.sql.SQLException;

class Main {

    public static void main(String[] args) {

        /*var repository1 = new BierRepository();
        try {
            System.out.print(repository1.verwijderBierenMetOnbekendeAlcohol());
            System.out.println(" bieren verwijderd.");
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
        }*/

        var repository2 = new BrouwerRepository();
        try {
            System.out.println("De gemiddelde omzet van alle brouwers: ");
            System.out.print(repository2.toonGemiddeldeOmzetVanAlleBrouwers());
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
        }
    }
}
