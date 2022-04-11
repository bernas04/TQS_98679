package tqs.HW1.model;


public class CountryData {
    private String date, last_update;
    private long confirmed, deaths, recovered, confirmed_diff, deaths_diff, recovered_diff;
    private long active, active_diff;
    private double fatality_rate;


    public CountryData(String date, long confirmed, long deaths, long recovered, long confirmed_diff, long deaths_diff, long recovered_diff, String last_update, long active, long active_diff, double fatality_rate) {
        this.date = date;
        this.confirmed = confirmed;
        this.deaths = deaths;
        this.recovered = recovered;
        this.confirmed_diff = confirmed_diff;
        this.deaths_diff = deaths_diff;
        this.recovered_diff = recovered_diff;
        this.last_update = last_update;
        this.active = active;
        this.active_diff = active_diff;
        this.fatality_rate = fatality_rate;
    }


    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public long getConfirmed() {
        return this.confirmed;
    }

    public void setConfirmed(long confirmed) {
        this.confirmed = confirmed;
    }

    public long getDeaths() {
        return this.deaths;
    }

    public void setDeaths(long deaths) {
        this.deaths = deaths;
    }

    public long getRecovered() {
        return this.recovered;
    }

    public void setRecovered(long recovered) {
        this.recovered = recovered;
    }

    public long getConfirmed_diff() {
        return this.confirmed_diff;
    }

    public void setConfirmed_diff(long confirmed_diff) {
        this.confirmed_diff = confirmed_diff;
    }

    public long getDeaths_diff() {
        return this.deaths_diff;
    }

    public void setDeaths_diff(long deaths_diff) {
        this.deaths_diff = deaths_diff;
    }

    public long getRecovered_diff() {
        return this.recovered_diff;
    }

    public void setRecovered_diff(long recovered_diff) {
        this.recovered_diff = recovered_diff;
    }

    public String getLast_update() {
        return this.last_update;
    }

    public void setLast_update(String last_update) {
        this.last_update = last_update;
    }

    public long getActive() {
        return this.active;
    }

    public void setActive(long active) {
        this.active = active;
    }

    public long getActive_diff() {
        return this.active_diff;
    }

    public void setActive_diff(long active_diff) {
        this.active_diff = active_diff;
    }

    public double getFatality_rate() {
        return this.fatality_rate;
    }

    public void setFatality_rate(double fatality_rate) {
        this.fatality_rate = fatality_rate;
    }

}
