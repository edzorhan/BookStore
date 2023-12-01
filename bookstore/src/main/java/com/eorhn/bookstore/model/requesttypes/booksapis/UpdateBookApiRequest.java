package com.eorhn.bookstore.model.requesttypes.booksapis;

import com.eorhn.bookstore.model.dtos.BookDto;
import com.eorhn.bookstore.model.dtos.MemberDto;
import jakarta.validation.Valid;

public class UpdateBookApiRequest {

    @Valid
    private BookDto bookDto;

    public BookDto getBookDto() {
        return bookDto;
    }

    public void setBookDto(BookDto bookDto) {
        this.bookDto = bookDto;
    }
}
