package com.eorhn.bookstore.model.responsetypes.ordersapis;

import com.eorhn.bookstore.model.interfaces.ApiResponse;
import com.eorhn.bookstore.model.responsetypes.ResponseHeader;

public class PlaceOrderApiResponse implements ApiResponse {

    private ResponseHeader responseHeader;


    @Override
    public ResponseHeader getResponseHeader() {
        return this.responseHeader;
    }

    @Override
    public void setResponseHeader(ResponseHeader header) {
        this.responseHeader = header;
    }
}
