package com.vaibhav.spring.template.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "Beers")
public class Beer {
    private String id;
    private String beerName;
    private String beerStyle;
    private String upc;
}