package be.vdab.domain;

import java.math.BigDecimal;

public class Brouwers {
    private final long id;
    private final String naam;
    private final String adres;
    private final int postcode;
    private final String gemeente;
    private final BigDecimal omzet;

    public Brouwers(long id, String naam, String adres, int postcode, String gemeente, BigDecimal omzet) {
        this.id = id;
        this.naam = naam;
        this.adres = adres;
        this.postcode = postcode;
        this.gemeente = gemeente;
        this.omzet = omzet;
    }

    @Override
    public String toString() {
        return id + ": " + naam  + " (" + omzet + ")";
    }
}
