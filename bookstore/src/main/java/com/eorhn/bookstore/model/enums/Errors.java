package com.eorhn.bookstore.model.enums;

public enum Errors {
    /**
     * Enum class for handling error messages and according codes from one place.
     */
    TABLE_IS_EMPTY("Table is empty!",1001),
    MEMBER_NOT_FOUND("Member does not exist!",1002),
    MEMBER_ALREADY_EXISTS("Member already exist!",1003),
    BOOK_NOT_FOUND("Book does not exist!",1004),
    BOOK_ALREADY_EXISTS("Book already exist!",1005),
    DELETE_NOT_FOUND("Record does not exist!" ,1006),
    UPDATE_GENERAL_EXCEPTION("Update caught exception!", -1),
    INSERT_GENERAL_EXCEPTION("Insert caught exception!", -1);



    public final String message;
    public final long code;

    Errors(String message, long code) {
        this.message = message;
        this.code = code;
    }
}
