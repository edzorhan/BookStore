package com.eorhn.bookstore.model.responsetypes.membersapis;

import com.eorhn.bookstore.model.interfaces.ApiResponse;
import com.eorhn.bookstore.model.responsetypes.ResponseHeader;

public class DeleteMemberApiResponse implements ApiResponse {

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
