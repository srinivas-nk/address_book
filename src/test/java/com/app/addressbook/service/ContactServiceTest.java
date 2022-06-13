package com.app.addressbook.service;

import com.app.addressbook.exception.ResourceNotFoundException;
import com.app.addressbook.model.ContactResponse;
import com.app.addressbook.repository.AddressBookRepository;
import com.app.addressbook.repository.ContactRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

public class ContactServiceTest {

    @InjectMocks
    private ContactService contactService;

    @Mock
    private AddressBookRepository addressBookRepository;

    @Mock
    private ContactRepository contactRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllContacts() {
        when(contactRepository.findAll()).thenReturn(TestData.getContacts());
        ResponseEntity<Object> response = contactService.getAllContacts();
        Set<ContactResponse> contacts = (Set<ContactResponse>) response.getBody();
        assertEquals(response.getStatusCode().value(), 200);
        assertEquals(contacts.size(), 1);
    }

    @Test
    public void getContactByIdAndAddressBookId() {
        when(addressBookRepository.findById(anyInt())).thenReturn(TestData.getOptionalAddressBook());
        when(contactRepository.findByIdAndAddressBook(anyInt(),any())).thenReturn(TestData.getOptionalContact());
        ResponseEntity<Object> response = contactService.getContactByIdAndAddressBookId(10,100);
        ContactResponse contactResponse = (ContactResponse) response.getBody();
        assertEquals(response.getStatusCode().value(), 200);
        assertEquals(contactResponse.getName(), "Customer");
    }

    @Test(expected = ResourceNotFoundException.class)
    public void getContactByIdAndAddressBookIdNotFound() {
        when(addressBookRepository.findById(anyInt())).thenReturn(TestData.getOptionalAddressBook());
        when(contactRepository.findByIdAndAddressBook(anyInt(),any())).thenReturn(Optional.empty());
        ResponseEntity<Object> response = contactService.getContactByIdAndAddressBookId(10,100);
    }

    @Test
    public void addContact() {
        when(addressBookRepository.findById(anyInt())).thenReturn(TestData.getOptionalAddressBook());
        ResponseEntity<Object> response = contactService.addContact("Customer","9812345678", 100);
        ContactResponse contactResponse = (ContactResponse) response.getBody();
        assertEquals(response.getStatusCode().value(), 201);
        assertEquals(contactResponse.getName(), "Customer");
        assertEquals(contactResponse.getPhone(), "9812345678");
    }

    @Test
    public void deleteContact() {
        when(addressBookRepository.findById(anyInt())).thenReturn(TestData.getOptionalAddressBook());
        when(contactRepository.findByIdAndAddressBook(anyInt(),any())).thenReturn(TestData.getOptionalContact());
        ResponseEntity<Object> response = contactService.deleteContact(10, 100);
        ContactResponse contactResponse = (ContactResponse) response.getBody();
        assertEquals(response.getStatusCode().value(), 200);
        assertEquals(contactResponse.getMessage(), "Contact deleted successfully");
    }
}