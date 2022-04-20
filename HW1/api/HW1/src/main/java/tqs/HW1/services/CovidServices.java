package tqs.HW1.services;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import tqs.HW1.cache.Cache;
import tqs.HW1.model.CountryData;
import tqs.HW1.model.Regions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
public class CovidServices {

    @Value("${api.host}")
    private String API_HOST;

    @Value("${api.url}")
    private String URI_API;
    
    @Value("${api.key}")
    private String API_KEY;

    private final long CACHE_TIME = 90000;
    
    private Cache c = new Cache(CACHE_TIME); 

    private CountryData informationByIso, worldInformation;
    private List<Integer> percentageInfo;
    private List<CountryData> fiveMonthsBeforeCountry = new ArrayList<>();
    private List<Regions> iso_country = new ArrayList<>();
    
    private static final Logger log = LoggerFactory.getLogger(CovidServices.class);

    public List<Regions> getAllRegions() throws IOException, InterruptedException{

        Object tmp = c.get("regions");


        if (tmp!=null){
            iso_country = (List<Regions>) tmp;
            log.info("[CACHE] Get all Countries and ISO Codes.");
            return iso_country;
        }

        log.info("[API] Get all Countries and ISO Codes.");
        HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(URI_API + "/regions"))
        .header("X-RapidAPI-Host", API_HOST)
        .header("X-RapidAPI-Key", API_KEY)
        .method("GET", HttpRequest.BodyPublishers.noBody())
        .build();
        String response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString()).body();
        JSONObject jsonResponse = new JSONObject(response);
        JSONArray importantData = jsonResponse.getJSONArray("data");
        
        iso_country.clear();

        for (int i = 0; i < importantData.length(); i++){
            JSONObject eachElement = importantData.getJSONObject(i);
            Regions r = new Regions(eachElement.getString("iso"), eachElement.getString("name"));
            iso_country.add(r);
        }
        c.push("regions", iso_country);
        
        
        
        return iso_country;
        
    }

    public List<CountryData> getLastFiveMonthsData(String iso) throws IOException, InterruptedException{
        LocalDate now = LocalDate.now();
        

        Object tmp = c.get("last5Months"+iso);

        if (tmp!=null){
            fiveMonthsBeforeCountry = (List<CountryData>) tmp;
            log.info("[CACHE] Getting "+iso+" last 5 months data.");
            return fiveMonthsBeforeCountry;
        }


        log.info("[API] Getting "+iso+" last 5 months data.");
        fiveMonthsBeforeCountry.clear();
        for (int i=-5; i<0; i++){
            String date="", last_update="";
            long confirmed =0, deaths =0, recovered=0, confirmed_diff=0, deaths_diff=0, recovered_diff=0, active=0, active_diff=0;
            double fatality_rate=0.000;
            HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(URI_API+"/reports?iso="+iso+"&date="+now.plusMonths(i)))
            .header("X-RapidAPI-Host", API_HOST) 
            .header("X-RapidAPI-Key", API_KEY)
            .method("GET", HttpRequest.BodyPublishers.noBody())
            .build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            JSONObject jsonResponse = new JSONObject(response.body());
            JSONArray importantData = jsonResponse.getJSONArray("data");
            for (int j = 0; j<importantData.length();j++){
                JSONObject eachJson = importantData.getJSONObject(j);
                date = eachJson.getString("date");
                confirmed += eachJson.getLong("confirmed");
                deaths += eachJson.getLong("deaths");
                recovered += eachJson.getLong("recovered");
                confirmed_diff += eachJson.getLong("confirmed_diff");
                deaths_diff += eachJson.getLong("deaths_diff");
                recovered_diff += eachJson.getLong("recovered_diff");
                last_update = eachJson.getString("last_update");
                active += eachJson.getLong("active");
                active_diff += eachJson.getLong("active_diff");
                fatality_rate += eachJson.getDouble("fatality_rate");
            }
            fiveMonthsBeforeCountry.add(new CountryData(date, confirmed, deaths, recovered, confirmed_diff, deaths_diff, recovered_diff, last_update, active, active_diff, fatality_rate/importantData.length()));
        }

        c.push("last5Months"+iso, fiveMonthsBeforeCountry);
        
        return fiveMonthsBeforeCountry;

    }


    public CountryData getInformationByIso(String iso) throws IOException, InterruptedException{
        String date="", last_update="";
        long confirmed =0, deaths =0, recovered=0, confirmed_diff=0, deaths_diff=0, recovered_diff=0, active=0, active_diff=0;
        double fatality_rate=0.000;

        Object tmp = c.get("country"+iso);

        if (tmp!=null){
            informationByIso = (CountryData) tmp;
            log.info("[CACHE] Getting "+iso+" data.");
            return informationByIso;
        }

        log.info("[API] Getting "+iso+" data.");
        HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(URI_API+"/reports?iso="+iso))
        .header("X-RapidAPI-Host", API_HOST) 
        .header("X-RapidAPI-Key", API_KEY)
        .method("GET", HttpRequest.BodyPublishers.noBody())
        .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        JSONObject jsonResponse = new JSONObject(response.body());
        JSONArray importantData = jsonResponse.getJSONArray("data");
        
        for (int i = 0; i<importantData.length();i++){
            JSONObject eachJson = importantData.getJSONObject(i);
            date = eachJson.getString("date");
            confirmed += eachJson.getLong("confirmed");
            deaths += eachJson.getLong("deaths");
            recovered += eachJson.getLong("recovered");
            confirmed_diff += eachJson.getLong("confirmed_diff");
            deaths_diff += eachJson.getLong("deaths_diff");
            recovered_diff += eachJson.getLong("recovered_diff");
            last_update = eachJson.getString("last_update");
            active += eachJson.getLong("active");
            active_diff += eachJson.getLong("active_diff");
            fatality_rate += eachJson.getDouble("fatality_rate");
        }
        
        c.push("country"+iso, informationByIso);
        informationByIso = new CountryData(date, confirmed, deaths, recovered, confirmed_diff, deaths_diff, recovered_diff, last_update, active, active_diff, fatality_rate/importantData.length());
        
        return informationByIso;
    }


    
    public CountryData getWorldInformation() throws IOException, InterruptedException{

        Object tmp = c.get("worldInfo");


        if (tmp!=null){
            worldInformation = (CountryData) tmp;
            log.info("[CACHE] Get world data.");
            return worldInformation;
        }
        
        String date="", last_update="";
        long confirmed =0, deaths =0, recovered=0, confirmed_diff=0, deaths_diff=0, recovered_diff=0, active=0, active_diff=0;
        double fatality_rate=0.000;
        log.info("[API] Get world data.");

        HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create("https://covid-19-statistics.p.rapidapi.com/reports/total"))
        .header("X-RapidAPI-Host", "covid-19-statistics.p.rapidapi.com")
        .header("X-RapidAPI-Key", "110558e39emshd191bd4c6012a68p161673jsnffbcc68d9b98")
        .method("GET", HttpRequest.BodyPublishers.noBody())
        .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        JSONObject jsonResponse = new JSONObject(response.body());
        JSONObject importantData = jsonResponse.getJSONObject("data");

        date = importantData.getString("date");
        confirmed = importantData.getLong("confirmed");
        deaths = importantData.getLong("deaths");
        recovered = importantData.getLong("recovered");
        confirmed_diff = importantData.getLong("confirmed_diff");
        deaths_diff = importantData.getLong("deaths_diff");
        recovered_diff = importantData.getLong("recovered_diff");
        last_update = importantData.getString("last_update");
        active = importantData.getLong("active");
        active_diff = importantData.getLong("active_diff");
        fatality_rate = importantData.getDouble("fatality_rate");
        worldInformation = new CountryData(date, confirmed, deaths, recovered, confirmed_diff, deaths_diff, recovered_diff, last_update, active, active_diff, fatality_rate);
        c.push("worldInfo", worldInformation);
        
        return worldInformation;
        
    }

    public List<Integer> getPercentageInfo() throws IOException, InterruptedException{
        
        Object tmp = c.get("percentageInfo");

        if (tmp!=null){
            percentageInfo= (List<Integer>) tmp;
            log.info("[CACHE] Get percentage diff data");
            return percentageInfo;
        } 


        LocalDate today = LocalDate.now();
        
        List<Long> confirmed_diff= new ArrayList<>();
        List<Long> deaths_diff= new ArrayList<>();
        List<Double> fatality_diff= new ArrayList<>();


        log.info("[API] Get percentage diff data");

        for (int i=-2; i< 0; i++){
            String dayString = today.plusDays(i).toString();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://covid-19-statistics.p.rapidapi.com/reports/total?date="+ dayString))
                    .header("X-RapidAPI-Host", "covid-19-statistics.p.rapidapi.com")
                    .header("X-RapidAPI-Key", "110558e39emshd191bd4c6012a68p161673jsnffbcc68d9b98")
                    .method("GET", HttpRequest.BodyPublishers.noBody())
                    .build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

            JSONObject jsonResponse = new JSONObject(response.body());
            JSONObject importantData = jsonResponse.getJSONObject("data");
            confirmed_diff.add(importantData.getLong("confirmed_diff"));
            deaths_diff.add(importantData.getLong("deaths_diff"));
            fatality_diff.add(importantData.getDouble("fatality_rate"));
        }
        
        Double d1 = (double) confirmed_diff.get(0);
        Double d2 = (double) confirmed_diff.get(1);
        Double confirmed_percentagem = ((d1-d2) / d1) * 100;
        int percentage = (int) Math.rint(confirmed_percentagem);

        Double d3 = (double) deaths_diff.get(0);
        Double d4 = (double) deaths_diff.get(1);
        Double deaths_percentagem = ((d3-d4) / d3) * 100;
        int percentageDeaths = (int) Math.rint(deaths_percentagem);

        Double d5 = (double) fatality_diff.get(0);
        Double d6 = (double) fatality_diff.get(1);
        Double dat_percentagem = ((d5-d6) / d5) * 100;
        int percentageFatality = (int) Math.rint(dat_percentagem);
        
        percentageInfo = Arrays.asList(percentage, percentageDeaths, percentageFatality);
        c.push("percentageInfo", percentageInfo);
    
        return percentageInfo;
    }

    public Map<String,Integer> getCacheInfo(){
        Map<String,Integer> cacheInfo = new HashMap<>();
        cacheInfo.put("hits", c.getHits());
        cacheInfo.put("misses", c.getMisses());
        cacheInfo.put("requests", c.getRequests());

        return cacheInfo;
    }
}
