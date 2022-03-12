package ma.enset.jpaap;

import ma.enset.jpaap.entities.Patient;
import ma.enset.jpaap.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
        patientRepository.save(new Patient(null,"fatima",new Date(),false,56));
        patientRepository.save(new Patient(null,"brahim",new Date(),false,56));
        patientRepository.save(new Patient(null,"meriem",new Date(),false,56));
        List<Patient> patients = patientRepository.findAll();
        patients.forEach(p->{
            System.out.println(p.getId());
            System.out.println(p.getName());
            System.out.println(p.getScore());
            System.out.println(p.getBirthDay());
            System.out.println(p.isSick());
        });
        System.out.println("********************************");
        Patient patient=patientRepository.findById(1L).orElse(null);
        if(patient!=null){
            System.out.println(patient.getName());
            System.out.println(patient.isSick());
        }
        patient.setScore(500);
        patientRepository.save(patient);
        /** save execute insert if the is null, else it execute update*/
    }
}
