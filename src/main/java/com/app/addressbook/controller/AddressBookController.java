package com.app.addressbook.controller;

import com.app.addressbook.model.ApiRequest;
import com.app.addressbook.service.AddressBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/address-book")
public class AddressBookController {
    @Autowired
    private AddressBookService addressBookService;

    @GetMapping
    public ResponseEntity<Object> getAllAddressBooks() {
        return addressBookService.getAllAddressBooks();
    }

    @GetMapping("/{addressBookId}")
    public ResponseEntity<Object> getContactsByAddressBookId(@PathVariable int addressBookId) {
        return addressBookService.getContactsByAddressBookId(addressBookId);
    }

    @PostMapping
    public ResponseEntity<Object> addAddressBook(@Valid @RequestBody ApiRequest request) {
        return addressBookService.addAddressBook(request.getName());
    }

    @DeleteMapping("/{addressBookId}")
    public ResponseEntity<Object> deleteAddressBook(@PathVariable int addressBookId) {
        return addressBookService.deleteAddressBook(addressBookId);
    }
}
