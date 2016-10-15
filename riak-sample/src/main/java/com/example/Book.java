package com.example;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

/**
 * @author Zoltan Altfatter
 */

@Data
@Builder
public class Book {

    private String title;
    private String author;
    private String body;
    private String isbn;
    private Integer copiesOwned;

    @Tolerate
    public Book() {
    }

}
