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

        System.out.print("id:");
        var scanner3 = new Scanner(System.in);
        var id = scanner3.nextLong();
        var repository4 = new BrouwerRepository();
        try {
            repository4.vindEenBrouwerOpId(id)
                    .ifPresentOrElse(System.out::println,
                            () -> System.out.println("Niet gevonden"));
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
        }

        System.out.print("Min:");
        var scanner4 = new Scanner(System.in);
        var minimum = scanner4.nextDouble();
        System.out.print("Max:");
        var scanner5 = new Scanner(System.in);
        var maximum = scanner5.nextDouble();
        var repository5 = new BrouwerRepository();
        try {
            System.out.println("De omzetten tussen "+minimum+" en "+maximum+" : ");
            repository5.brouwersWaarvanOmzetLigtTussenMinEnMaxMetStoredProcedure(minimum, maximum).forEach(System.out::println);
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
        }

        var repository1 = new BierRepository();
        try {
            repository1.brouwer1GaatFailliet();
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
        }

        System.out.print("Maand:");
        var scanner6 = new Scanner(System.in);
        var maand = scanner6.nextInt();
        while (maand < 1 || maand > 12) {
            System.out.println("Verkeerd, maand:");
            maand = scanner6.nextInt();
        }
        var repository6 = new BierRepository();
        try {
            System.out.print("De bieren die voor het eerst verkocht zijn in die maand: \n");
            repository6.vindAlBierenInEenMaand(maand).forEach(System.out::println);
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
        }

        var repository7= new BrouwerRepository();
        try {
            repository7.vindBrouwersEnHunBieren().forEach(System.out::println);
        } catch (SQLException ex){
            ex.printStackTrace(System.err);
        }
    }
}
