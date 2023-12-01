package com.eorhn.bookstore.model.interfaces;

import com.eorhn.bookstore.model.responsetypes.ResponseHeader;

public interface ApiResponse {
    ResponseHeader getResponseHeader();

    void setResponseHeader(ResponseHeader header);
}
