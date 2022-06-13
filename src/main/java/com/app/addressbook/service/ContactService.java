package com.app.addressbook.service;

import com.app.addressbook.entities.AddressBook;
import com.app.addressbook.entities.Contact;
import com.app.addressbook.exception.ResourceNotFoundException;
import com.app.addressbook.model.ContactResponse;
import com.app.addressbook.repository.AddressBookRepository;
import com.app.addressbook.repository.ContactRepository;
import com.app.addressbook.utils.ConverterUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.app.addressbook.constants.Constants.CONTACT_NOT_FOUND;
import static com.app.addressbook.constants.Constants.ADDRESS_BOOK_NOT_FOUND;
import static com.app.addressbook.constants.Constants.CONTACT_DELETED;

@Service
public class ContactService {
    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private AddressBookRepository addressBookRepository;

    public ResponseEntity<Object> getAllContacts() {
        List<Contact> contacts = contactRepository.findAll();
        if(contacts.isEmpty()) {
            throw new ResourceNotFoundException(CONTACT_NOT_FOUND);
        }
        return new ResponseEntity<>(ConverterUtil.uniqueContacts(contacts), HttpStatus.OK);
    }

    public ResponseEntity<Object> getContactByIdAndAddressBookId(int addressBookId, int contactId) {
        Optional<AddressBook> addressBook = addressBookRepository.findById(addressBookId);
        if(addressBook.isPresent()) {
            Optional<Contact> contact = contactRepository.findByIdAndAddressBook(contactId, addressBook.get());
            if (contact.isPresent()) {
                return new ResponseEntity<>(ConverterUtil.contactResponseConverter(contact.get()), HttpStatus.OK);
            }
            throw new ResourceNotFoundException(CONTACT_NOT_FOUND);
        }
        throw new ResourceNotFoundException(ADDRESS_BOOK_NOT_FOUND);
    }

    public ResponseEntity<Object> addContact(String name, String phone, int addressBookId) {
        Contact contact = new Contact();
        contact.setName(name);
        contact.setPhone(phone);
        Optional<AddressBook> addressBook = addressBookRepository.findById(addressBookId);
        if(addressBook.isPresent()) {
            contact.setAddressBook(addressBook.get());
            contactRepository.save(contact);
            return new ResponseEntity<>(ConverterUtil.contactResponseConverter(contact),
                    HttpStatus.CREATED);
        }
        throw new ResourceNotFoundException(ADDRESS_BOOK_NOT_FOUND);
    }

    public ResponseEntity<Object> deleteContact(int addressBookId, int contactId) {
        Optional<AddressBook> addressBook = addressBookRepository.findById(addressBookId);
        if(addressBook.isPresent()) {
            Optional<Contact> contact = contactRepository.findByIdAndAddressBook(contactId, addressBook.get());
            if (contact.isPresent()) {
                contactRepository.delete(contact.get());
                ContactResponse response = ConverterUtil.contactResponseConverter(contact.get());
                response.setMessage(CONTACT_DELETED);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            throw new ResourceNotFoundException(CONTACT_NOT_FOUND);
        }
        throw new ResourceNotFoundException(ADDRESS_BOOK_NOT_FOUND);
    }
}
