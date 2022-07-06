package com.app.addressbook.utils;

import com.app.addressbook.entities.AddressBook;
import com.app.addressbook.entities.Contact;
import com.app.addressbook.model.ApiResponse;
import com.app.addressbook.model.ContactResponse;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ConverterUtil {

    public static List<ContactResponse> contactResponseConverter(List<Contact> contacts) {
        List<ContactResponse> responses = new ArrayList<>();
        for(Contact contact : contacts) {
            responses.add(contactResponseConverter(contact));
        }
        return responses;
    }

    public static ContactResponse contactResponseConverter(Contact contact) {
        ContactResponse contactResponse = new ContactResponse();
        contactResponse.setId(contact.getId());
        contactResponse.setPhone(contact.getPhone());
        contactResponse.setName(contact.getName());
        return contactResponse;
    }

    public static ContactResponse contactResponseConverter(int id, String name, String phone) {
        ContactResponse contactResponse = new ContactResponse();
        contactResponse.setId(id);
        contactResponse.setPhone(phone);
        contactResponse.setName(name);
        return contactResponse;
    }

    public static ApiResponse getApiResponse(AddressBook addressBook) {
        ApiResponse response = new ApiResponse();
        response.setId(addressBook.getId());
        response.setName(addressBook.getName());
        return response;
    }

    public static Set<ContactResponse> uniqueContacts(List<Contact> contacts) {
        Set<ContactResponse> responses = new HashSet<>();
        for(Contact contact : contacts) {
            responses.add(contactResponseConverter(contact));
        }
        return responses;
    }
}
