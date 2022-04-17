package com.example.springcrudtutorial.user;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;

    public List<User> listAll(){
        return (List<User>)userRepository.findAll();
    }
}
