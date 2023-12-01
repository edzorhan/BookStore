package com.eorhn.bookstore.model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name="BOOKS")
public class TblBooks {

    @Id
    @Column(name="BOOKID")
    private long bookId;
    @Column(name="BOOK_NAME")
    private String bookName;
    @Column(name="AUTHOR")
    private String author;
    @Column(name="PRICE")
    private BigDecimal price;
    @Column(name="STOCK_COUNT")
    private long stockCount;

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public long getStockCount() {
        return stockCount;
    }

    public void setStockCount(long stockCount) {
        this.stockCount = stockCount;
    }
}
