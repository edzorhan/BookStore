package com.eorhn.bookstore.model.responsetypes.membersapis;

import com.eorhn.bookstore.model.entities.TblMembers;
import com.eorhn.bookstore.model.interfaces.ApiResponse;
import com.eorhn.bookstore.model.responsetypes.ResponseHeader;

import java.util.Optional;

public class GetMemberInfoApiResponse implements ApiResponse {

    private ResponseHeader responseHeader;
    private TblMembers memberInfo;


    public TblMembers getMemberInfo() {
        return memberInfo;
    }

    public void setMemberInfo(TblMembers memberInfo) {
        this.memberInfo = memberInfo;
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
