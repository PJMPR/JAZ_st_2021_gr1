package com.pjwstk.sakila.diagnostics.service.monitor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;

@Configuration
class Registerer {
    String name;
    String host;

    public Registerer(@Value("${sakila.diagnostics.service.name}") String name,
            @Value("${sakila.diagnostics.service.host}") String host) {
        this.name = name;
        this.host = host;
    }

    @PostConstruct
    public void postConstruct() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<ServiceData> entity = new HttpEntity<ServiceData>(
                new ServiceData(
                        name,
                        host),
                headers);

        ResponseEntity response = restTemplate.exchange(
                "http://localhost:8083/monitoring/register",
                HttpMethod.POST,
                entity,
                ServiceData.class);
    }
}