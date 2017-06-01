package com.tistory.heowc.lombok;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Slf4j
public class PersonTest {

    @Test
    public void test_equalAndHashCode() throws Exception {
        log.info("test_equalAndHashCode");
        Person person = new Person();
        person.setFirstName("won chul");
        person.setLastName("heo");
        person.setDateOfBirth(LocalDate.of(1992, 7, 8));

        Assert.assertEquals(person, Person.getDefaultPerson());
    }

    @Test
    public void test_builder() throws Exception {
        log.info("test_builder");
        Person person = Person.of("won chul",
                                    "heo",
                                    LocalDate.of(1992, 7, 8));
        Assert.assertEquals(person, Person.getDefaultPerson());
    }

    @Test
    public void test_hashSet() throws Exception {
        log.info("test_hashSet");
        Set<Person> personSet = new HashSet<>();
        personSet.add(Person.getDefaultPerson());
        personSet.add(Person.getDefaultPerson());
        personSet.add(Person.getDefaultPerson());
        personSet.add(Person.getDefaultPerson());

        Assert.assertEquals(personSet.size(), 1);
    }
}
