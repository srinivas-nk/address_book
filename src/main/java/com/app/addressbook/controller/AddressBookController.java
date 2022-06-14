package com.app.addressbook.controller;

import com.app.addressbook.model.ApiRequest;
import com.app.addressbook.service.AddressBookService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.app.addressbook.constants.Constants.ADDRESS_BOOK_RESPONSE;
import static com.app.addressbook.constants.Constants.ERROR_RESPONSE;

@RestController
@RequestMapping("/v1/address-book")
public class    AddressBookController {
    @Autowired
    private AddressBookService addressBookService;

    @ApiOperation(value = "Get all AddressBook details", notes = "Returns all AddressBook and their contacts")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved", examples = @Example(value = @ExampleProperty(mediaType = "application/json", value = ADDRESS_BOOK_RESPONSE))),
            @ApiResponse(code = 404, message = "Not found - The AddressBook details were not found", examples = @Example(value = @ExampleProperty(mediaType = "application/json", value = ERROR_RESPONSE)))
    })
    @GetMapping
    public ResponseEntity<Object> getAllAddressBooks() {
        return addressBookService.getAllAddressBooks();
    }

    @ApiOperation(value = "Get all AddressBook details by addressBookId", notes = "Returns all AddressBook and their contacts by addressBookId")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved", examples = @Example(value = @ExampleProperty(mediaType = "application/json", value = ADDRESS_BOOK_RESPONSE))),
            @ApiResponse(code = 404, message = "Not found - The AddressBook details were not found", examples = @Example(value = @ExampleProperty(mediaType = "application/json", value = ERROR_RESPONSE)))
    })
    @GetMapping("/{addressBookId}")
    public ResponseEntity<Object> getContactsByAddressBookId(@PathVariable int addressBookId) {
        return addressBookService.getContactsByAddressBookId(addressBookId);
    }

    @ApiOperation(value = "create AddressBook", notes = "Returns AddressBook details")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created Successfully", examples = @Example(value = @ExampleProperty(mediaType = "application/json", value = ADDRESS_BOOK_RESPONSE)))
    })
    @PostMapping
    public ResponseEntity<Object> addAddressBook(@Valid @RequestBody ApiRequest request) {
        return addressBookService.addAddressBook(request.getName());
    }

    @ApiOperation(value = "delete AddressBook by addressBookId", notes = "Returns deleted AddressBook details")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Deleted Successfully", examples = @Example(value = @ExampleProperty(mediaType = "application/json", value = ADDRESS_BOOK_RESPONSE))),
            @ApiResponse(code = 404, message = "Not found - The AddressBook details were not found", examples = @Example(value = @ExampleProperty(mediaType = "application/json", value = ERROR_RESPONSE)))
    })
    @DeleteMapping("/{addressBookId}")
    public ResponseEntity<Object> deleteAddressBook(@PathVariable int addressBookId) {
        return addressBookService.deleteAddressBook(addressBookId);
    }
}
