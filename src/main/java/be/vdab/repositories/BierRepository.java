package be.vdab.repositories;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BierRepository extends AbstractRepository {
    public int verwijderBierenMetOnbekendeAlcohol() throws SQLException {
        try (var connection = super.getConnection();
             var statement = connection.prepareStatement(
                     "DELETE FROM bieren WHERE alcohol IS null"
             )) {
            return statement.executeUpdate();
        }
    }

    public void brouwer1GaatFailliet() throws SQLException {
        var sqlBierenMetAlcoholVanaf85 = "UPDATE bieren SET brouwerId = 2 WHERE brouwerId = 1 AND alcohol >= 8.5";
        var sqlBierenMetAlcoholMeerDan85 = "UPDATE bieren SET brouwerId = 3 WHERE brouwerId = 1";
        var sqlVerwijderBrouwer1 = "DELETE FROM brouwers WHERE id = 1";
        try (var connection = super.getConnection();
             var statementBierenMetAlcoholVanaf85 = connection.prepareStatement(sqlBierenMetAlcoholVanaf85);
             var statementBierenMetAlcoholMeerDan85 = connection.prepareStatement(sqlBierenMetAlcoholMeerDan85);
             var statementVerwijderBrouwer1 = connection.prepareStatement(sqlVerwijderBrouwer1)) {
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            connection.setAutoCommit(false);
            statementBierenMetAlcoholVanaf85.executeUpdate();
            statementBierenMetAlcoholMeerDan85.executeUpdate();
            statementVerwijderBrouwer1.executeUpdate();
            connection.commit();
        }
    }

    public List<String> vindAlBierenInEenMaand (int maand) throws SQLException{
        try (var connection = super.getConnection();
             var statement = connection.prepareStatement(
                     "SELECT naam FROM bieren WHERE {fn month(sinds)} = ?")) {
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            connection.setAutoCommit(false);
            statement.setObject(1, maand);
            var namen= new ArrayList<String>();
            var result = statement.executeQuery();
            while (result.next()) {
                namen.add(result.getString("naam"));
            }
            connection.commit();
            return namen;
        }
    }
}
