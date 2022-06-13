package com.app.addressbook.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "CONTACT")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    private String phone;

    @ManyToOne
    @JoinColumn(name="address_book_id", nullable=false)
    private AddressBook addressBook;

}
