package ma.enset.jpaap.repositories;

import ma.enset.jpaap.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface PatientRepository extends JpaRepository<Patient,Long> {
    @Query("select p from Patient p where p.name like :x and p.score<:y")
    List<Patient> chercherPatients(@Param("x") String name,@Param("y") int scoreMin);
}
