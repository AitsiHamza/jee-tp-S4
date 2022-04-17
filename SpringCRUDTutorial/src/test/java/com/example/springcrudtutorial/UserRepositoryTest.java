package com.example.springcrudtutorial;

import com.example.springcrudtutorial.user.User;
import com.example.springcrudtutorial.user.UserRepository;
import lombok.AllArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
/*@AllArgsContructor didn't work here!??!*/
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void testAddNew(){
        User user=new User();
        String s= String.valueOf (Math.random()*1000);
        user.setEmail(s+"@000.sdkb");
        user.setPassword("kfb5454f");
        user.setFirstname("kfbe");
        user.setLastname("sdkb");

        User savedUser= userRepository.save(user);

        Assertions.assertThat(savedUser).isNotNull();
        Assertions.assertThat(savedUser.getId()).isGreaterThan(0);

    }

    @Test
    public void testListAll(){
        Iterable<User> users=userRepository.findAll();
        Assertions.assertThat(users).hasSizeGreaterThan(0);

        for (User user : users) {
            System.out.println(user.toString());
        }
    }

    @Test
    public void testUpdate(){
        Integer userId=1;
        Optional<User> userOptional=userRepository.findById(userId);
        userRepository.findById(userId);
        User user=userOptional.get();
        user.setLastname("65");
        User updatedUser=userRepository.save(user);

        Assertions.assertThat(updatedUser.getId()).isGreaterThan(0);
    }

    @Test
    public void testGet(){
        Integer userId=4;
        Optional<User> optionalUser=userRepository.findById(userId);
        Assertions.assertThat(optionalUser.get());
    }

    @Test
    public void testDelete(){
        Integer userId=2;
        userRepository.deleteById(userId);
        Optional<User> optionalUser=userRepository.findById(userId);
        Assertions.assertThat(optionalUser).isNotPresent();
    }

}
