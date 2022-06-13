package com.app.addressbook.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Objects;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContactResponse {
    private int id;
    private String name;
    private String phone;
    private String message;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactResponse that = (ContactResponse) o;
        return name.equals(that.name) && phone.equals(that.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, phone);
    }
}
