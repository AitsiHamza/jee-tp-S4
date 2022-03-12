package ma.enset.jpaap;

import ma.enset.jpaap.entities.Patient;
import ma.enset.jpaap.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.text.SimpleDateFormat;
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
        Date d1=new Date();
        for (int i = 0; i < 100; i++) {
            patientRepository.save(new Patient(null, "azer", new Date(), (Math.random()>0.5)?true:false, (int)(Math.random()*100)));
        }
        Date d2=new Date();
        List<Patient> patients = patientRepository.findByBirthDayBetweenAndSickIsTrueOrNameLike(d1,d2,"%z%");
        patients.forEach(p->{
            System.out.println("==============================");
            System.out.println(p.getId());
            System.out.println(p.getName());
            System.out.println(p.isSick());
        });
    }
}
