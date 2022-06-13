package com.app.addressbook.service;

import com.app.addressbook.entities.AddressBook;
import com.app.addressbook.entities.Contact;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TestData {
    public static List<AddressBook> getAddressBooks() {
        List<AddressBook> addressBooks = new ArrayList<>();
        addressBooks.add(getAddressBook("retails"));
        return addressBooks;
    }

    public static AddressBook getAddressBook(String name) {
        AddressBook addressBook = new AddressBook();
        addressBook.setName(name);
        addressBook.setId(1);
        return addressBook;
    }

    public static Optional<AddressBook> getOptionalAddressBook() {
        Optional<AddressBook> addressBook = Optional.ofNullable(getAddressBook("retails"));
        return addressBook;
    }
    public static List<Contact> getContacts() {
        List<Contact> contacts = new ArrayList<>();
        contacts.add(getContact("Customer","9812345678"));
        return contacts;
    }

    public static Contact getContact(String name, String phone) {
        Contact contact = new Contact();
        contact.setName(name);
        contact.setPhone(phone);
        return contact;
    }

    public static Optional<Contact> getOptionalContact() {
        Optional<Contact> contact = Optional.ofNullable(getContact("Customer","9812345678"));
        return contact;
    }
}
