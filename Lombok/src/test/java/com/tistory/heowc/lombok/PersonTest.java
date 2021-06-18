package com.tistory.heowc.lombok;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
class PersonTest {

	@Test
	void test_equalAndHashCode() throws Exception {
		log.info("test_equalAndHashCode");
		Person person = new Person();
		person.setFirstName("won chul");
		person.setLastName("heo");
		person.setDateOfBirth(LocalDate.of(1992, 7, 8));

		assertEquals(person, Person.getDefaultPerson());
	}

	@Test
	void test_builder() throws Exception {
		log.info("test_builder");
		Person person = Person.of("won chul",
				"heo",
				LocalDate.of(1992, 7, 8));
		assertEquals(person, Person.getDefaultPerson());
	}

	@Test
	void test_hashSet() throws Exception {
		log.info("test_hashSet");
		Set<Person> personSet = new HashSet<>();
		personSet.add(Person.getDefaultPerson());
		personSet.add(Person.getDefaultPerson());
		personSet.add(Person.getDefaultPerson());
		personSet.add(Person.getDefaultPerson());

		assertEquals(personSet.size(), 1);
	}
}
