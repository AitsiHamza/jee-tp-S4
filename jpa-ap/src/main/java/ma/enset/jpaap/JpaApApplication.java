package ma.enset.jpaap;

import ma.enset.jpaap.entities.Patient;
import ma.enset.jpaap.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class JpaApApplication implements CommandLineRunner {
    @Autowired
    private PatientRepository patientRepository;

    public static void main(String[] args) {
        SpringApplication.run(JpaApApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        for (int i = 0; i < 100; i++) {
            patientRepository.save(new Patient(null, "azer", new Date(), (Math.random()>0.5)?true:false, 56));
        }
        /**find all isn't always recommended*/
        /**we can use the pagination*/
        Page<Patient> patients = patientRepository.findAll(PageRequest.of(0,5));
        patients.forEach(p->{
            System.out.println("==============================");
            System.out.println(p.getId());
            System.out.println(p.getName());
            System.out.println(p.isSick());
        });
    }
}
