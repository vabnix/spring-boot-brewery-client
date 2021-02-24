package com.vaibhav.spring.template.web;

import com.vaibhav.spring.template.models.Beer;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.URI;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class BreweryClientTest {
    Logger logger = LoggerFactory.getLogger(BreweryClientTest.class);

    @Autowired
    BreweryClient breweryClient;

    @Test
    void getBeerByIdTest() {
        Beer beer = breweryClient.getBeerById(UUID.randomUUID().toString());
        assertNotNull(beer);
    }

    @Test
    void saveNewBeerTest() {
        Beer beer = Beer.builder().beerName("Kingfisher").beerStyle("ALE").build();
        URI uri = breweryClient.saveNewBeer(beer);
        assertNotNull(uri);
        logger.info("Generated Path for new Beer " + uri);
    }

    @Test
    void deleteBeer() {
        breweryClient.deleteBeer("7efd717b-99b2-4f11-9c28-bba0700f73b2");
    }

    @Test
    void updateBeerTest(){
        Beer beer = Beer.builder()
                .id("7efd717b-99b2-4f11-9c28-bba0700f73b2")
                .beerName("Kingfisher")
                .beerStyle("ALE")
                .build();
        breweryClient.updateBeerRecord("7efd717b-99b2-4f11-9c28-bba0700f73b2", beer);
    }
}