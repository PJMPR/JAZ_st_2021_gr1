package com.example.demo.service;

import com.example.demo.quartz.UpdateDB;
import com.example.demo.quartz.ScheduleInfo;
import com.example.demo.contract.IMDBMovieDto;
import com.example.demo.contract.MovieDto;
import com.example.demo.contract.SystemStatus;
import com.example.demo.model.Actor;
import com.example.demo.repository.ActorRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

@Service
public class FilmService {
    RestTemplate rest;
    ActorRepository actorRepository;
    private static final Logger LOGGER = Logger.getLogger(String.valueOf(FilmService.class));
    FileHandler fh;
    private final SystemStatus systemStatus;
    private final SchedulerService scheduler;

    @Autowired
    public FilmService(RestTemplate rest, ActorRepository actorRepository, SystemStatus systemStatusInfo, SchedulerService scheduler) throws IOException {
            fh = new FileHandler("demo/src/main/java/com/example/demo/Log.txt", true);
            LOGGER.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
            LOGGER.info("Logger started working");

        this.rest = rest;
        this.actorRepository = actorRepository;
        this.systemStatus = systemStatusInfo;
        this.scheduler = scheduler;
    }

    public void runDatabaseChecker(){
        final ScheduleInfo info = new ScheduleInfo();
        info.setRunForever(false);
        info.setRepeatIntervalHs(24);
        info.setStartHour(2);

        scheduler.schedule(UpdateDB.class, info);
    }

    public MovieDto getMoveInfo(int id) {
        var movie = rest.getForEntity("https://api.themoviedb.org/3/movie/" +
                id + "?api_key=" + System.getenv("TMDBapiKey"), MovieDto.class).getBody();

        if (movie != null) {
            var movieIMDB = getMovieInfoFromIMDB(movie.getImdb_id());
            BeanUtils.copyProperties(movieIMDB, movie);
            List<Actor> actors = new ArrayList<>();
            String[] names = movieIMDB.getActors().split(",");
            forNames(actors, names);
            movie.setActors(actors);
        }
        return movie;
    }

    private void forNames(List<Actor> actors, String[] names) {
        for (String s : names) {
            int actorId = checkIfActorExist(s);
            if (actorId == 0) {
                String[] parts = s.strip().split(" ");
                actors.add(new Actor(parts[0], parts[1]));
            } else {
                actors.add(actorRepository.findById(actorId).get());
            }
        }
    }

    public IMDBMovieDto getMovieInfoFromIMDB(String id) {
        return rest.getForEntity("https://www.omdbapi.com/?apikey="
                + System.getenv("OMDBapiKey") +
                "&i=" + id, IMDBMovieDto.class).getBody();
    }

    public int checkIfActorExist(String name) {

        String firstname = name.strip().split(" ")[0];
        String lastname = name.strip().split(" ")[1];
        boolean isInDB = actorRepository.findAll().stream().anyMatch(actor -> Objects.equals(actor.getFirstName(), firstname)
                && Objects.equals(actor.getLastName(), lastname));

        if (isInDB) {
            return actorRepository.findAll().stream().filter(actor -> Objects.equals(actor.getFirstName(), firstname)
                    && Objects.equals(actor.getLastName(), lastname)).findFirst().get().getActorId();
        } else return 0;

    }

    public SystemStatus getSystemStatusInfo() {
        return systemStatus;
    }

    public String reloadData() {
        return "reloading...";
    }

    public String reloadDataByYear(int year) {
        return "reloading...";
    }
}
