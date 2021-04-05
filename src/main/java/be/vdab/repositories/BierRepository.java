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
}
