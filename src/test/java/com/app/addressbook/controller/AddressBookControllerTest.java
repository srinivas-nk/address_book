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
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.delete;
import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItems;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class AddressBookControllerTest {

    @LocalServerPort
    int randomServerPort;

    private String baseUrl = "http://localhost:";

    @Before
    public void setUp() {
        baseUrl = baseUrl + randomServerPort + "/app/v1/address-book";
    }

    @Test
    public void getAddressBook() {
        Response response = get(baseUrl);
        response.then().body("id", hasItems(101, 102));
        response.then().body("name", hasItems("amazon","flipkart"));
    }

    @Test
    public void getAddressBookById() {
        Response response = get(baseUrl+"/102");
        response.then().body("id", Matchers.is(102));
        response.then().body("name", Matchers.is("flipkart"));
    }

    @Test
    public void addAddressBook() {
        Response response = given().
                contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("{\"name\": \"retail\"}")
                .when()
                .post(baseUrl);
        response.then().body("id", Matchers.any(Integer.class));
        response.then().body("name", Matchers.is("retail"));
    }

    @Test
    public void deleteAddressBook() {
        Response response = delete(baseUrl+"/101");
        response.then().body("id", Matchers.any(Integer.class));
        response.then().body("name", Matchers.is("amazon"));
    }

    @Test
    public void deleteAddressBookNotFound() {
        Response response = delete(baseUrl+"/1001");
        response.then().body("code", Matchers.is(404));
        response.then().body("message", Matchers.is("AddressBook Not Found"));
    }

    @Test
    public void getAddressBookByIdNotFound() {
        Response response = get(baseUrl+"/1001");
        response.then().body("code", Matchers.is(404));
        response.then().body("message", Matchers.is("AddressBook Not Found"));
    }
}