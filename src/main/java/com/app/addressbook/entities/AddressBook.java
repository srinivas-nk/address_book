package com.app.addressbook.entities;

import lombok.Data;
import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "ADDRESSBOOK")
public class AddressBook {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;

    String name;

    @OneToMany(mappedBy = "addressBook",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Contact> contacts;
}
