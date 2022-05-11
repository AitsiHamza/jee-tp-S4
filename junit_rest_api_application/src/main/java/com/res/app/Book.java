package com.res.app;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "book_record")
@Data @AllArgsConstructor @NoArgsConstructor
@Builder
public class Book {
    @Id @GeneratedValue(strategy= GenerationType.AUTO)
    private Long booId;

    @NonNull
    private String name;

    @NonNull
    private String summary;

    private int rating;
}
