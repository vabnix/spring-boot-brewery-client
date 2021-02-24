package com.vaibhav.spring.template.web;

import com.vaibhav.spring.template.models.Beer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Component
@ConfigurationProperties(value = "vs.brewery", ignoreUnknownFields = false)
public class BreweryClient {
    @Value("vs.brewery.apiHost")
    private String apiHost;

    public final String BEER_PATH = "/api/v1/beers/";

    private final RestTemplate restTemplate;

    public BreweryClient(RestTemplateBuilder templateBuilder) {
        this.restTemplate = templateBuilder.build();
    }

    public Beer getBeerById(String id){
        id = "01a69a4e-05ec-4f6f-a5e9-cd3aef7ee395";
        return restTemplate.getForObject(apiHost + BEER_PATH + id, Beer.class);
    }

    public URI saveNewBeer(Beer beer){
        return restTemplate.postForLocation(apiHost + BEER_PATH , beer);
    }

    public void updateBeerRecord(String id, Beer beer){
        restTemplate.put(apiHost + BEER_PATH + id, beer);
    }

    public void deleteBeer(String id){
         restTemplate.delete(apiHost + BEER_PATH + id);
    }

    public void setApiHost(String apiHost) {
        this.apiHost = apiHost;
    }
}
