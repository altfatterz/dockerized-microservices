package com.example;

import lombok.Data;

/**
 * @author Zoltan Altfatter
 */

@Data
public class Book {

    private String title;
    private String author;
    private String body;
    private String isbn;
    private Integer copiesOwned;

}
