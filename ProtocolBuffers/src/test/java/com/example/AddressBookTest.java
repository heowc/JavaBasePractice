package com.example;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class AddressBookTest {

    @Test
    public void test() {
        AddressBookProtos.Person john =
                AddressBookProtos.Person.newBuilder()
                        .setId(1234)
                        .setName("John Doe")
                        .setEmail("jdoe@example.com")
                        .addPhones(
                                AddressBookProtos.Person.PhoneNumber.newBuilder()
                                        .setNumber("555-4321")
                                        .setType(AddressBookProtos.Person.PhoneType.HOME))
                        .build();

        AddressBookProtos.AddressBook addressBook = AddressBookProtos.AddressBook.newBuilder().addPeople(john).buildPartial();

        assertThat(addressBook.getPeopleList().size(), is(1));
        assertThat(addressBook.getPeopleList().get(0).getId(), is(1234));
        assertThat(addressBook.getPeopleList().get(0).getName(), is("John Doe"));
        assertThat(addressBook.getPeopleList().get(0).getEmail(), is("jdoe@example.com"));
        assertThat(addressBook.getPeopleList().get(0).getPhonesCount(), is(1));
        assertThat(addressBook.getPeopleList().get(0).getPhones(0).getNumber(), is("555-4321"));
        assertThat(addressBook.getPeopleList().get(0).getPhones(0).getTypeValue(), is(AddressBookProtos.Person.PhoneType.HOME.getNumber()));
    }
}
