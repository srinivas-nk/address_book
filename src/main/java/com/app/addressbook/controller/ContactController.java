package com.app.addressbook.controller;

import com.app.addressbook.model.ApiRequest;
import com.app.addressbook.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/address-book")
public class ContactController {
    @Autowired
    private ContactService contactService;

    @GetMapping("/all-unique-contacts")
    public ResponseEntity<Object> getAllContacts() {
        return contactService.getAllContacts();
    }

    @GetMapping("/{addressBookId}/contacts")
    public ResponseEntity<Object> getAllContactByAddressBookId(@PathVariable int addressBookId) {
        return contactService.getContactsByAddressBookId(addressBookId);
    }

    @GetMapping("/{addressBookId}/contacts/{contactId}")
    public ResponseEntity<Object> getContactByIdAndAddressBookId(@PathVariable int addressBookId, @PathVariable int contactId) {
        return contactService.getContactByIdAndAddressBookId(addressBookId, contactId);
    }

    @PostMapping("/{addressBookId}/contacts")
    public ResponseEntity<Object> addContact(@Valid @RequestBody ApiRequest request, @PathVariable int addressBookId) {
        return contactService.addContact(request.getName(), request.getPhone(), addressBookId);
    }

    @DeleteMapping("/{addressBookId}/contacts/{contactId}")
    public ResponseEntity<Object> deleteByIdAndAddressBookId(@PathVariable int addressBookId, @PathVariable int contactId) {
        return contactService.deleteContact(addressBookId, contactId);
    }
}
