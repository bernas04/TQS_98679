package tqs.HW1.services;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import tqs.HW1.model.CountryData;
import tqs.HW1.model.Regions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
public class CovidServices {
    private final String URI_API = "https://covid-19-statistics.p.rapidapi.com";
    private final String API_HOST = "covid-19-statistics.p.rapidapi.com";
    private final String API_KEY = "110558e39emshd191bd4c6012a68p161673jsnffbcc68d9b98";
    
    private static final Logger log = LoggerFactory.getLogger(CovidServices.class);

    public List<Regions> getAllRegions() throws IOException, InterruptedException{
        log.info("> Get all Countries and ISO Codes.");
        HttpRequest request = HttpRequest.newBuilder()
		.uri(URI.create(URI_API + "/regions"))
		.header("X-RapidAPI-Host", API_HOST)
		.header("X-RapidAPI-Key", API_KEY)
		.method("GET", HttpRequest.BodyPublishers.noBody())
		.build();
        String response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString()).body();
        JSONObject jsonResponse = new JSONObject(response);
        JSONArray importantData = jsonResponse.getJSONArray("data");
        

        List<Regions> iso_country = new ArrayList<>();
        for (int i = 0; i < importantData.length(); i++){
            JSONObject eachElement = importantData.getJSONObject(i);
            iso_country.add(new Regions(eachElement.getString("iso"), eachElement.getString("name")));
        }
        return iso_country;
    }

    public CountryData getInformationByIso(String iso) throws IOException, InterruptedException{
        log.info("> Getting "+iso+" data.");
        String date="", last_update="";
        long confirmed =0, deaths =0, recovered=0, confirmed_diff=0, deaths_diff=0, recovered_diff=0, active=0, active_diff=0;
        double fatality_rate=0.000;
        
        HttpRequest request = HttpRequest.newBuilder()
		.uri(URI.create(URI_API+"/reports?iso="+iso+"&date=2022-04-07"))
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
        return new CountryData(date, confirmed, deaths, recovered, confirmed_diff, deaths_diff, recovered_diff, last_update, active, active_diff, fatality_rate/importantData.length());
    }

    public CountryData getWorldInformation() throws IOException, InterruptedException{
        log.info("> Get world data.");
        String date="", last_update="";
        long confirmed =0, deaths =0, recovered=0, confirmed_diff=0, deaths_diff=0, recovered_diff=0, active=0, active_diff=0;
        double fatality_rate=0.000;

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
        
        return new CountryData(date, confirmed, deaths, recovered, confirmed_diff, deaths_diff, recovered_diff, last_update, active, active_diff, fatality_rate/importantData.length());
    }
}
