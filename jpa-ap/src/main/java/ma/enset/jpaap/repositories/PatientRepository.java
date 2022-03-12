package ma.enset.jpaap.repositories;

import ma.enset.jpaap.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface PatientRepository extends JpaRepository<Patient,Long> {
    @Query("select p from Patient p where p.birthDay between :x and :y or p.name like :z")
    List<Patient> chercherPatients(@Param("x") Date d1,@Param("y") Date d2,@Param("z") String name);
}
