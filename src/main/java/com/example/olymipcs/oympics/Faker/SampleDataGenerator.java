package com.example.olymipcs.oympics.Faker;
import com.example.olymipcs.oympics.Entity.Athlete;
import com.example.olymipcs.oympics.Entity.Event;
import com.example.olymipcs.oympics.Entity.RegistrationEntity;
import com.github.javafaker.Faker;

import java.util.Locale;
import java.util.UUID;

public class SampleDataGenerator {
    private static final UUID[] EVENT_UUIDS = new UUID[] {
            UUID.fromString("123e4567-e89b-12d3-a456-426614174000"),
            UUID.fromString("123e4567-e89b-12d3-a456-426614174001"),
            UUID.fromString("123e4567-e89b-12d3-a456-426614174002"),
            UUID.fromString("123e4567-e89b-12d3-a456-426614174003"),
            UUID.fromString("123e4567-e89b-12d3-a456-426614174004"),
            UUID.fromString("123e4567-e89b-12d3-a456-426614174005"),
            UUID.fromString("123e4567-e89b-12d3-a456-426614174006"),
            UUID.fromString("123e4567-e89b-12d3-a456-426614174007"),
            UUID.fromString("123e4567-e89b-12d3-a456-426614174008"),
            UUID.fromString("123e4567-e89b-12d3-a456-426614174009")
    };
    private static final String[] EVENT_NAMES = new String[] {
            "Running",
            "Long Jump",
            "100m Sprint",
            "High Jump",
            "Pole Vault",
            "Marathon",
            "1500m",
            "Discus Throw",
            "Javelin Throw",
            "Shot Put"
    };
    public static Athlete generateSampleAthlete() {
        Faker faker = new Faker(new Locale("en-US"));

        return Athlete.builder()
                .id(UUID.randomUUID())
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .age(faker.number().numberBetween(18, 40))
                .gender(faker.options().option("Male", "Female"))
                .countryCode(faker.address().countryCode())
                .build();
    }

    public static Event generateSampleEventItem(int index) {
        if (index < 0 || index >= EVENT_UUIDS.length) {
            throw new IllegalArgumentException("Invalid index for event UUID");
        }

        return Event.builder()
                .id(EVENT_UUIDS[index])
                .name(EVENT_NAMES[index])
                .build();
    }

    public static RegistrationEntity generateSampleRegistration(Athlete athlete, Event event) {
        return RegistrationEntity.builder()
                .id(UUID.randomUUID())
                .athlete(athlete)
                .event(event)
                .build();
    }



}
