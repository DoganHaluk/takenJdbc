package be.vdab.repositories;

import be.vdab.domain.Brouwers;
import be.vdab.dto.BrouwerNaamEnAantalBieren;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    private Brouwers naarBrouwer(ResultSet result) throws SQLException {
        return new Brouwers(result.getLong("id"), result.getString("naam"), result.getString("adres"), result.getInt("postcode"), result.getString("gemeente"), result.getBigDecimal("omzet"));
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

    public List<Brouwers> brouwersWaarvanOmzetLigtTussenMinimumEnMaximum(double min, double max) throws SQLException {
        try (var connection = super.getConnection();
             var statement = connection.prepareStatement(
                     "SELECT id, naam, adres, postcode, gemeente, omzet FROM brouwers WHERE omzet BETWEEN ? AND ? ORDER BY omzet")) {
            statement.setDouble(1, min);
            statement.setDouble(2, max);
            var brouwers = new ArrayList<Brouwers>();
            var result = statement.executeQuery();
            while (result.next()) {
                brouwers.add(naarBrouwer(result));
            }
            return brouwers;
        }
    }

    public Optional<Brouwers> vindEenBrouwerOpId(long id) throws SQLException {
        try (var connection = super.getConnection();
             var statement = connection.prepareStatement(
                     "SELECT id, naam, adres, postcode, gemeente, omzet FROM brouwers WHERE id = ?")) {
            statement.setLong(1, id);
            var result = statement.executeQuery();
            return result.next() ? Optional.of(naarBrouwer(result)) : Optional.empty();
        }
    }

    public List<Brouwers> brouwersWaarvanOmzetLigtTussenMinEnMaxMetStoredProcedure(double min, double max) throws SQLException {
        try (var connection = super.getConnection();
             var statement = connection.prepareCall(
                     "{call BrouwersMetOmzetTussenMinimumEnMaximum(?, ?)}")) {
            statement.setDouble(1, min);
            statement.setDouble(2, max);
            var brouwers = new ArrayList<Brouwers>();
            var result = statement.executeQuery();
            while (result.next()) {
                brouwers.add(naarBrouwer(result));
            }
            return brouwers;
        }
    }

    public List<BrouwerNaamEnAantalBieren> vindBrouwersEnHunBieren() throws SQLException {
        try (var connection = super.getConnection();
             var statement = connection.prepareStatement(
                     "SELECT brouwers.naam AS brouwernaam, count(brouwerId) AS aantalbieren FROM bieren INNER JOIN brouwers ON bieren.brouwerId=brouwers.id GROUP BY brouwers.naam ORDER BY brouwers.naam")) {
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
            connection.setAutoCommit(false);
            var list = new ArrayList<BrouwerNaamEnAantalBieren>();
            var result = statement.executeQuery();
            while (result.next()) {
                list.add(new BrouwerNaamEnAantalBieren(result.getString("brouwernaam"), result.getLong("aantalbieren")));
            }
            connection.commit();
            return list;
        }
    }
}
