package com.example.springcrudtutorial.user;

import com.example.springcrudtutorial.exceptions.UserNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;

    public List<User> listAll(){
        return (List<User>)userRepository.findAll();
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public User get(Integer id)throws UserNotFoundException {
        Optional<User> result= userRepository.findById(id);
        if(result.isPresent()){
            return result.get();
        }
        throw new UserNotFoundException("Could not find any users with ID "+id);
    }

    public void delete(Integer id) throws UserNotFoundException {
        Long count =userRepository.countById(id);
        if(count==null || count ==0){
            throw new UserNotFoundException("User id = "+id+" Couldn't be Found!");
        }
        userRepository.deleteById(id);
    }

}
