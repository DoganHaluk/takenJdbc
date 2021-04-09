package be.vdab.dto;

public class BrouwerNaamEnAantalBieren {
    private final String brouwerNaam;
    private final long aantalBieren;

    public BrouwerNaamEnAantalBieren(String brouwerNaam, long aantalBieren) {
        this.brouwerNaam = brouwerNaam;
        this.aantalBieren = aantalBieren;
    }

    @Override
    public String toString() {
        return brouwerNaam + " - " + aantalBieren;
    }
}


