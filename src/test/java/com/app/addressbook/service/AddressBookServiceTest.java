package com.app.addressbook.service;

import com.app.addressbook.entities.AddressBook;
import com.app.addressbook.model.ApiResponse;
import com.app.addressbook.repository.AddressBookRepository;
import com.app.addressbook.repository.ContactRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

public class AddressBookServiceTest {

    @InjectMocks
    private AddressBookService addressBookService;

    @Mock
    private AddressBookRepository addressBookRepository;

    @Mock
    private ContactRepository contactRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void addAddressBook() {
        ResponseEntity<Object> response = addressBookService.addAddressBook("retails");
        AddressBook addressBook = (AddressBook) response.getBody();
        assertEquals(response.getStatusCode().value(), 201);
        assertEquals(addressBook.getName(), "retails");
    }

    @Test
    public void getAllAddressBooks() {
        when(addressBookRepository.findAll()).thenReturn(TestData.getAddressBooks());
        ResponseEntity<Object> response = addressBookService.getAllAddressBooks();
        List<ApiResponse> responses = (List<ApiResponse>) response.getBody();
        assertEquals(response.getStatusCode().value(), 200);
        assertEquals(responses.get(0).getName(), "retails");
    }

    @Test
    public void getContactsByAddressBookId() {
        when(addressBookRepository.findById(anyInt())).thenReturn(TestData.getOptionalAddressBook());
        ResponseEntity<Object> response = addressBookService.getContactsByAddressBookId(10);
        ApiResponse apiResponse = (ApiResponse) response.getBody();
        assertEquals(response.getStatusCode().value(), 200);
        assertEquals(apiResponse.getName(), "retails");
    }

    @Test
    public void deleteAddressBook() {
        when(addressBookRepository.findById(anyInt())).thenReturn(TestData.getOptionalAddressBook());
        ResponseEntity<Object> response = addressBookService.deleteAddressBook(10);
        ApiResponse apiResponse = (ApiResponse) response.getBody();
        assertEquals(response.getStatusCode().value(), 200);
        assertEquals(apiResponse.getMessage(), "AddressBook deleted successfully");
    }
}