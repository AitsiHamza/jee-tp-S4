package com.example.students_mvc.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Student {
    @Id //@GeneratedValue(strategy = GenerationType.IDENTITY)
    //@GenericGenerator(name = "uuid2",strategy = "org.hibernate.id.UUIDGenerator")
    //how can it work
    private String idStudent;
    @NotEmpty
    @Column(nullable = false,length = 20)
    private String firstname;
    @Column(nullable = false,length = 20)
    private String lastname;
    @Column(nullable = false,unique = true)
    @Pattern(regexp = "[a-zA-Z0-9\\-_]{5,}[@][a-zA-Z0-9\\-_]+(.[a-zA-Z0-9\\-_]+)+")
    //site : https://regexr.com/
    //valid email : aaaaa5_-@a-5.a5.ma5
    private String email;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    @Enumerated(value = EnumType.STRING)
    private Gender gender;
    private boolean good;
}
