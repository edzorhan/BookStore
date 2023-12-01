package com.eorhn.bookstore.model.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name="ORDERS")
public class TblOrders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ORDERID")
    private long orderId;
    @Column(name="MEMBERID")
    private long memberId;
    @Column(name="BOOKID")
    private long bookId;
    @Column(name="PURCHASE_COUNT")
    private long count;
    @Column(name="PRICE")
    private BigDecimal price;
    @Column(name="DELIVERY_ADDRESS")
    private String address;

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public long getMemberId() {
        return memberId;
    }

    public void setMemberId(long memberId) {
        this.memberId = memberId;
    }

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
