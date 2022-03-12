package ma.enset.hospital.repositoties;

import ma.enset.hospital.entities.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultationRepository  extends JpaRepository<Consultation,Long> {
}
