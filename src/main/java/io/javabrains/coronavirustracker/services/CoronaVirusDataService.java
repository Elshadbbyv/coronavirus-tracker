package io.javabrains.coronavirustracker.services;

import io.javabrains.coronavirustracker.model.LocationStarts;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service

public class CoronaVirusDataService {
    private static String VIRUS_DATA_URL  = "https://raw.githubusercontent.com/DataHerb/dataset-covid-19/master/csse_covid_19_time_series/time_series_19-covid-Confirmed.csv";

    private List<LocationStarts> allStats = new ArrayList<>();
    @PostConstruct
    @Scheduled(cron = "* * 1 * * *")
    public void fetchVirusDate() throws IOException, InterruptedException {
        List<LocationStarts> newStats = new ArrayList<>();

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(VIRUS_DATA_URL))
                .build();
        HttpResponse<String> httpResponse = httpClient.send(httpRequest,HttpResponse.BodyHandlers.ofString());

        StringReader reader = new StringReader(httpResponse.body());
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader);
        for (CSVRecord record : records){
            LocationStarts starts = new LocationStarts();
            starts.setState(record.get("Province/State"));
            starts.setCountry(record.get("Country/Region"));
//            starts.setLatestTotalCases(Integer.parseInt(record.get(record.size() - 1)));
            System.out.println(starts);
            newStats.add(starts);

    }
        this.allStats = newStats;
    }
}
