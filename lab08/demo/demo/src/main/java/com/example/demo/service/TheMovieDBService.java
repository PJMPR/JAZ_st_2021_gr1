package com.example.demo.service;

import com.example.demo.contract.MovieDto;
import com.example.demo.quartz.DBUpdater;
import com.example.demo.quartz.SchedulerInfo;
import com.example.demo.statusInfo.SystemStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.logging.FileHandler;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class TheMovieDBService {
    private final RestTemplate restTemplate;
    @Value("${themoviedb.api.key}")
    String apiKey;

    private static final Logger LOGGER = Logger.getLogger(String.valueOf(TheMovieDBService.class));
    FileHandler fileHandler;
    private final SystemStatus systemStatus;
    private final SchedulerService schedulerService;

    public MovieDto getMovie(int id){
        var movie =  restTemplate.getForEntity("https://api.themoviedb.org/3/movie/" +
                        id +
                        "?api_key=" + apiKey, MovieDto.class)
                .getBody();
        return movie;
    }

    public void runDataBaseChecker(){
        final SchedulerInfo info = new SchedulerInfo();
        info.setRunForever(false);
        info.setRepeatIntervals(24);
        info.setStartHour(2);

        schedulerService.scheduler(DBUpdater.class,info);
    }

    public SystemStatus getSystemStatusInfo() {
        return systemStatus;
    }

    public String reloadData() {
        return "reloading...";
    }

    public  String reloadDataByYear(int year) {
        return "reloading...";
    }

}
