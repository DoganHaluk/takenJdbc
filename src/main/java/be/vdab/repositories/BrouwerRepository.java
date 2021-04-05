package be.vdab.repositories;

import be.vdab.domain.Brouwers;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BrouwerRepository extends AbstractRepository {
    public BigDecimal toonGemiddeldeOmzetVanAlleBrouwers() throws SQLException {
        try (var connection = super.getConnection();
             var statement = connection.prepareStatement(
                     "SELECT Avg(omzet) AS gemiddelde FROM brouwers"
             )) {
            var result = statement.executeQuery();
            result.next();
            return result.getBigDecimal("gemiddelde");
        }
    }

    public List<Brouwers> brouwersOmzetHebbenDieHogerDanGemiddelde() throws SQLException {
        try (var connection = super.getConnection();
             var statement = connection.prepareStatement(
                     "SELECT id, naam, adres, postcode, gemeente, omzet FROM brouwers WHERE omzet > (SELECT Avg(omzet) FROM brouwers)")) {
            var brouwers = new ArrayList<Brouwers>();
            var result = statement.executeQuery();
            while (result.next()) {
                brouwers.add(naarBrouwer(result));
            }
            return brouwers;
        }
    }

    private Brouwers naarBrouwer(ResultSet result) throws SQLException {
        return new Brouwers(result.getLong("id"), result.getString("naam"), result.getString("adres"), result.getInt("postcode"), result.getString("gemeente"), result.getBigDecimal("omzet"));
    }
}
