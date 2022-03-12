package ma.enset.hospital;

import ma.enset.hospital.entities.*;
import ma.enset.hospital.repositoties.ConsultationRepository;
import ma.enset.hospital.repositoties.MedecinRepository;
import ma.enset.hospital.repositoties.PatientRepository;
import ma.enset.hospital.repositoties.RendezVousRepository;
import ma.enset.hospital.service.IHospitalService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.stream.Stream;

@SpringBootApplication
public class HospitalApplication {

	public static void main(String[] args) {
		SpringApplication.run(HospitalApplication.class, args);
	}

	@Bean
	CommandLineRunner start(IHospitalService hospitalService,
							PatientRepository patientRepository,
							RendezVousRepository rendezVousRepository,
							MedecinRepository medecinRepository){
		return args -> {
			//patientRepository.save(new Patient(null,"Hassan",new Date(),false,null));
			Stream.of("Mohammed","Amine","Aitsi")
					.forEach(name->{
						Patient patient=new Patient();
						patient.setName(name);
						patient.setBirthDay(new Date());
						patient.setSick(false);
						hospitalService.savePatient(patient);
					});
			Stream.of("3a","na","tiz")
					.forEach(name->{
						Medecin medecin =new Medecin();
						medecin.setName(name);
						medecin.setSpecialite(Math.random()>0.5?"Cardio":"Dentiste");
						medecin.setEmail(name+"@gmail.com");
						hospitalService.saveMedecin(medecin);
					});
			Patient patient=patientRepository.findById(1L).orElse(null);
			Patient patient1=patientRepository.findByName("Mohammed");

			Medecin medecin= medecinRepository.findByName("na");

			RendezVous rendezVous=new RendezVous();
			rendezVous.setDate(new Date());
			rendezVous.setStatus(StatusRDV.PENDING);
			rendezVous.setMedecin(medecin);
			rendezVous.setPatient(patient);
			RendezVous savedRDV = hospitalService.saveRDV(rendezVous);
			System.out.println(savedRDV.getId());
			/**It is frequent that we retrun the object that has been registred,
			 * because Allah alone khows when we're going to need it!*/

			RendezVous rendezVous1=rendezVousRepository.findAll().get(0);
			Consultation consultation=new Consultation();
			consultation.setDateConsultation(new Date());
			consultation.setRendezVous(rendezVous1);
			consultation.setRapport("Rapport de la consultation .......");
			hospitalService.saveConsultation(consultation);
		};
	}

}
