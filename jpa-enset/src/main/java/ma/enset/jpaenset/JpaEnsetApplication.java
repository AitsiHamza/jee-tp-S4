package ma.enset.jpaenset;

import ma.enset.jpaenset.entities.Role;
import ma.enset.jpaenset.entities.User;
import ma.enset.jpaenset.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
public class JpaEnsetApplication {

    public static void main(String[] args) {
        SpringApplication.run(JpaEnsetApplication.class, args);
    }

    @Bean
    CommandLineRunner start(UserService userService){
        return args -> {
            User user1=new User();
            user1.setUsername("user");
            user1.setPassword("azer");
            userService.addNewUser(user1);

            User user2=new User();
            user2.setUsername("admin");
            user2.setPassword("azer");
            userService.addNewUser(user2);

            Stream.of("Student","User","Admin").forEach(r->{
                Role role1=new Role();
                role1.setRoleName(r);
                userService.addNewRole(role1);
            });

            userService.addRoleToUser("user","Student");
            userService.addRoleToUser("user","User");

            userService.addRoleToUser("admin","Admin");
            userService.addRoleToUser("admin","User");

            try{
                User user=userService.authenticate("user","azer");
                System.out.println(user.getUserId());
                System.out.println(user.getUsername());
                user.getRoles().forEach(r->{
                    System.out.println("Roles=>"+r.toString());
                });
            }catch (Exception e){
                e.printStackTrace();
            }
        };
    }
}
