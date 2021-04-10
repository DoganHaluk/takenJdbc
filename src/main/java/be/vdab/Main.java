package be.vdab;

import be.vdab.repositories.BierRepository;
import be.vdab.repositories.BrouwerRepository;
import be.vdab.repositories.SoortRepository;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.LinkedHashSet;
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
            System.out.print(repository2.toonGemiddeldeOmzetVanAlleBrouwers() + "\n");
            repository2.brouwersOmzetHebbenDieHogerDanGemiddelde().forEach(System.out::println);
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
        }

        System.out.print("Minimum omzet: ");
        var scanner1 = new Scanner(System.in);
        var min = scanner1.nextDouble();
        System.out.print("Maximum omzet: ");
        var scanner2 = new Scanner(System.in);
        var max = scanner2.nextDouble();
        var repository3 = new BrouwerRepository();
        try {
            System.out.println("De omzetten tussen " + min + " en " + max + " : ");
            repository3.brouwersWaarvanOmzetLigtTussenMinimumEnMaximum(min, max).forEach(System.out::println);
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
        }

        System.out.print("Brouwer id: ");
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

        System.out.print("Min omzet:");
        var scanner4 = new Scanner(System.in);
        var minimum = scanner4.nextDouble();
        System.out.print("Max omzet:");
        var scanner5 = new Scanner(System.in);
        var maximum = scanner5.nextDouble();
        var repository5 = new BrouwerRepository();
        try {
            System.out.println("De omzetten tussen " + minimum + " en " + maximum + " : ");
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

        System.out.print("Maand: ");
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

        var repository7 = new BrouwerRepository();
        try {
            repository7.vindBrouwersEnHunBieren().forEach(System.out::println);
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
        }

        System.out.print("Naam van een bier soort: ");
        var scanner7 = new Scanner(System.in);
        var soort = scanner7.nextLine();
        var repository8 = new SoortRepository();
        try {
            var namen = repository8.vindBierenVanSoort(soort);
            if (namen.isEmpty()) {
                System.out.println("Geen bieren gevonden");
            } else {
                namen.forEach(System.out::println);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
        }

        var brouwerIds = new LinkedHashSet<Long>();
        var scanner8 = new Scanner(System.in);
        System.out.print("Brouwer id (0 om te stoppen): ");
        for (long brouwerId; (brouwerId = scanner8.nextLong()) != 0; ) {
            if (brouwerId < 0) {
                System.out.print("Nummer moet positief zijn, probeer opnieuw:");
            } else {
                if (!brouwerIds.add(brouwerId)) {
                    System.out.print(brouwerId + " reeds getypt, probeer opnieuw:");
                }
            }
        }
        var repository10 = new BrouwerRepository();
        try {
            if (repository10.maakDeOmzetVanDeBrouwersLeeg(brouwerIds) != brouwerIds.size()) {
                System.out.println("Niet gevonden ids: ");
                var gevondenIds = repository10.vindIdsDieNietBestaan(brouwerIds);
                brouwerIds.stream().filter(brouwerId -> !gevondenIds.contains(brouwerId))
                        .forEach(System.out::println);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
        }
    }
}
