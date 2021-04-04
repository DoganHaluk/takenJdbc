package be.vdab.repositories;

import java.sql.SQLException;

public class BrouwerRepository extends AbstractRepository {
    public int toonGemiddeldeOmzetVanAlleBrouwers() throws SQLException {
        try (var connection = super.getConnection();
             var statement = connection.prepareStatement(
                     "SELECT Avg(omzet) as gemiddelde FROM brouwers"
             )) {
            var result = statement.executeQuery();
            result.next();
            return result.getInt("gemiddelde");
        }
    }
}
