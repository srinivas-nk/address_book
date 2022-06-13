package com.app.addressbook.service;

import com.app.addressbook.entities.AddressBook;
import com.app.addressbook.entities.Contact;
import com.app.addressbook.exception.ResourceNotFoundException;
import com.app.addressbook.model.ApiResponse;
import com.app.addressbook.model.ContactResponse;
import com.app.addressbook.repository.AddressBookRepository;
import com.app.addressbook.repository.ContactRepository;
import com.app.addressbook.utils.ConverterUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.app.addressbook.constants.Constants.ADDRESS_BOOK_NOT_FOUND;
import static com.app.addressbook.constants.Constants.ADDRESS_BOOK_DELETED;

@Service
public class AddressBookService {
    @Autowired
    private AddressBookRepository addressBookRepository;

    @Autowired
    private ContactRepository contactRepository;

    public ResponseEntity<Object> addAddressBook(String name) {
        AddressBook addressBook = new AddressBook();
        addressBook.setName(name);
        addressBookRepository.save(addressBook);
        return new ResponseEntity<>(addressBook, HttpStatus.CREATED) ;
    }

    public ResponseEntity<Object> getAllAddressBooks() {
        List<AddressBook> addressBooks = addressBookRepository.findAll();
        if(addressBooks.isEmpty()) {
            throw new ResourceNotFoundException(ADDRESS_BOOK_NOT_FOUND);
        }
        List<ApiResponse> responses = new ArrayList<>();
        for(AddressBook addressBook : addressBooks) {
            ApiResponse response = ConverterUtil.getApiResponse(addressBook);
            response.getContacts().addAll(convertContacts(addressBook));
            responses.add(response);
        }
        return new ResponseEntity<>(responses, HttpStatus.OK) ;
    }

    public List<ContactResponse> convertContacts(AddressBook addressBook) {
        List<Contact> contacts = contactRepository.findByAddressBook(addressBook);
        return ConverterUtil.contactResponseConverter(contacts);
    }

    public ResponseEntity<Object> getContactsByAddressBookId(int addressBookId) {
        Optional<AddressBook> addressBook = addressBookRepository.findById(addressBookId);
        if(addressBook.isPresent()) {
            ApiResponse response = ConverterUtil.getApiResponse(addressBook.get());
            response.getContacts().addAll(convertContacts(addressBook.get()));
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        throw new ResourceNotFoundException(ADDRESS_BOOK_NOT_FOUND);
    }

    public ResponseEntity<Object> deleteAddressBook(int addressBookId) {
        Optional<AddressBook> addressBook = addressBookRepository.findById(addressBookId);
        if(addressBook.isPresent()) {
            ApiResponse response = ConverterUtil.getApiResponse(addressBook.get());
            response.setMessage(ADDRESS_BOOK_DELETED);
            response.getContacts().addAll(convertContacts(addressBook.get()));
            addressBookRepository.deleteById(addressBookId);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        throw new ResourceNotFoundException(ADDRESS_BOOK_NOT_FOUND);
    }
}
