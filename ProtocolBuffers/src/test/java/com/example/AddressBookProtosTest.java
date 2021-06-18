package com.example;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

class AddressBookProtosTest {

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
        assertThat(addressBook.getPeopleList().size()).isEquals(1);
        assertThat(addressBook.getPeopleList().get(0).getId()).isEquals(1234);
        assertThat(addressBook.getPeopleList().get(0).getName()).isEquals("John Doe");
        assertThat(addressBook.getPeopleList().get(0).getEmail()).isEquals("jdoe@example.com");
        assertThat(addressBook.getPeopleList().get(0).getPhonesCount()).isEquals(1);
        assertThat(addressBook.getPeopleList().get(0).getPhones(0).getNumber()).isEquals("555-4321");
        assertThat(addressBook.getPeopleList().get(0).getPhones(0).getTypeValue()).isEquals(AddressBookProtos.Person.PhoneType.HOME.getNumber());
    }

    @Test
    void simple() {
        AddressBookProtos.AddressBook addressBook = getAddressBook();
        assertThatAll(addressBook);
    }

    @Test
    void writeAndRead() throws IOException {
        AddressBookProtos.AddressBook outputAddressBook = getAddressBook();

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        outputAddressBook.writeTo(output);
        output.close();

        ByteArrayInputStream input = new ByteArrayInputStream(output.toByteArray());
        AddressBookProtos.AddressBook inputAddressBook = AddressBookProtos.AddressBook.parseFrom(input);

        assertThatAll(inputAddressBook);
    }

}
