package be.vdab.repositories;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SoortRepository extends AbstractRepository {
    public List<String> vindBierenVanSoort(String soort) throws SQLException {
        try (var connection = super.getConnection();
             var statement = connection.prepareStatement(
                     "SELECT bieren.naam AS bierennaam FROM soorten INNER JOIN bieren ON bieren.soortId=soorten.id WHERE soorten.naam = ?")) {
            statement.setString(1, soort);
            var soorten = new ArrayList<String>();
            var result = statement.executeQuery();
            while (result.next()) {
                soorten.add(result.getString("bierennaam"));
            }
            return soorten;
        }
    }
}
