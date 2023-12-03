package com.eorhn.bookstore.model.responsetypes.booksapis;

import com.eorhn.bookstore.model.entities.TblBooks;
import com.eorhn.bookstore.model.interfaces.ApiResponse;
import com.eorhn.bookstore.model.responsetypes.ResponseHeader;

import java.util.List;

public class GetPagedBooksInfoApiResponse implements ApiResponse {

    private ResponseHeader responseHeader;
    private List<TblBooks> bookInfo;


    public List<TblBooks> getBookInfo() {
        return bookInfo;
    }

    public void setBookInfo(List<TblBooks> bookInfo) {
        this.bookInfo = bookInfo;
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
