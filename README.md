# Address Book API

## Features

- Address book should hold name and phone numbers of contact entries
- Users should be able to add a new contact entry to an existing address book
- Users should be able to remove an existing contact entry from an existing address book
- Users should be able to retrieve all contacts in an address book
- Users should be able to retrieve a unique set of all contacts across multiple address books

## Getting Started

### Build:
```sh
mvn clean install
```

### Start:
```sh
mvn spring-boot:run
```

## REST API Endpoints
Get All Contact details from AddressBook
```
GET http://localhost:8080/v1/address-book/{addressBookId}/contacts
```

Get Contact details by id from AddressBook
```
GET http://localhost:8080/v1/address-book/{addressBookId}/contacts/{contactId}
```

Add Contact details to AddressBook
```
POST http://localhost:8080/v1/address-book/{addressBookId}/contacts

Payload:
{
    "name":"Customer",
    "phone": "9345678911"
}
```

Delete Contact details by id from AddressBook
```
DELETE http://localhost:8080/v1/address-book/{addressBookId}/contacts/{contactId}
```

Get All Unique contact details
```
GET http://localhost:8080/v1/address-book/all-unique-contacts
```

Get all AddressBook details 
```
GET http://localhost:8080/v1/address-book
```

Get AddressBook details by Id
```
GET http://localhost:8080/v1/address-book/{addressBookId}
```

Add AddressBook
```
POST http://localhost:8080/v1/address-book

Payload:
{
    "name":"amazon"
}
```

Delete AddressBook
```
DELETE http://localhost:8080/v1/address-book/{addressBookId}
```