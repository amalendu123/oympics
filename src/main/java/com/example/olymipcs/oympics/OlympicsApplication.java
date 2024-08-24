package com.example.olymipcs.oympics;
import com.example.olymipcs.oympics.Entity.Athlete;
import com.example.olymipcs.oympics.Entity.Event;
import com.example.olymipcs.oympics.Entity.RegistrationEntity;
import com.example.olymipcs.oympics.Faker.SampleDataGenerator;
import com.example.olymipcs.oympics.Repository.AthleteRepository;
import com.example.olymipcs.oympics.Repository.EventRepository;
import com.example.olymipcs.oympics.Repository.RegisterRepository;
import com.example.olymipcs.oympics.Service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OlympicsApplication implements CommandLineRunner {

	@Autowired
	private AthleteRepository athleteRepository;

	@Autowired
	private EventRepository eventRepository;



	@Autowired
	private RegisterRepository registrationRepository;

	@Autowired
	private RegisterService registerService;
	public static void main(String[] args) {
		SpringApplication.run(OlympicsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Athlete athlete = SampleDataGenerator.generateSampleAthlete();
		Event event = SampleDataGenerator.generateSampleEventItem(2);
		RegistrationEntity registration = SampleDataGenerator.generateSampleRegistration(athlete, event);

		athleteRepository.save(athlete);
		eventRepository.save(event);
		registrationRepository.save(registration);


		registerService.registerAthlete(athlete,event.getId());
		System.out.println("Sample Athlete: " + athlete);
		System.out.println("Sample Event: " + event);
		System.out.println("Sample Registration: " + registration);
	}
}
