package src;

public class Aeroport {
    private String IATA;
    private String name;
    private String country;
    private double latitude;
    private double longitude;

    Aeroport(String IATA, String name, String country, double latitude, double longitude)
    {
        this.IATA = IATA;
        this.name = name;
        this.country = country;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getIATA()
    {
        return IATA;
    }

    public double getLatitude()
    {
        return latitude;
    }

    public double getLongitude()
    {
        return longitude;
    }

    @Override
    public String toString()
    {
        return "Aeroport : IATA = " + IATA
                + "; Name = " + name
                + "; Country = " + country
                + "; latitude = " + latitude
                + "; longitude = " + longitude
                + ";";
    }
}
