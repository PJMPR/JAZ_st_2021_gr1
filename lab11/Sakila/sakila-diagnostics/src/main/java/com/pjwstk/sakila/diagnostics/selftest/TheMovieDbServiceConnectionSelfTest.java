package com.pjwstk.sakila.diagnostics.selftest;

import com.pjwstk.sakila.diagnostics.contract.SelftestResult;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;

@Component
@AllArgsConstructor
public class TheMovieDbServiceConnectionSelfTest implements ISelfTest {
    @Value("${themoviesdb.api.key}")
    String apiKey;
    
    private final RestTemplate rest = new RestTemplate();


    @Override
    public SelftestResult execute() {
        SelftestResult selftestResult = new SelftestResult(getName(), getDescription(), false, null);
        try{
            var movie = rest.getForEntity("https://api.themoviedb.org/3/movie/" +
                    1122 +
                    "?api_key=" + apiKey, Object.class).getBody();
            System.out.println(movie);
            selftestResult.setPassed(true);
        } catch (HttpClientErrorException e){
            selftestResult.setPassed(false);
            selftestResult.setErrors(Arrays.asList(e.getMessage()));
        }

        return selftestResult;
    }

    @Override
    public String getName() {
        return "TheMovieDbServiceConnectionSelfTest";
    }

    @Override
    public String getDescription() {
        return "Checks movie db connection";
    }


}
