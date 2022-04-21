package tqs.HW1.model;

public class Regions implements Comparable<Regions>{
    private String iso, name;


    public Regions(String iso, String name) {
        this.iso = iso;
        this.name = name;
    }



    public String getIso() {
        return this.iso;
    }

    public void setIso(String iso) {
        this.iso = iso;
    }

    public String getName() {
        return this.name;
    }

    public void setname(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Regions o) {
        return this.getName().compareTo(o.getName());
    }


    @Override
    public String toString() {
        return "{" +
            " iso='" + getIso() + "'" +
            ", name='" + getName() + "'" +
            "}";
    }


}
