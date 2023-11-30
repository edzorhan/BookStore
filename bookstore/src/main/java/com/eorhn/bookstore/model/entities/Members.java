package com.eorhn.bookstore.model.entities;

import jakarta.persistence.*;

@Entity
@Table(name="MEMBERS")
public class Members {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="MEMBERID")
    private long memberId;
    @Column(name="NAME")
    private String name;
    @Column(name="SURNAME")
    private String surname;
    @Column(name="ADDRESS")
    private String address;

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getMemberId() {
        return memberId;
    }

    public void setMemberId(long memberId) {
        this.memberId = memberId;
    }
}
