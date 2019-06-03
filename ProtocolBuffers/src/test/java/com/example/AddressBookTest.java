package com.example;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class AddressBookTest {

    private AddressBookProtos.AddressBook getAddressBook() {
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

        return AddressBookProtos.AddressBook.newBuilder().addPeople(john).build();
    }

    private void assertThatAll(AddressBookProtos.AddressBook addressBook) {
        assertThat(addressBook.getPeopleList().size(), is(1));
        assertThat(addressBook.getPeopleList().get(0).getId(), is(1234));
        assertThat(addressBook.getPeopleList().get(0).getName(), is("John Doe"));
        assertThat(addressBook.getPeopleList().get(0).getEmail(), is("jdoe@example.com"));
        assertThat(addressBook.getPeopleList().get(0).getPhonesCount(), is(1));
        assertThat(addressBook.getPeopleList().get(0).getPhones(0).getNumber(), is("555-4321"));
        assertThat(addressBook.getPeopleList().get(0).getPhones(0).getTypeValue(), is(AddressBookProtos.Person.PhoneType.HOME.getNumber()));
    }

    @Test
    public void simple() {
        AddressBookProtos.AddressBook addressBook = getAddressBook();
        assertThatAll(addressBook);
    }

    @Test
    public void writeAndRead() throws IOException {
        AddressBookProtos.AddressBook outputAddressBook = getAddressBook();

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        outputAddressBook.writeTo(output);
        output.close();

        ByteArrayInputStream input = new ByteArrayInputStream(output.toByteArray());
        AddressBookProtos.AddressBook inputAddressBook = AddressBookProtos.AddressBook.parseFrom(input);

        assertThatAll(inputAddressBook);
    }

}
