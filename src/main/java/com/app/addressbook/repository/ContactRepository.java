package com.app.addressbook.repository;

import com.app.addressbook.entities.AddressBook;
import com.app.addressbook.entities.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ContactRepository extends JpaRepository<Contact, Integer> {
    List<Contact> findByAddressBook(AddressBook addressBook);
    Optional<Contact> findByIdAndAddressBook(int id, AddressBook addressBook);
}
