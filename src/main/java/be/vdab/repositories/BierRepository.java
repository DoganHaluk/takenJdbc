package be.vdab.repositories;

import java.sql.SQLException;

public class BierRepository extends AbstractRepository{
    public int verwijderBierenMetOnbekendeAlcohol() throws SQLException{
        try (var connection = super.getConnection();
            var statement = connection.prepareStatement(
                "delete from bieren where alcohol is null"
            )){
            return statement.executeUpdate();
        }
    }
}
