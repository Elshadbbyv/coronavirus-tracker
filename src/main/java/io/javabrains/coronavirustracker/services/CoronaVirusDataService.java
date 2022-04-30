package io.javabrains.coronavirustracker.services;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class CoronaVirusDataService {
    private static String VIRUS_DATA_URL  = "https://raw.githubusercontent.com/DataHerb/dataset-covid-19/master/csse_covid_19_time_series/time_series_19-covid-Confirmed.csv";
    @PostConstruct
    public void fetchVirusDate() throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(VIRUS_DATA_URL))
                .build();
        HttpResponse<String> httpResponse = httpClient.send(httpRequest,HttpResponse.BodyHandlers.ofString());
        System.out.println(httpResponse.body());
    }
}
