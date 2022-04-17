package com.example.springcrudtutorial.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="users")
@Data @AllArgsConstructor @NoArgsConstructor
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false,unique=true,length = 45)
    private String email;
    @Column(length = 15,nullable = false)
    private String password;
    @Column(length = 45,nullable = false,name = "first_name")
    private String firstname;
    @Column(length = 15,nullable = false,name = "last_name")
    private String lastname;
}
