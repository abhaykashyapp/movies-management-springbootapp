package com.abhay.moviesManagement.entity;


import lombok.Data;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;


@Document(collection = "movies")
@Data

public class Movie {
    @Id
    private ObjectId id;
    private String name;
    private String genre;
    private LocalDateTime date;


}
