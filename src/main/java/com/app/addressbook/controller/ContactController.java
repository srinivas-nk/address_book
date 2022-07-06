package com.app.addressbook.controller;

import com.app.addressbook.model.ApiRequest;
import com.app.addressbook.model.ExceptionResponse;
import com.app.addressbook.service.ContactService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.app.addressbook.constants.Constants.CONTACT_RESPONSE;
import static com.app.addressbook.constants.Constants.ERROR_RESPONSE;

@RestController
@RequestMapping("/v1/address-book")
public class ContactController {
    @Autowired
    private ContactService contactService;

    @ApiOperation(value = "Get all unique contacts", notes = "Returns all unique contacts")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved", examples = @Example(value = @ExampleProperty(mediaType = "application/json", value = CONTACT_RESPONSE))),
            @ApiResponse(code = 404, message = "Not found - The contact details were not found", examples = @Example(value = @ExampleProperty(mediaType = "application/json", value = ERROR_RESPONSE)))
    })
    @GetMapping("/all-unique-contacts")
    public ResponseEntity<Object> getAllContacts() {
        return contactService.getAllContacts();
    }

    @ApiOperation(value = "Get all contacts by AddressBookId", notes = "Returns all contact as per the AddressBookId")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved",examples = @Example(value = @ExampleProperty(mediaType = "application/json", value = CONTACT_RESPONSE))),
            @ApiResponse(code = 404, message = "Not found - The contact details were not found", examples = @Example(value = @ExampleProperty(mediaType = "application/json", value = ERROR_RESPONSE)))
    })
    @GetMapping("/{addressBookId}/contacts")
    public ResponseEntity<Object> getAllContactByAddressBookId(@PathVariable int addressBookId) {
        return contactService.getContactsByAddressBookId(addressBookId);
    }

    @ApiOperation(value = "Get contact by ContactId and AddressBookId", notes = "Returns contact as per the ContactId and AddressBookId")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved", examples = @Example(value = @ExampleProperty(mediaType = "application/json", value = CONTACT_RESPONSE))),
            @ApiResponse(code = 404, message = "Not found - The contact details were not found", examples = @Example(value = @ExampleProperty(mediaType = "application/json", value = ERROR_RESPONSE)))
    })
    @GetMapping("/{addressBookId}/contacts/{contactId}")
    public ResponseEntity<Object> getContactByIdAndAddressBookId(@PathVariable int addressBookId, @PathVariable int contactId) {
        return contactService.getContactByIdAndAddressBookId(addressBookId, contactId);
    }

    @ApiOperation(value = "Add contact to AddressBook", notes = "Returns contact details added to AddressBook")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created Successfully", examples = @Example(value = @ExampleProperty(mediaType = "application/json", value = CONTACT_RESPONSE)))
    })
    @PostMapping("/{addressBookId}/contacts")
    public ResponseEntity<Object> addContact(@Valid @RequestBody ApiRequest request, @PathVariable int addressBookId) {
        return contactService.addContact(request.getName(), request.getPhone(), addressBookId);
    }

    @ApiOperation(value = "Delete contact from AddressBook", notes = "Returns contact details deleted from AddressBook")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Deleted Successfully", examples = @Example(value = @ExampleProperty(mediaType = "application/json", value = CONTACT_RESPONSE))),
            @ApiResponse(code = 404, message = "Not found - The Contact details were not found", examples = @Example(value = @ExampleProperty(mediaType = "application/json", value = ERROR_RESPONSE)))
    })
    @DeleteMapping("/{addressBookId}/contacts/{contactId}")
    public ResponseEntity<Object> deleteByIdAndAddressBookId(@PathVariable int addressBookId, @PathVariable int contactId) {
        return contactService.deleteContact(addressBookId, contactId);
    }

    @ApiOperation(value = "Get all Common contacts", notes = "Returns all common contacts")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved", examples = @Example(value = @ExampleProperty(mediaType = "application/json", value = CONTACT_RESPONSE))),
            @ApiResponse(code = 404, message = "Not found - The contact details were not found", examples = @Example(value = @ExampleProperty(mediaType = "application/json", value = ERROR_RESPONSE)))
    })
    @GetMapping("/all-common-contacts")
    public ResponseEntity<Object> getAllCommonContacts() {
        return contactService.getCommonContacts();
    }

}
