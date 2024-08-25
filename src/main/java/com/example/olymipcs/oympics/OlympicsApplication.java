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
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
	@Transactional
	public void run(String... args) throws Exception {
		// Generate and save Athlete
		for (int i = 0; i < 100; i++) {
			Athlete athlete = SampleDataGenerator.generateSampleAthlete();
			athlete = athleteRepository.save(athlete);

			// Generate and save Event based on the Athlete's gender
			Event event = SampleDataGenerator.generateSampleEventItem(athlete);
			List<Event> existingEvents = eventRepository.findByName(event.getName());
			Event savedEvent;
			if (existingEvents.isEmpty()) {
				savedEvent = eventRepository.save(event);
			} else {
				savedEvent = existingEvents.get(0);
			}

			// Create and save RegistrationEntity
			RegistrationEntity registration = SampleDataGenerator.generateSampleRegistration(athlete, savedEvent);
			registration = registrationRepository.save(registration);

			// Register the athlete
			registerService.registerAthlete(athlete, savedEvent.getId());

			System.out.println("Sample Athlete: " + athlete);
			System.out.println("Sample Event: " + savedEvent);
			System.out.println("Sample Registration: " + registration);
		}
	}
}