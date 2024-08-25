package com.example.olymipcs.oympics.Faker;

import com.example.olymipcs.oympics.Entity.Athlete;
import com.example.olymipcs.oympics.Entity.Event;
import com.example.olymipcs.oympics.Entity.RegistrationEntity;
import com.github.javafaker.Faker;

import java.util.*;

public class SampleDataGenerator {
    private static final List<Locale> LOCALES = Arrays.asList(
            Locale.US, Locale.UK, Locale.FRANCE, Locale.GERMANY, Locale.CANADA,
            new Locale("es", "ES"), new Locale("pt", "BR"), new Locale("it", "IT")
    );

    private static final String[] EVENT_NAMES = {
            "Running", "Long Jump", "100m Sprint", "High Jump", "Pole Vault",
            "Marathon", "1500m", "Discus Throw", "Javelin Throw", "Shot Put"
    };

    private static final Map<String, Event> eventCache = new HashMap<>();

    public static Faker getRandomFaker() {
        Locale randomLocale = LOCALES.get((int) (Math.random() * LOCALES.size()));
        return new Faker(randomLocale);
    }

    public static Athlete generateSampleAthlete() {
        Faker faker = getRandomFaker();
        return Athlete.builder()
                .id(UUID.randomUUID())
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .age(faker.number().numberBetween(18, 40))
                .gender(faker.options().option("Male", "Female"))
                .countryCode(faker.address().countryCode())
                .build();
    }

    public static Event getOrCreateEvent(String eventName) {
        return eventCache.computeIfAbsent(eventName, name ->
                Event.builder()
                        .id(UUID.randomUUID())
                        .name(name)
                        .build()
        );
    }

    public static Event generateSampleEventItem() {
        String randomEventName = EVENT_NAMES[(int) (Math.random() * EVENT_NAMES.length)];
        return getOrCreateEvent(randomEventName);
    }

    public static RegistrationEntity generateSampleRegistration(Athlete athlete, Event event) {
        return RegistrationEntity.builder()
                .id(UUID.randomUUID())
                .athlete(athlete)
                .event(event)
                .build();
    }
}