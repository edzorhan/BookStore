package com.eorhn.bookstore.model.responsetypes.booksapis;

import com.eorhn.bookstore.model.entities.TblBooks;
import com.eorhn.bookstore.model.interfaces.ApiResponse;
import com.eorhn.bookstore.model.responsetypes.ResponseHeader;

import java.util.List;

public class GetAllBooksApiResponse implements ApiResponse {

    private ResponseHeader responseHeader;
    private List<TblBooks> booksList;

    public List<TblBooks> getBooksList() {
        return booksList;
    }

    public void setBooksList(List<TblBooks> booksList) {
        this.booksList = booksList;
    }

    @Override
    public ResponseHeader getResponseHeader() {
        return this.responseHeader;
    }

    @Override
    public void setResponseHeader(ResponseHeader header) {
        this.responseHeader = header;
    }
}
