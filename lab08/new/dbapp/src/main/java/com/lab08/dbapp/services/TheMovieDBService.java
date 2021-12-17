package com.lab08.dbapp.services;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import com.lab08.dbapp.dto.MovieDto;
import com.lab08.dbapp.hepler.GZIP;
import com.lab08.dbapp.hepler.TheMovieDBJSON;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

@Service
public class TheMovieDBService {

    RestTemplate rest = new RestTemplate();

    @Value("${themoviedb.key}")
    String apiKey;

    public MovieDto getMovieDetails(@PathVariable int id) {
        return rest.getForEntity("https://api.themoviedb.org/3/movie/" +
                id +
                "?api_key=" + apiKey, MovieDto.class)
                .getBody();
    }

    public void getMoviesIdsByReleaseDate(int year) {

        LocalDate currentdate = LocalDate.now();
        LocalDate startingdate = LocalDate.of(year, 1, 1);

        while (startingdate.compareTo(currentdate) <= 0) {

            if (startingdate.getMonthValue() == currentdate.getMonthValue()) {
                var s = getResponseFromDiscoverEndpoint(startingdate.format(DateTimeFormatter.ofPattern("YYYY-MM-DD")),
                        startingdate.plusDays(1)
                                .format(DateTimeFormatter.ofPattern("YYYY-MM-DD")));
                startingdate = startingdate.plusDays(1);
            } else {
                startingdate = startingdate.plusMonths(1);
            }
        }
    }

    private MovieDto getResponseFromDiscoverEndpoint(String ltedate, String gtedate) {
        var s = rest.getForEntity("https://api.themoviedb.org/3/discover/movie" + "?api_key=" + apiKey
                + "&sort_by=primary_release_date.asc&primary_release_date.gte=" + gtedate + "&primary_release_date.lte"
                + ltedate, MovieDto.class).getBody();
        return s;
    }

    @Deprecated
    public Iterable<Integer> getAllMoviesIds() {

        LocalDate currentdate = LocalDate.now();
        currentdate = currentdate.minusDays(1);
        String filename = String.format("movie_ids_%s_%s_%s.json.gz",
                String.format("%02d", currentdate.getMonthValue()),
                String.format("%02d", currentdate.getDayOfMonth()),
                currentdate.getYear());
        String resultJSON = filename.replace(".gz", "");
        GZIP.decompressGzipFile(filename, resultJSON);
        getTheMovieDBJSON(resultJSON);

        TheMovieDBJSON movieJson = new TheMovieDBJSON();
        try {
            return movieJson.getAllMoviesIds(resultJSON);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Deprecated
    private void getTheMovieDBJSON(String filename) {
        File f = new File(filename);
        if (!f.exists()) {
            try (BufferedInputStream in = new BufferedInputStream(
                    new URL("http://files.tmdb.org/p/exports/" + filename)
                            .openStream());
                    FileOutputStream fileOutputStream = new FileOutputStream(filename)) {
                byte dataBuffer[] = new byte[1024];
                int bytesRead;
                while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                    fileOutputStream.write(dataBuffer, 0, bytesRead);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
