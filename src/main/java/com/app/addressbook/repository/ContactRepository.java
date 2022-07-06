package com.app.addressbook.repository;

import com.app.addressbook.entities.AddressBook;
import com.app.addressbook.entities.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ContactRepository extends JpaRepository<Contact, Integer> {
    List<Contact> findByAddressBook(AddressBook addressBook);
    Optional<Contact> findByIdAndAddressBook(int id, AddressBook addressBook);

    @Query("select min(user1.id), user1.name, user1.phone " +
            "from Contact user1 " +
            "join Contact user2 " +
            "on user1.addressBook != user2.addressBook " +
            "and user1.name = user2.name " +
            "and user1.phone = user2.phone " +
            "group by user1.name, user1.phone " +
            "having count(*) > 1")
    List<Object> findCommonContacts();
}
