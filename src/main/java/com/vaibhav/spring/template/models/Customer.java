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
@Document(collection = "Customer")
public class Customer {
    private String id;
    private String firstName;
    private String lastName;
    private Boolean local;
}
