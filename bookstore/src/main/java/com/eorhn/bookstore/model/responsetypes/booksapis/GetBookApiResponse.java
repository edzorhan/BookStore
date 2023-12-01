package com.eorhn.bookstore.model.responsetypes.booksapis;

import com.eorhn.bookstore.model.entities.TblBooks;
import com.eorhn.bookstore.model.interfaces.ApiResponse;
import com.eorhn.bookstore.model.responsetypes.ResponseHeader;

public class GetBookApiResponse implements ApiResponse {

    private ResponseHeader responseHeader;
    private TblBooks bookInfo;


    public TblBooks getBookInfo() {
        return bookInfo;
    }

    public void setBookInfo(TblBooks bookInfo) {
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
