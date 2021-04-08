package be.vdab.repositories;

import java.sql.SQLException;

public class BierRepository extends AbstractRepository {
    public int verwijderBierenMetOnbekendeAlcohol() throws SQLException {
        try (var connection = super.getConnection();
             var statement = connection.prepareStatement(
                     "DELETE FROM bieren WHERE alcohol IS null"
             )) {
            return statement.executeUpdate();
        }
    }

    public int brouwer1GaatFailliet() throws SQLException {
        var sqlBierenMetAlcoholVanaf85 = "UPDATE bieren SET brouwerId = 2 WHERE brouwerId = 1 AND alcohol < 8.5";
        var sqlBierenMetAlcoholMeerDan85 = "UPDATE bieren SET brouwerId = 3 WHERE brouwerId = 1 AND alcohol >= 8.5";
        var sqlVerwijderBrouwer1 = "DELETE FROM brouwers WHERE id = 1";
        try (var connection = super.getConnection();
             var statementBierenMetAlcoholVanaf85 = connection.prepareStatement(sqlBierenMetAlcoholVanaf85);
             var statementBierenMetAlcoholMeerDan85 = connection.prepareStatement(sqlBierenMetAlcoholMeerDan85);
             var statementVerwijderBrouwer1 = connection.prepareStatement(sqlVerwijderBrouwer1)) {
            connection.setAutoCommit(false);
            statementBierenMetAlcoholVanaf85.executeUpdate();
            statementBierenMetAlcoholMeerDan85.executeUpdate();
            statementVerwijderBrouwer1.executeUpdate();
            connection.commit();
            return statementBierenMetAlcoholVanaf85.executeUpdate() + statementBierenMetAlcoholMeerDan85.executeUpdate();
        }
    }
}
