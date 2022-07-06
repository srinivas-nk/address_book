package com.app.addressbook.controller;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.annotation.Order;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.delete;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class ContactControllerTest {
    @LocalServerPort
    int randomServerPort;

    private String baseUrl = "http://localhost:";

    @Before
    public void setUp() {
        baseUrl = baseUrl + randomServerPort + "/app/v1/address-book";
    }

    @Test
    public void getAllUniqueContacts() {
        Response response = get(baseUrl+"/all-unique-contacts");
        response.then().body("size", is(5));
        //add existing contact
        response = given().
                contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("{\"name\": \"Customer1\",\"phone\": \"9812345678\"}")
                .when()
                .post(baseUrl+"/101/contacts");
        //get unique-contacts
        response = get(baseUrl+"/all-unique-contacts");
        response.then().body("size", is(5));

    }

    @Test
    public void getContactByIdAndAddressBookId() {
        Response response = get(baseUrl+"/101/contacts/1001");
        response.then().body("id", Matchers.any(Integer.class));
        response.then().body("name", Matchers.is("Customer2"));
        response.then().body("phone", Matchers.is("9812345679"));
    }

    @Test
    public void addContact() {
        Response response = given().
                contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("{\"name\": \"Customer1\",\"phone\": \"9812345678\"}")
                .when()
                .post(baseUrl+"/101/contacts");
        //System.out.println(response.asString());
        response.then().body("id", Matchers.any(Integer.class));
        response.then().body("name", Matchers.is("Customer1"));
        response.then().body("phone", Matchers.is("9812345678"));
    }

    @Test
    public void deleteByIdAndAddressBookId() {
        Response response = delete(baseUrl+"/101/contacts/1000");
        response.then().body("id", Matchers.any(Integer.class));
        response.then().body("name", Matchers.is("Customer1"));
        response.then().body("phone", Matchers.is("9812345678"));
    }

    @Test
    public void deleteByIdAndAddressBookIdNotFound() {
        Response response = delete(baseUrl+"/101/contacts/2000");
        //System.out.println(response.asString());
        response.then().body("code", Matchers.is(404));
        response.then().body("message", Matchers.is("Contact Not Found"));
    }

    @Test
    public void getAllCommonContacts() {
        Response response = get(baseUrl+"/all-common-contacts");
        response.then().body("size", is(2));
        //add existing contact in 102 addressbook to 101 addressbook
        response = given().
                contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("{\"name\": \"Customer9\",\"phone\": \"9812345666\"}")
                .when()
                .post(baseUrl+"/101/contacts");
        //get unique-contacts
        response = get(baseUrl+"/all-common-contacts");
        response.then().body("size", is(3));

    }
}