package be.vdab;

import be.vdab.repositories.BierRepository;
import be.vdab.repositories.BrouwerRepository;

import java.sql.SQLException;
import java.util.Scanner;

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
            System.out.print(repository2.toonGemiddeldeOmzetVanAlleBrouwers()+"\n");
            repository2.brouwersOmzetHebbenDieHogerDanGemiddelde().forEach(System.out::println);
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
        }

        System.out.print("Min:");
        var scanner1 = new Scanner(System.in);
        var min = scanner1.nextDouble();
        System.out.print("Max:");
        var scanner2 = new Scanner(System.in);
        var max = scanner2.nextDouble();
        var repository3 = new BrouwerRepository();
        try {
            System.out.println("De omzetten tussen "+min+" en "+max+" : ");
            repository3.brouwersWaarvanOmzetLigtTussenMinimumEnMaximum(min, max).forEach(System.out::println);
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
        }
    }
}
