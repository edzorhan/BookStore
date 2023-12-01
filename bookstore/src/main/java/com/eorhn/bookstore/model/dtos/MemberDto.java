package com.eorhn.bookstore.model.dtos;


import jakarta.validation.constraints.Positive;

public class MemberDto {
    @Positive(message = "ID must be bigger than zero!")
    private long memberId;
    private String name;
    private String surname;

    private String address;

    public long getMemberId() {
        return memberId;
    }

    public void setMemberId(long memberId) {
        this.memberId = memberId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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
}
