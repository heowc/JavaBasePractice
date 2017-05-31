package com.tistory.heowc.lombok;

import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@Builder
@EqualsAndHashCode(exclude = {"address", "city"})
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    @Getter(AccessLevel.PRIVATE)
    private String firstName;

    private String lastName;

    private String address;

    private String city;

    private LocalDate dateOfBirth;

    public static Person of(String firstName, String lastName, LocalDate localDate) {
        return Person.builder()
                .firstName(firstName)
                .lastName(lastName)
                .dateOfBirth(localDate)
                .build();
    }

    public static Person getDefaultPerson() {
        Person person = new Person();
        person.setFirstName("won chul");
        person.setLastName("heo");
        person.setAddress("South Korea");
        person.setCity("Seoul");
        person.setDateOfBirth(LocalDate.of(1992, 7, 8));
        return person;
    }
}
