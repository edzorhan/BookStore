package com.eorhn.bookstore.model.enums;


public enum BookSortFields {
    ID("bookId"),
    NAME("bookName"),
    AUTHOR("author"),
    PRICE("price"),
    STOCK_COUNT("stockCount");


    private final String columnName;

    BookSortFields(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnName() {
        return columnName;
    }
}
