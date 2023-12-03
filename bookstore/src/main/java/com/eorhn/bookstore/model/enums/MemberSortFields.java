package com.eorhn.bookstore.model.enums;


public enum MemberSortFields {
    ID("memberId"),
    NAME("name");

    private final String columnName;

    MemberSortFields(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnName() {
        return columnName;
    }
}
