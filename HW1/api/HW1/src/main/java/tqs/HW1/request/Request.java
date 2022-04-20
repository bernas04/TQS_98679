package tqs.HW1.request;


import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.springframework.beans.factory.annotation.Value;



public class Request {
    
    private String API_HOST = "covid-19-statistics.p.rapidapi.com";
    private String URI_API = "https://covid-19-statistics.p.rapidapi.com";
    private String API_KEY = "110558e39emshd191bd4c6012a68p161673jsnffbcc68d9b98";

    public Request(){}

    public String request(String url) throws IOException, InterruptedException{
        HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(URI_API + url))
        .header("X-RapidAPI-Host", API_HOST)
        .header("X-RapidAPI-Key", API_KEY)
        .method("GET", HttpRequest.BodyPublishers.noBody())
        .build();
        String response =  HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString()).body();
        return response;
    }
}
