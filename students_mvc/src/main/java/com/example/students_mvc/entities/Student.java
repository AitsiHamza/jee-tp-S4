package com.example.students_mvc.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Student {
    @Id
    private String idStudent;
    @NotEmpty
    @Column(unique = true,length = 20)
    private String firstname;
    @Column(unique = true,length = 20)
    private String lastname;
    private String email;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    private Gender gender;
    private boolean enRegle;
}
