package be.vdab.repositories;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SoortRepository extends AbstractRepository {
    public List<String> vindBierenVanSoort(String soort) throws SQLException {
        try (var connection = super.getConnection();
             var statement = connection.prepareStatement(
                     "SELECT bieren.naam AS bierennaam FROM soorten INNER JOIN bieren ON bieren.soortId=soorten.id WHERE soorten.naam = ?")) {
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            connection.setAutoCommit(false);
            statement.setString(1, soort);
            var namen = new ArrayList<String>();
            var result = statement.executeQuery();
            while (result.next()) {
                namen.add(result.getString("bierennaam"));
            }
            connection.commit();
            return namen;
        }
    }
}
