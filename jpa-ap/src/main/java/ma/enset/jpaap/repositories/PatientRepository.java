package ma.enset.jpaap.repositories;

import ma.enset.jpaap.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface PatientRepository extends JpaRepository<Patient,Long> {
    List<Patient> findByBirthDayBetweenAndSickIsTrueOrNameLike(Date d1,Date d2,String name);
}
