package com.example.demo.business;

import com.example.demo.business.handlers.ActorsHandler;
import com.example.demo.business.handlers.Handler;
import com.example.demo.business.handlers.LanguageHandler;
import com.example.demo.web.DTOs.DiscoveryTheMoveDbDTO;
import com.example.demo.web.DTOs.FilmDTO;
import com.example.demo.web.DTOs.OMDbDTO;
import com.example.demo.web.DTOs.TheMoveDbDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Calendar;

@Service
@RequiredArgsConstructor
public class UpdaterService {
    private final FilmService filmService;
    private final RestTemplate rest;

    @Value("${TheMovieDb.api.key}")
    private String TheMovieDbApiKey;
    private final String TheMovieDbApiURL = "https://api.themoviedb.org/3/";

    @Value("${OMDb.api.key}")
    private String OMDbApiKey;
    private final String OMDbApiURL = "http://www.omdbapi.com/";

    public void startUpdate(int year){
        DiscoveryTheMoveDbDTO discovery;
        Status status = Status.getInstance();
        FilmDTO filmDTO = new FilmDTO();

        for (int selectedYear = year; selectedYear <= Calendar.getInstance().get(Calendar.YEAR); selectedYear++) {
            discovery = getMovies(selectedYear, 1);
            status.setMoviesToUpdate(status.getMoviesToUpdate() + discovery.getTotal_pages());
            for (int selectedPage = 1; selectedPage <= discovery.getTotal_pages(); selectedPage++) {
                for (var result : discovery.getResults()){
                    var moveDbDTO = getMovieFromTheMoveDb(result.getId());
                    var omDbDTO = getMovieFromOMDb(moveDbDTO.getImdbId());
                    BeanUtils.copyProperties(moveDbDTO, filmDTO);
                    BeanUtils.copyProperties(omDbDTO, filmDTO);
                    System.out.println(filmDTO);
                }
                discovery = getMovies(selectedYear, selectedPage);
            }
        }
    }



    private TheMoveDbDTO getMovieFromTheMoveDb(int id){
        return rest.getForEntity(TheMovieDbApiURL +
                "movie/" + id +
                "?api_key=" + TheMovieDbApiKey, TheMoveDbDTO.class).getBody();
    }

    private OMDbDTO getMovieFromOMDb(String imdb_id){
        return rest.getForEntity( OMDbApiURL +
                "?apikey=" + OMDbApiKey +
                "&i=" + imdb_id, OMDbDTO.class).getBody();
    }

    private DiscoveryTheMoveDbDTO getMovies(int year, int page){
        return rest.getForEntity(TheMovieDbApiURL +
                "discover/movie" +
                "?api_key=" + TheMovieDbApiKey +
                "&sort_by=popularity.desc" +
                "&page=" + page +
                "&primary_release_year=" + year, DiscoveryTheMoveDbDTO.class).getBody();
    }
}
