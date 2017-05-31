package com.tistory.heowc.lombok;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class PersonTest {

    @Test
    public void test_equalAndHashCode() throws Exception {
        Person person = new Person();
        person.setFirstName("won chul");
        person.setLastName("heo");
        person.setDateOfBirth(LocalDate.of(1992, 7, 8));

        Assert.assertEquals(person, Person.getDefaultPerson());
    }

    @Test
    public void test_builder() throws Exception {
        Person person = Person.of("won chul",
                                    "heo",
                                    LocalDate.of(1992, 7, 8));
        Assert.assertEquals(person, Person.getDefaultPerson());
    }

    @Test
    public void test_hashSet() throws Exception {
        Set<Person> personSet = new HashSet<>();
        personSet.add(Person.getDefaultPerson());
        personSet.add(Person.getDefaultPerson());
        personSet.add(Person.getDefaultPerson());
        personSet.add(Person.getDefaultPerson());

        Assert.assertEquals(personSet.size(), 1);
    }
}
