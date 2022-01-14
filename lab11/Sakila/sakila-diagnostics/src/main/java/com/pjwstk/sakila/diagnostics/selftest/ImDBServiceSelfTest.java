package com.pjwstk.sakila.diagnostics.selftest;

import com.pjwstk.sakila.diagnostics.contract.SelftestResult;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.Collections;

@Component
@AllArgsConstructor
public class ImDBServiceSelfTest implements ISelfTest {
    @Value("${imdb.api.key}")
    String apiKey;

    private final RestTemplate rest = new RestTemplate();

    


    @Override
    public SelftestResult execute() {
        SelftestResult selftestResult = new SelftestResult(getName(), getDescription(), false, null);
        try{
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            headers.add("x-rapidapi-host", "imdb8.p.rapidapi.com");
            headers.add("x-rapidapi-key", "${imdbApiKey}");

            HttpEntity<String> entity = new HttpEntity<>("body", headers);

            rest.exchange("https://imdb8.p.rapidapi.com/auto-complete?q=game%20of%20thr", HttpMethod.GET, entity, String.class);
            selftestResult.setPassed(true);
        } catch (HttpClientErrorException e){
            selftestResult.setPassed(false);
            selftestResult.setErrors(Arrays.asList(e.getMessage()));
        }

        return selftestResult;
    }




    @Override
    public String getName() {

        return "ImDBServiceSelfTest";
    }




    @Override
    public String getDescription() {
        return "Checks imdb is avaliable";
    }
}
