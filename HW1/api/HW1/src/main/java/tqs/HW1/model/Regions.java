package tqs.HW1.model;

public class Regions {
    private String iso, country;


    public Regions(String iso, String country) {
        this.iso = iso;
        this.country = country;
    }



    public String getIso() {
        return this.iso;
    }

    public void setIso(String iso) {
        this.iso = iso;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

}