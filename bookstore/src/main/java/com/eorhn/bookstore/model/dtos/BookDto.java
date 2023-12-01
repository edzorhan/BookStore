package com.eorhn.bookstore.model.dtos;

import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public class BookDto {
    @Positive(message = "ID must be bigger than zero!")
    private long bookId;
    private String bookName;
    private String author;

    @Positive(message = "Price must be bigger than zero!")
    private BigDecimal price;

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
