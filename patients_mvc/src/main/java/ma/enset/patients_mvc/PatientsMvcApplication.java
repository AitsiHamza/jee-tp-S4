package ma.enset.patients_mvc;

import ma.enset.patients_mvc.entities.Patient;
import ma.enset.patients_mvc.repositories.PatientRepository;
import ma.enset.patients_mvc.sec.service.SecurityService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class PatientsMvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(PatientsMvcApplication.class, args);
    }

    //@Bean
    CommandLineRunner commandLineRunner(PatientRepository patientRepository){
        return args -> {
            patientRepository.save(
                    new Patient(null,"hamza",new Date(),false,20));
            patientRepository.save(
                    new Patient(null,"azeze",new Date(),false,20));
            patientRepository.save(
                    new Patient(null,"zdza",new Date(),false,20));
            patientRepository.save(
                    new Patient(null,"zedze",new Date(),true,20));

            /* patientRepository.findAll().forEach(p->{
                System.out.println(p.getNom());
            });*/
        };
    }

    //@Bean
    CommandLineRunner saveUsers(SecurityService securityService){
        return args -> {
            System.out.println("uuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuu");
            securityService.saveNewUser("hamza2","1111","1111");
            System.out.println("uuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuu");
            securityService.saveNewUser("mohammed2","1111","1111");
            //System.out.println("rrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr");
            //securityService.saveNewRole("USER","");
            //securityService.saveNewRole("ADMIN","");
            System.out.println("urururururururururururururururururuurur");

            securityService.addRoleToUser("hamza2","ADMIN");
            securityService.addRoleToUser("hamza2","USER");
            securityService.addRoleToUser("mohammed2","USER");
        };
    }
}
